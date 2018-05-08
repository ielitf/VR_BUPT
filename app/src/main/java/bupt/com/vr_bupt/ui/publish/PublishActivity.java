package bupt.com.vr_bupt.ui.publish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import bupt.com.vr_bupt.R;

import static bupt.com.vr_bupt.R.id.top_publish_layout;

public class PublishActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView topName,add_tv,topPublish;
    private RelativeLayout top_back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        initViews();
    }

    private void initViews() {
        topName = (TextView) findViewById(R.id.top_name_text);
        topPublish = (TextView) findViewById(R.id.top_publish_tv);
        top_back_btn = (RelativeLayout) findViewById(R.id.top_back_btn);
        add_tv = (TextView) findViewById(R.id.add_tv);
        topName.setText("发布");
        topPublish.setText("发布");
        topPublish.setVisibility(View.VISIBLE);
        top_back_btn.setVisibility(View.VISIBLE);
        topPublish.setOnClickListener(this);
        top_back_btn.setOnClickListener(this);
        add_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.top_publish_tv:
                Toast.makeText(this,"发布成功",Toast.LENGTH_LONG).show();
                break;
            case R.id.add_tv:
                break;
        }
    }
}
