package bupt.com.vr_bupt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.martin.ads.vrlib.constant.MimeType;
import com.martin.ads.vrlib.ui.Pano360ConfigBundle;
import com.martin.ads.vrlib.ui.PanoPlayerActivity;
import com.martin.ads.vrlib.utils.BitmapUtils;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;

import bupt.com.vr_bupt.adapter.ComAdapter;
import bupt.com.vr_bupt.bean.CommonBean;
import bupt.com.vr_bupt.data.SummaryData;

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
                filePath = "http://cache.utovr.com/201508270528174780.m3u8";
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
            case R.id.RelativeLayout2:
                if (Build.VERSION.SDK_INT >= 23 && (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                } else {
                    Intent intent = new Intent(this, FilePickerActivity.class);
                    intent.putExtra(FilePickerActivity.ARG_FILTER, Pattern.compile("(.*\\.mp4$)||(.*\\.avi$)||(.*\\.wmv$)"));
                    startActivityForResult(intent, 1);
                }
                break;

            case R.id.RelativeLayout3:
                Intent intent2 = new Intent(this, LocalVedioActivity.class);
                startActivity(intent2);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            mimeType = MimeType.LOCAL_FILE | MimeType.VIDEO;
            start();
        }
    }
}
