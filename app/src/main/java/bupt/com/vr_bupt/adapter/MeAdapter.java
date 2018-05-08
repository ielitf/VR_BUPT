package bupt.com.vr_bupt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.entity.MeItemBean;

public class MeAdapter extends MyBaseAdapter<MeItemBean> {

    private LayoutInflater inflater;

    public MeAdapter(Context context, List<MeItemBean> mData) {
        super(context, mData);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.me_item, null, false);
        holderView.title = (TextView) convertView.findViewById(R.id.item_text);
        holderView.imageView = (ImageView) convertView.findViewById(R.id.item_image);
        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, MeItemBean model) {
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
