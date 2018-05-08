package bupt.com.vr_bupt.ui.news;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import bupt.com.vr_bupt.R;


public class NewsFragment extends Fragment implements View.OnClickListener {
    protected View contentView;
    private TextView topName;
    private Context context;
    private TextView fans_tv,comment_tv,like_tv,reply_tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            contentView = inflater.inflate(R.layout.fragment_news,container,false);
        context = getActivity();
        initViews(contentView);
        return contentView;
    }

    private void initViews(View contentView) {
        topName = (TextView) contentView.findViewById(R.id.top_name_text);
        topName.setText("消息");
        fans_tv = (TextView) contentView.findViewById(R.id.fans_tv);
        comment_tv = (TextView) contentView.findViewById(R.id.comment_tv);
        like_tv = (TextView) contentView.findViewById(R.id.like_tv);
        reply_tv = (TextView) contentView.findViewById(R.id.reply_tv);
        fans_tv.setOnClickListener(this);
        comment_tv.setOnClickListener(this);
        like_tv.setOnClickListener(this);
        reply_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fans_tv:
                Toast.makeText(context,"粉丝",Toast.LENGTH_LONG).show();
                break;
            case R.id.comment_tv:
                Toast.makeText(context,"评论",Toast.LENGTH_LONG).show();
                break;
            case R.id.like_tv:
                Toast.makeText(context,"点赞",Toast.LENGTH_LONG).show();
                break;
            case R.id.reply_tv:
                Toast.makeText(context,"回复",Toast.LENGTH_LONG).show();
                break;
        }
    }
}

