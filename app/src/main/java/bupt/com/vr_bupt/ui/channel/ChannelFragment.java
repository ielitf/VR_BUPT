package bupt.com.vr_bupt.ui.channel;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;
import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.adapter.VrChannelAdapter;
import bupt.com.vr_bupt.bean.ChannelBean;
import bupt.com.vr_bupt.data.VrData;


public class ChannelFragment extends Fragment  {
    private TextView topName;
    private Context context;
    protected View contentView;
    private ArrayList<ChannelBean> mData;
    private GridView mRecyclerView;
    private VrChannelAdapter mAdapter;
    private Handler mHandler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_channel,container,false);
        context = getActivity();
        initViews(contentView);
        return contentView;
    }
    private void initViews(View contentView) {
        topName = (TextView) contentView.findViewById(R.id.top_name_text);
        topName.setText("频道");
        mHandler = new Handler();
        addData();
        mRecyclerView = (GridView) contentView.findViewById(R.id.recycler_view_channel);
        mAdapter = new VrChannelAdapter(context,mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addData() {
        mData = new ArrayList<>();
        for (int i = 0; i < VrData.channel_icon.length; i++) {
            mData.add(new ChannelBean(VrData.channel_icon[i], VrData.channel_title[i]));
        }
    }
}

