package bupt.com.vr_bupt.ui.me;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.adapter.MeAdapter;
import bupt.com.vr_bupt.entity.MeItemBean;
import bupt.com.vr_bupt.widget.NoScrollListView;


public class MeFragment extends Fragment  {
    protected View contentView;
    private TextView topName;
    private Context context;
    private NoScrollListView listView;
    private MeAdapter adapter;
    private List<MeItemBean> datas;
    private String[] titles = {"消息推送", "我的监控", "我的收藏", "清除缓存", "用户反馈", "版本信息", "关于我们"};
    private int[] icons = {R.mipmap.message, R.mipmap.monitor,
            R.mipmap.collect, R.mipmap.clear, R.mipmap.userback,
            R.mipmap.version, R.mipmap.aboutus};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            contentView = inflater.inflate(R.layout.fragment_me,container,false);
        context = getActivity();
        initViews(contentView);
        return contentView;
    }
    private void initViews(View contentView){
        topName = (TextView) contentView.findViewById(R.id.top_name_text);
        topName.setText("我的");
        listView = (NoScrollListView) contentView.findViewById(R.id.me_listview);
        datas = new ArrayList<>();
        getdata();
        adapter = new MeAdapter(context, datas);
        listView.setAdapter(adapter);
    }
    private void getdata() {
        for (int i = 0; i < titles.length; i++) {
            datas.add(new MeItemBean(icons[i], titles[i]));
        }
    }
}

