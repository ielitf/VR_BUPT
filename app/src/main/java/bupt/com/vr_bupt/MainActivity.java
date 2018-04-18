package bupt.com.vr_bupt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.martin.ads.vrlib.constant.MimeType;
import com.martin.ads.vrlib.ext.GirlFriendNotFoundException;
import com.martin.ads.vrlib.ui.Pano360ConfigBundle;
import com.martin.ads.vrlib.ui.PanoPlayerActivity;
import com.martin.ads.vrlib.utils.BitmapUtils;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;

import bupt.com.vr_bupt.adapter.ComAdapter;
import bupt.com.vr_bupt.bean.CommonBean;
import bupt.com.vr_bupt.data.SummaryData;
import bupt.com.vr_bupt.utils.ImageUtils;

public class MainActivity extends AppCompatActivity implements AdapterView.OnClickListener {
    private Context context;
    private ListView mListView;
    private ArrayList<CommonBean> mData;
    private ComAdapter adapter;
    private View relativeLayout1, relativeLayout2, relativeLayout3, relativeLayout4;
    private String videoHotspotPath;
    private boolean planeModeEnabled;
    private int mimeType;
    private String filePath = "~(～￣▽￣)～";
    private boolean USE_DEFAULT_ACTIVITY = true;
    private static final int CODE_GALLERY_REQUEST = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        initViews();
        addData();
        adapter = new ComAdapter(context, mData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                filePath = "http://cache.utovr.com/201508270528174780.m3u8";
                switch (position){
                    case 0:
//                        filePath = "http://192.168.9.242/demo.mp4 ";//内网
                        filePath = "http://182.18.26.6/demo.mp4";//公网
                        break;
                    case 1:
//                        filePath = "http://192.168.9.242/congo.mp4 ";
                        filePath = "http://182.18.26.6/congo.mp4";
                        break;
                    case 2:
//                        filePath = "http://192.168.9.242/IFA2016 VR 1-MX005_injected.mp4 ";
                        filePath = "http://182.18.26.6/IFA2016 VR 1-MX005_injected.mp4";
                        break;
                    case 3:
//                        filePath = "http://192.168.9.242/MX026 Citadines Melbourne-V5_injected.mp4 ";
                        filePath = "http://182.18.26.6/MX026 Citadines Melbourne-V5_injected.mp4";
                        break;
                    case 4:
//                        filePath = "http://192.168.9.242/MX027 Somerset Hobart-V6_injected.mp4 ";
                        filePath = "http://182.18.26.6/MX027 Somerset Hobart-V6_injected.mp4 ";
                        break;
                    case 5:
//                        filePath = "http://192.168.9.242/MX028 Ayutthaya phase2_injected.mp4 ";
                        filePath = "http://182.18.26.6/MX028 Ayutthaya phase2_injected.mp4";
                        break;
                    case 6:
//                        filePath = "http://192.168.9.242/加勒比海巴哈马旅游-音乐版-MX016_injected.mp4 ";
                        filePath = "http://182.18.26.6/加勒比海巴哈马旅游-音乐版-MX016_injected.mp4";
                        break;
                    case 7:
//                        filePath = "http://192.168.9.242/泰国大城府遗迹延时VR-MX012_injected.mp4 ";
                        filePath = "http://182.18.26.6/泰国大城府遗迹延时VR-MX012_injected.mp4";
                        break;
                    default:
                        break;
                }
                mimeType = MimeType.ONLINE | MimeType.VIDEO;
                start();
            }
        });
    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.list_item);
        relativeLayout1 = findViewById(R.id.RelativeLayout1);
        relativeLayout2 = findViewById(R.id.RelativeLayout2);
        relativeLayout3 = findViewById(R.id.RelativeLayout3);
        relativeLayout4 = findViewById(R.id.RelativeLayout4);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        relativeLayout3.setOnClickListener(this);
        relativeLayout4.setOnClickListener(this);
    }

    private void addData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mData.add(new CommonBean(SummaryData.temple_icon[i], SummaryData.temple_title[i]));
        }
    }

    @Override
    public void onClick(View v) {
        videoHotspotPath = null;
        switch (v.getId()) {
            case R.id.RelativeLayout1:
                break;
            case R.id.RelativeLayout2://打开本地视频
                if (Build.VERSION.SDK_INT >= 23 && (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                } else {
                    Intent intent = new Intent(this, FilePickerActivity.class);
                    intent.putExtra(FilePickerActivity.ARG_FILTER, Pattern.compile("(.*\\.mp4$)||(.*\\.avi$)||(.*\\.wmv$)"));
                    startActivityForResult(intent, 1);
                }
                break;

            case R.id.RelativeLayout3://打开本地相册
//                Intent intent2 = new Intent(this, LocalVedioActivity.class);
//                startActivity(intent2);
                if (Build.VERSION.SDK_INT >= 23 && (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                } else{
                    choseHeadImageFromGallery();
                }
                break;
            case R.id.RelativeLayout4:

                break;
            default:
                break;
        }
    }

    private void start() {
        Pano360ConfigBundle configBundle = Pano360ConfigBundle
                .newInstance()
                .setFilePath(filePath)
                .setMimeType(mimeType)
                .setPlaneModeEnabled(false)
                .setRemoveHotspot(true);//去除中间那个“智障科技图片的”;

        if ((mimeType & MimeType.BITMAP) != 0) {
            //add your own picture here
            // this interface may be removed in future version.
            configBundle.startEmbeddedActivityWithSpecifiedBitmap(
                    this, BitmapUtils.loadBitmapFromRaw(this, R.mipmap.ic_launcher));
            return;
        }

        if (USE_DEFAULT_ACTIVITY)
            configBundle.startEmbeddedActivity(this);
        else {
            Intent intent = new Intent(this, DemoWithGLSurfaceView.class);
            intent.putExtra(PanoPlayerActivity.CONFIG_BUNDLE, configBundle);
            startActivity(intent);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if(resultCode == RESULT_OK){
            if (requestCode == 1 ) {
                filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                mimeType = MimeType.LOCAL_FILE | MimeType.VIDEO;
                start();
            }else
            if (requestCode == CODE_GALLERY_REQUEST ) {
                Uri uri = data.getData();
                filePath = ImageUtils.getRealPathFromURI(MainActivity.this, uri);
                Log.e("===========", "filePath:" + filePath);
                mimeType = MimeType.LOCAL_FILE | MimeType.PICTURE;
                start();
            }else {
                throw new GirlFriendNotFoundException();
            }
        }else{
            throw new GirlFriendNotFoundException();
        }
    }
}
