package bupt.com.vr_bupt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.bean.CommonBean;

public class AttentionAdapter extends MyBaseAdapter<CommonBean> {
    private LayoutInflater inflater;

    public AttentionAdapter(Context context, ArrayList<CommonBean> mData) {
        super(context, mData);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.comm_item4, null, false);
        holderView.title = (TextView) convertView.findViewById(R.id.comm_title);
        holderView.imageView = (ImageView) convertView.findViewById(R.id.comm_img);

        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, CommonBean model) {
        ViewHolder holderView = (ViewHolder) view.getTag();
        holderView.title.setText(model.getTitle());
        holderView.imageView.setImageResource(model.getIcon());
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
