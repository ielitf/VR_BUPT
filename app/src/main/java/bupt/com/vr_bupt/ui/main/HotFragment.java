package bupt.com.vr_bupt.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.adapter.CardRecordAdapter;
import bupt.com.vr_bupt.bean.VrVideoBean;
import bupt.com.vr_bupt.data.VrData;
import cn.lemon.view.RefreshRecyclerView;
import cn.lemon.view.adapter.Action;


public class HotFragment extends Fragment {
    private Context context;
    protected View contentView;
    private ArrayList<VrVideoBean> mData;
    private RefreshRecyclerView mRecyclerView;
    private CardRecordAdapter mAdapter;
    private Handler mHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_hot, container, false);
        context = getActivity();
        initViews(contentView);
        return contentView;
    }

    private void initViews(View contentView) {
        mHandler = new Handler();
        mAdapter = new CardRecordAdapter(context);
        mRecyclerView = (RefreshRecyclerView) contentView.findViewById(R.id.recycler_view);
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
        for (int i = 0; i < VrData.vr_video_url.length; i++) {
            mData.add(new VrVideoBean(VrData.vr_video_icon[i], VrData.vr_video_title[i], VrData.vr_video_url[i],
                    VrData.attention_icon[i], VrData.attention_name[i], VrData.vr_scan_num[i]));
        }
    }
}

