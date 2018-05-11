package bupt.com.vr_bupt.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.martin.ads.vrlib.constant.MimeType;
import com.martin.ads.vrlib.ui.Pano360ConfigBundle;
import com.martin.ads.vrlib.ui.PanoPlayerActivity;

import java.util.ArrayList;
import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.adapter.AttentionAdapter;
import bupt.com.vr_bupt.adapter.AttentionAdapter2;
import bupt.com.vr_bupt.adapter.CardRecordAdapter;
import bupt.com.vr_bupt.bean.CommonBean;
import bupt.com.vr_bupt.data.SummaryData;
import bupt.com.vr_bupt.ui.DemoWithGLSurfaceView;
import bupt.com.vr_bupt.widget.HorizontalListView;
import bupt.com.vr_bupt.widget.NoScrollGridView;
import cn.lemon.view.RefreshRecyclerView;
import cn.lemon.view.adapter.Action;

public class AttentionFragment extends Fragment{
    private Context context;
    protected View contentView;
    private ArrayList<CommonBean> mData;
    private RefreshRecyclerView mRecyclerView;
    private HorizontalListView attentionListView;
    private AttentionAdapter attentionAdapter;
    private AttentionAdapter2 attentionAdapter2;
    private NoScrollGridView noScrollGridView;
    private CardRecordAdapter mAdapter;
    private ArrayList<CommonBean> attentionData;
    private Handler mHandler;
    private int page = 1;

    private String url="";
    private int mimeType;
    private boolean USE_DEFAULT_ACTIVITY = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_attention,container,false);
        context = getActivity();
        initViews(contentView);
        initViews(contentView);
        return contentView;
    }

    private void initViews(View contentView) {
        mHandler = new Handler();
        mAdapter = new CardRecordAdapter(context);
        attentionListView = (HorizontalListView) contentView.findViewById(R.id.attention_lv);

        mRecyclerView = (RefreshRecyclerView) contentView.findViewById(R.id.recycler_view2);
        mRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        addData();
        attentionAdapter = new AttentionAdapter(context,attentionData);
        attentionListView.setAdapter(attentionAdapter);
        noScrollGridView = (NoScrollGridView) contentView.findViewById(R.id.recycler_view);
        attentionAdapter2 = new AttentionAdapter2(context,mData);
        mAdapter.addAll(mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.dismissSwipeRefresh();
        noScrollGridView.setAdapter(attentionAdapter2);
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
        attentionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"点我干哈？",Toast.LENGTH_LONG).show();
            }
        });
        noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                url = mData.get(position).getUrl();
                mimeType = MimeType.ONLINE | MimeType.VIDEO;
                start();
            }
        });

    }

    private void addData() {
        attentionData = new ArrayList<>();
        for (int i = 0; i < SummaryData.vr_video_icon.length; i++) {
            attentionData.add(new CommonBean(SummaryData.vr_video_url[i],SummaryData.vr_video_icon[i], SummaryData.vr_video_title[i]));
        }
        mData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mData.add(new CommonBean(SummaryData.vr_video_url[i],SummaryData.vr_video_icon[i], SummaryData.vr_video_title[i]));
        }
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

