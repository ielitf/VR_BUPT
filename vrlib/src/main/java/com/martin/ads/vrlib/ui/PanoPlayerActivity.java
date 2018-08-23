package com.martin.ads.vrlib.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.martin.ads.vrlib.PanoMediaPlayerWrapper;
import com.martin.ads.vrlib.PanoViewWrapper;
import com.martin.ads.vrlib.R;
import com.martin.ads.vrlib.constant.MimeType;
import com.martin.ads.vrlib.constant.PanoMode;
import com.martin.ads.vrlib.constant.PanoStatus;
import com.martin.ads.vrlib.filters.advanced.FilterType;
import com.martin.ads.vrlib.utils.UIUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Ads on 2016/11/10.
 * UI is modified from UtoVR demo
 */
//FIXME:looks so lame.
public class PanoPlayerActivity extends Activity {

    public static final String CONFIG_BUNDLE = "configBundle";
    private PanoUIController mPanoUIController;
    private PanoViewWrapper mPanoViewWrapper;
    private ImageView mImgBufferAnim;
    private Pano360ConfigBundle configBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.player_activity_layout);
        init();
    }

    private void init(){
        configBundle= (Pano360ConfigBundle) getIntent().getSerializableExtra(CONFIG_BUNDLE);

        if(configBundle==null){
            throw new RuntimeException("config can't be null");
        }

        findViewById(R.id.img_full_screen).setVisibility(configBundle.isWindowModeEnabled() ? View.VISIBLE : View.GONE);

        mImgBufferAnim = (ImageView) findViewById(R.id.activity_imgBuffer);
        UIUtils.setBufferVisibility(mImgBufferAnim, !configBundle.isImageModeEnabled());
        mPanoUIController = new PanoUIController(
                (RelativeLayout)findViewById(R.id.player_toolbar_control),
                (RelativeLayout)findViewById(R.id.player_toolbar_progress),
                this, configBundle.isImageModeEnabled());

        TextView title = (TextView) findViewById(R.id.video_title);
        title.setText(Uri.parse(configBundle.getFilePath()).getLastPathSegment());

        GLSurfaceView glSurfaceView = (GLSurfaceView) findViewById(R.id.surface_view);

        Bitmap bitmap=null;
        if((configBundle.getMimeType() & MimeType.BITMAP)!=0){
            bitmap=getIntent().getParcelableExtra("bitmap");
        }
        mPanoViewWrapper = PanoViewWrapper.with(this)
                .setConfig(configBundle)
                .setGlSurfaceView(glSurfaceView)
                .setBitmap(bitmap)
                .init();
        if(configBundle.isRemoveHotspot())
            mPanoViewWrapper.removeDefaultHotSpot();
        glSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mPanoUIController.startHideControllerTimer();
                return mPanoViewWrapper.handleTouchEvent(event);
            }
        });
        mPanoUIController.setAutoHideController(true);
        mPanoUIController.setUiCallback(new PanoUIController.UICallback() {

            @Override
            public void share() {
                if (Build.VERSION.SDK_INT >= 23 && (PanoPlayerActivity.this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(PanoPlayerActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                } else
                    new ShareAction(PanoPlayerActivity.this).withText("hello")
                            .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,
                                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE)
                            .setCallback(shareListener).open();
            }

            @Override
            public void requestScreenshot() {
                mPanoViewWrapper.getTouchHelper().shotScreen();
            }

            @Override
            public void requestFinish() {
                finish();
            }

            @Override
            public void changeDisPlayMode() {
                if (mPanoViewWrapper.getStatusHelper().getPanoDisPlayMode()==PanoMode.DUAL_SCREEN)
                    mPanoViewWrapper.getStatusHelper().setPanoDisPlayMode(PanoMode.SINGLE_SCREEN);
                else mPanoViewWrapper.getStatusHelper().setPanoDisPlayMode(PanoMode.DUAL_SCREEN);
            }

            @Override
            public void changeInteractiveMode() {
                if (mPanoViewWrapper.getStatusHelper().getPanoInteractiveMode()==PanoMode.MOTION)
                    mPanoViewWrapper.getStatusHelper().setPanoInteractiveMode(PanoMode.TOUCH);
                else mPanoViewWrapper.getStatusHelper().setPanoInteractiveMode(PanoMode.MOTION);
            }

            @Override
            public void changePlayingStatus() {
                if (mPanoViewWrapper.getStatusHelper().getPanoStatus()== PanoStatus.PLAYING){
                    mPanoViewWrapper.getMediaPlayer().pauseByUser();
                }else if (mPanoViewWrapper.getStatusHelper().getPanoStatus()== PanoStatus.PAUSED_BY_USER){
                    mPanoViewWrapper.getMediaPlayer().start();
                }
            }

            @Override
            public void playerSeekTo(int pos) {
                mPanoViewWrapper.getMediaPlayer().seekTo(pos);
            }

            @Override
            public int getPlayerDuration() {
                return mPanoViewWrapper.getMediaPlayer().getDuration();
            }

            @Override
            public int getPlayerCurrentPosition() {
                return mPanoViewWrapper.getMediaPlayer().getCurrentPosition();
            }

            @Override
            public void addFilter(FilterType filterType) {
                mPanoViewWrapper.getRenderer().switchFilter();
            }
        });
        mPanoViewWrapper.getTouchHelper().setPanoUIController(mPanoUIController);

        if(!configBundle.isImageModeEnabled()){
            mPanoViewWrapper.getMediaPlayer().setPlayerCallback(new PanoMediaPlayerWrapper.PlayerCallback() {
                @Override
                public void updateProgress() {
                    mPanoUIController.updateProgress();
                }

                @Override
                public void updateInfo() {
                    UIUtils.setBufferVisibility(mImgBufferAnim,false);
                    mPanoUIController.startHideControllerTimer();
                    mPanoUIController.setInfo();
                }

                @Override
                public void requestFinish() {
                    finish();
                }
            });
        }else mPanoUIController.startHideControllerTimer();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mPanoViewWrapper.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mPanoViewWrapper.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mPanoViewWrapper.releaseResources();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(PanoPlayerActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(PanoPlayerActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(PanoPlayerActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };
}
