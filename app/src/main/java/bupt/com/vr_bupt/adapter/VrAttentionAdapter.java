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

public class VrAttentionAdapter extends MyBaseAdapter<VrVideoBean> {
    private LayoutInflater inflater;

    public VrAttentionAdapter(Context context, ArrayList<VrVideoBean> mData) {
        super(context, mData);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.comm_item4, null, false);
        holderView.title = (TextView) convertView.findViewById(R.id.attention_name);
        holderView.imageView = (ImageView) convertView.findViewById(R.id.attention_icon);

        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, VrVideoBean model) {
        ViewHolder holderView = (ViewHolder) view.getTag();
        holderView.title.setText(model.getVrVideoName());
        holderView.imageView.setImageResource(model.getVrVideoPicture());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView title;
        private ImageView imageView;
    }
}
