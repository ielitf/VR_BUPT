package bupt.com.vr_bupt.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.bean.ChannelBean;
import bupt.com.vr_bupt.bean.CommonBean;
import cn.lemon.view.adapter.BaseViewHolder;

public class ChannelCardRecordHolder extends BaseViewHolder<ChannelBean> {

    private ImageView live_img;
    private TextView live_text;
    private Context context;
    private String title="";

    public ChannelCardRecordHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.channel_holder_consume);
        this.context=context;
    }

    @Override
    public void setData(final ChannelBean object) {
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
        live_img = findViewById(R.id.channel_pic);
        live_text = findViewById(R.id.channel_text);
    }

    @Override
    public void onItemViewClick(ChannelBean object) {
        super.onItemViewClick(object);
        //点击事件
    }
}