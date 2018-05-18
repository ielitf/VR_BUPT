package bupt.com.vr_bupt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.bean.VrVideoBean;

public class VrVideoAdapter extends MyBaseAdapter<VrVideoBean> {
    private LayoutInflater inflater;
    public VrVideoAdapter(Context context, ArrayList<VrVideoBean> mData) {
        super(context, mData);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.vr_video_item, null, false);
        holderView.vrVideoName = (TextView) convertView.findViewById(R.id.live_text);
        holderView.vrAtenTionName = (TextView) convertView.findViewById(R.id.attention_name);
        holderView.vrScanNum = (TextView) convertView.findViewById(R.id.scan_num);
        holderView.vrVideoPicture = (ImageView) convertView.findViewById(R.id.live_pic);
        holderView.vrAtenTionHeadIcon = (ImageView) convertView.findViewById(R.id.attention_img);
        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, VrVideoBean model) {
        ViewHolder holderView = (ViewHolder) view.getTag();
        holderView.vrVideoName.setText(model.getVrVideoName());
        holderView.vrAtenTionName.setText(model.getVrAtenTionName());
        holderView.vrScanNum.setText(model.getVrScanNum());
        holderView.vrVideoPicture.setImageResource(model.getVrVideoPicture());
        holderView.vrAtenTionHeadIcon.setImageResource(model.getVrAtenTionHeadIcon());
        holderView.vrVideoUrl =  model.getVrVideoUrl();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView vrVideoName,vrAtenTionName,vrScanNum;
        private ImageView vrVideoPicture,vrAtenTionHeadIcon;
        private String vrVideoUrl="";
    }
}
