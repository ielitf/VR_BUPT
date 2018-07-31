package bupt.com.vr_bupt.ui.me;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.martin.ads.vrlib.constant.MimeType;
import com.martin.ads.vrlib.ext.GirlFriendNotFoundException;
import com.martin.ads.vrlib.ui.Pano360ConfigBundle;
import com.martin.ads.vrlib.ui.PanoPlayerActivity;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.adapter.MeAdapter;
import bupt.com.vr_bupt.control.CodeConstants;
import bupt.com.vr_bupt.entity.MeItemBean;
import bupt.com.vr_bupt.ui.DemoWithGLSurfaceView;
import bupt.com.vr_bupt.ui.MainActivity;
import bupt.com.vr_bupt.utils.ImageUtils;
import bupt.com.vr_bupt.widget.NoScrollListView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class MeFragment extends Fragment implements View.OnClickListener {
    protected View contentView;
    private TextView topName;
    private TextView mine_tv, mall_tv, history_tv, local_tv;
    private Context context;
    private NoScrollListView listView;
    private MeAdapter adapter;
    private List<MeItemBean> datas;
    private String[] titles = {"推荐应用", "我的收藏", "清除缓存", "用户反馈", "版本信息", "关于我们"};
    private int[] icons = {R.mipmap.message,
            R.mipmap.collect, R.mipmap.clear, R.mipmap.userback,
            R.mipmap.version, R.mipmap.aboutus};

    private int mimeType;
    private String filePath = "~(～￣▽￣)～";
    private boolean USE_DEFAULT_ACTIVITY = true;
    private static final int CODE_GALLERY_REQUEST = 111;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_me, container, false);
        context = getActivity();
        initViews(contentView);
        return contentView;
    }

    private void initViews(View contentView) {
        topName = (TextView) contentView.findViewById(R.id.top_name_text);
        topName.setText("我的");
        mine_tv = (TextView) contentView.findViewById(R.id.mine_tv);
        mall_tv = (TextView) contentView.findViewById(R.id.mall_tv);
        history_tv = (TextView) contentView.findViewById(R.id.history_tv);
        local_tv = (TextView) contentView.findViewById(R.id.local_tv);
        mine_tv.setOnClickListener(this);
        mall_tv.setOnClickListener(this);
        history_tv.setOnClickListener(this);
        local_tv.setOnClickListener(this);
        listView = (NoScrollListView) contentView.findViewById(R.id.me_listview);
        datas = new ArrayList<>();
        getdata();
        adapter = new MeAdapter(context, datas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        new ShareAction(getActivity()).withText("hello")
                                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,
                                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE)
                                .setCallback(shareListener).open();
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void getdata() {
        for (int i = 0; i < titles.length; i++) {
            datas.add(new MeItemBean(icons[i], titles[i]));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_tv:
                Toast.makeText(context, "点我干哈？", Toast.LENGTH_LONG).show();
                break;
            case R.id.mall_tv:
                Toast.makeText(context, "点我干哈？", Toast.LENGTH_LONG).show();
                break;
            case R.id.history_tv:
                Toast.makeText(context, "点我干哈？", Toast.LENGTH_LONG).show();
                break;
            case R.id.local_tv:

                //打开手机文件夹
                if (Build.VERSION.SDK_INT >= 23 && (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                } else {
                    Intent intent = new Intent(context, FilePickerActivity.class);
                    intent.putExtra(FilePickerActivity.ARG_FILTER, Pattern.compile("(.*\\.mp4$)||(.*\\.avi$)||(.*\\.wmv$)"));
                    startActivityForResult(intent, CodeConstants.CODE_VIDEO_LOCAL);
                }

                //打开手机相册
//                if (Build.VERSION.SDK_INT >= 23 && (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
//                } else{
//                    choseHeadImageFromGallery();
//                }
                break;
        }
    }

    /**
     * 从本地相册选取图片
     */

    private void choseHeadImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, null);
        // 此处调用了图片选择器
        // 如果直接写intent.setDataAndType("image/*");
        // 调用的是系统图库
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CODE_GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (resultCode == RESULT_OK) {
            Log.e("===========", "requestCode:" + requestCode);
            if (requestCode == CodeConstants.CODE_VIDEO_LOCAL) {
                filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                mimeType = MimeType.LOCAL_FILE | MimeType.VIDEO;
                start();
            } else if (requestCode == CODE_GALLERY_REQUEST) {
                Uri uri = data.getData();
                filePath = ImageUtils.getRealPathFromURI(context, uri);
                Log.e("===========", "filePath:" + filePath);
                mimeType = MimeType.LOCAL_FILE | MimeType.PICTURE;
                start();
            } else {
                throw new GirlFriendNotFoundException();
            }
        } else {
            throw new GirlFriendNotFoundException();
        }
    }

    private void start() {
        Pano360ConfigBundle configBundle = Pano360ConfigBundle
                .newInstance()
                .setFilePath(filePath)
                .setMimeType(mimeType)
                .setPlaneModeEnabled(false)
                .setRemoveHotspot(true);//去除中间那个“智障科技图片的”;

//        if ((mimeType & MimeType.BITMAP) != 0) {
//            //add your own picture here
//            // this interface may be removed in future version.
//            configBundle.startEmbeddedActivityWithSpecifiedBitmap(
//                    this, BitmapUtils.loadBitmapFromRaw(this, R.mipmap.ic_launcher));
//            return;
//        }

        if (USE_DEFAULT_ACTIVITY)
            configBundle.startEmbeddedActivity(context);
        else {
            Intent intent = new Intent(context, DemoWithGLSurfaceView.class);
            intent.putExtra(PanoPlayerActivity.CONFIG_BUNDLE, configBundle);
            startActivity(intent);
        }
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
            Toast.makeText(context, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, "取消了", Toast.LENGTH_LONG).show();

        }
    };
}

