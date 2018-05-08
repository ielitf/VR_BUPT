package bupt.com.vr_bupt.ui.channel;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.adapter.CardRecordAdapter;
import bupt.com.vr_bupt.adapter.ChannelCardRecordAdapter;
import bupt.com.vr_bupt.bean.ChannelBean;
import bupt.com.vr_bupt.bean.CommonBean;
import bupt.com.vr_bupt.data.SummaryData;
import cn.lemon.view.RefreshRecyclerView;
import cn.lemon.view.adapter.Action;


public class ChannelFragment extends Fragment  {
    private TextView topName;
    private Context context;
    protected View contentView;
    private ArrayList<ChannelBean> mData;
    private RefreshRecyclerView mRecyclerView;
    private ChannelCardRecordAdapter mAdapter;
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
        mAdapter = new ChannelCardRecordAdapter(context);
        mRecyclerView = (RefreshRecyclerView) contentView.findViewById(R.id.recycler_view_channel);
        mRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        addData();
        mAdapter.addAll(mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.dismissSwipeRefresh();

        mRecyclerView.setRefreshAction(new Action() {
            @Override
            public void onAction() {
                mRecyclerView.dismissSwipeRefresh();
            }
        });

        mRecyclerView.setLoadMoreAction(new Action() {
            @Override
            public void onAction() {
                mRecyclerView.showNoMore();
            }
        });

    }

    private void addData() {
        mData = new ArrayList<>();
        for (int i = 0; i < SummaryData.channel_icon.length; i++) {
            mData.add(new ChannelBean(SummaryData.channel_icon[i], SummaryData.channel_title[i]));
        }
    }
}

