package bupt.com.vr_bupt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.martin.ads.vrlib.constant.MimeType;
import com.martin.ads.vrlib.ui.Pano360ConfigBundle;
import com.martin.ads.vrlib.ui.PanoPlayerActivity;
import com.martin.ads.vrlib.utils.BitmapUtils;

/**
 */
public class DemoVidioActivity extends AppCompatActivity {

    private  Button button ;
    private String filePath = "~(～￣▽￣)～";
    private int mimeType;
    private boolean USE_DEFAULT_ACTIVITY = true;
    private String videoHotspotPath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_vidio);
        button = (Button) findViewById(R.id.demo_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePath = "android.resource://" + getPackageName() + "/" + R.raw.demo_vidio_test;
                mimeType = MimeType.RAW | MimeType.VIDEO;
                start();
            }
        });
    }
    private void start() {
        Pano360ConfigBundle configBundle = Pano360ConfigBundle
                .newInstance()
                .setFilePath(filePath)
                .setMimeType(mimeType)
                .setPlaneModeEnabled(false)
                .setRemoveHotspot(true)//去除中间那个“智障科技图片的”
                .setVideoHotspotPath(videoHotspotPath)
                ;


        if (USE_DEFAULT_ACTIVITY)
            configBundle.startEmbeddedActivity(this);
        else {
            Intent intent = new Intent(this, DemoWithGLSurfaceView.class);
            intent.putExtra(PanoPlayerActivity.CONFIG_BUNDLE, configBundle);
            startActivity(intent);
        }
    }
}
