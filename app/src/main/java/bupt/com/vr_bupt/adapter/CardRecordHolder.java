package bupt.com.vr_bupt.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.ads.vrlib.constant.MimeType;
import com.martin.ads.vrlib.ui.Pano360ConfigBundle;
import com.martin.ads.vrlib.ui.PanoPlayerActivity;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.bean.VrVideoBean;
import bupt.com.vr_bupt.ui.DemoWithGLSurfaceView;
import cn.lemon.view.adapter.BaseViewHolder;

public class CardRecordHolder extends BaseViewHolder<VrVideoBean> {
    private TextView vrVideoName,vrAtenTionName,vrScanNum;
    private ImageView vrVideoPicture,vrAtenTionHeadIcon;
    private String vrVideoUrl="";

    private Context context;
    private String title="";
    private String url="";
    private int mimeType;
    private boolean USE_DEFAULT_ACTIVITY = true;

    public CardRecordHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.vr_video_item);
        this.context=context;

    }

    @Override
    public void setData(final VrVideoBean object) {
        super.setData(object);
        title=object.getVrVideoName();
        if (object.getVrVideoName().equals("")){
            vrVideoName.setVisibility(View.GONE);
            vrVideoPicture.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }else {
            vrVideoName.setVisibility(View.VISIBLE);
            vrVideoName.setText(object.getVrVideoName());
            vrVideoPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        vrVideoPicture.setImageResource(object.getVrVideoPicture());

        vrVideoName.setText(object.getVrVideoName());
        vrAtenTionName.setText(object.getVrAtenTionName());
        vrScanNum.setText(object.getVrScanNum());
        vrVideoPicture.setImageResource(object.getVrVideoPicture());
        vrAtenTionHeadIcon.setImageResource(object.getVrAtenTionHeadIcon());
        vrVideoUrl =  object.getVrVideoUrl();

    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        vrVideoName = findViewById(R.id.live_text);
        vrAtenTionName = findViewById(R.id.attention_name);
        vrScanNum = findViewById(R.id.scan_num);
        vrVideoPicture = findViewById(R.id.live_pic);
        vrAtenTionHeadIcon = findViewById(R.id.attention_img);
    }

    @Override
    public void onItemViewClick(VrVideoBean object) {
        super.onItemViewClick(object);
        url = object.getVrVideoUrl();
        mimeType = MimeType.ONLINE | MimeType.VIDEO;
        start();
    }
    private void start() {
        Pano360ConfigBundle configBundle = Pano360ConfigBundle
                .newInstance()
                .setFilePath(url)
                .setMimeType(mimeType)
                .setPlaneModeEnabled(true)
                .setRemoveHotspot(true);//去除中间那个“智障科技图片的”;

        if (USE_DEFAULT_ACTIVITY)
            configBundle.startEmbeddedActivity(context);
        else {
            Intent intent = new Intent(context, DemoWithGLSurfaceView.class);
            intent.putExtra(PanoPlayerActivity.CONFIG_BUNDLE, configBundle);
            context.startActivity(intent);
        }
    }
}