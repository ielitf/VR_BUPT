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
import bupt.com.vr_bupt.bean.CommonBean;
import bupt.com.vr_bupt.ui.DemoWithGLSurfaceView;
import cn.lemon.view.adapter.BaseViewHolder;

public class CardRecordHolder extends BaseViewHolder<CommonBean> {

    private ImageView live_img;
    private TextView live_text;
    private Context context;
    private String title="";
    private String url="";
    private int mimeType;
    private boolean USE_DEFAULT_ACTIVITY = true;

    public CardRecordHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.holder_consume);
        this.context=context;

    }

    @Override
    public void setData(final CommonBean object) {
        super.setData(object);
        title=object.getTitle();
        if (object.getTitle().equals("")){
            live_text.setVisibility(View.GONE);
            live_img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }else {
            live_text.setVisibility(View.VISIBLE);
            live_text.setText(object.getTitle());
            live_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        live_img.setImageResource(object.getIcon());
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        live_img = findViewById(R.id.live_pic);
        live_text = findViewById(R.id.live_text);
    }

    @Override
    public void onItemViewClick(CommonBean object) {
        super.onItemViewClick(object);
        url = object.getUrl();
        mimeType = MimeType.ONLINE | MimeType.VIDEO;
        start();
    }
    private void start() {
        Pano360ConfigBundle configBundle = Pano360ConfigBundle
                .newInstance()
                .setFilePath(url)
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
            context.startActivity(intent);
        }
    }
}