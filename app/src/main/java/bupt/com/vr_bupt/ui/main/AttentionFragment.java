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
import bupt.com.vr_bupt.adapter.VrAttentionAdapter;
import bupt.com.vr_bupt.adapter.VrVideoAdapter;
import bupt.com.vr_bupt.bean.CommonBean;
import bupt.com.vr_bupt.bean.VrVideoBean;
import bupt.com.vr_bupt.data.VrData;
import bupt.com.vr_bupt.ui.DemoWithGLSurfaceView;
import bupt.com.vr_bupt.widget.HorizontalListView;
import bupt.com.vr_bupt.widget.NoScrollGridView;
import cn.lemon.view.RefreshRecyclerView;
import cn.lemon.view.adapter.Action;

public class AttentionFragment extends Fragment{
    private Context context;
    protected View contentView;
    private ArrayList<VrVideoBean> mData;
    private ArrayList<VrVideoBean> attentionData;
    private HorizontalListView attentionListView;
    private VrAttentionAdapter vrAttentionAdapter;
    private VrVideoAdapter vrVideoAdapter;
    private NoScrollGridView noScrollGridView;
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
        return contentView;
    }

    private void initViews(View contentView) {
        mHandler = new Handler();
        attentionListView = (HorizontalListView) contentView.findViewById(R.id.attention_lv);
        noScrollGridView = (NoScrollGridView) contentView.findViewById(R.id.recycler_view);
        addData();
        vrAttentionAdapter = new VrAttentionAdapter(context,attentionData);
        attentionListView.setAdapter(vrAttentionAdapter);
        vrVideoAdapter = new VrVideoAdapter(context,mData);
        noScrollGridView.setAdapter(vrVideoAdapter);

        attentionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,attentionData.get(position).getVrVideoName(),Toast.LENGTH_LONG).show();
            }
        });
        noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                url = mData.get(position).getVrVideoUrl();
                mimeType = MimeType.ONLINE | MimeType.VIDEO;
                start();
            }
        });

    }
    private void addData() {
        attentionData = new ArrayList<>();
        for (int i = 0; i < VrData.attention_icon.length; i++) {
            attentionData.add(new VrVideoBean(VrData.attention_icon[i], VrData.attention_name[i]));
        }

        mData = new ArrayList<>();
        for (int i = 0; i < VrData.vr_video_icon.length; i++) {
            mData.add(new VrVideoBean(VrData.vr_video_icon[i], VrData.vr_video_title[i], VrData.vr_video_url[i],
                    VrData.attention_icon[i], VrData.attention_name[i], VrData.vr_scan_num[i]));
        }
    }
    private void start() {
        Pano360ConfigBundle configBundle = Pano360ConfigBundle
                .newInstance()
                .setFilePath(url)
                .setMimeType(mimeType)
                .setPlaneModeEnabled(false)
                .setRemoveHotspot(true);//去除中间那个“智障科技图片的”;

        if (USE_DEFAULT_ACTIVITY)
            configBundle.startEmbeddedActivity(context);
        else {
            Intent intent = new Intent(context, DemoWithGLSurfaceView.class);
            intent.putExtra(PanoPlayerActivity.CONFIG_BUNDLE, configBundle);
            context.startActivity(intent);
        }
    }
}

