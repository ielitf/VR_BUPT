package bupt.com.vr_bupt;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import bupt.com.vr_bupt.adapter.ComAdapter;
import bupt.com.vr_bupt.bean.CommonBean;
import bupt.com.vr_bupt.data.SummaryData;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ListView mListView;
    private ArrayList<CommonBean> mData;
    private ComAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        mListView = (ListView) findViewById(R.id.list_item);
        addData();
        adapter = new ComAdapter(context,mData);
        mListView.setAdapter(adapter);
    }

    private void addData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mData.add(new CommonBean(SummaryData.temple_icon[i], SummaryData.temple_title[i]));
        }
    }
}
