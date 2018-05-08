package bupt.com.vr_bupt.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import bupt.com.vr_bupt.R;

public class HomeFragment extends Fragment implements View.OnClickListener{
    protected View contentView;
    private TextView main_hot,main_attention;
    private HotFragment hotFragment = null;
    private AttentionFragment attentionFragment = null;
    private FragmentManager fragmentManager;
    private int mIndex = 0;
    private int typeid = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
            contentView = inflater.inflate(R.layout.fragment_main,container,false);
        fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        initViews(contentView);
        showFragment(transaction, R.id.main_hot);
        return contentView;
    }

    private void initViews(View contentView) {
        main_hot = (TextView) contentView.findViewById(R.id.main_hot);
        main_attention = (TextView) contentView.findViewById(R.id.main_attention);
        main_hot.setOnClickListener(this);
        main_attention.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.main_hot:
                typeid = 0;
                main_hot.setBackgroundResource(R.drawable.corners_left_selected);
                setTextColor(main_hot, true);
                main_attention.setBackgroundResource(R.drawable.corners_right_unselected);
                setTextColor(main_attention, false);
                showFragment(transaction, R.id.main_hot);
                break;
            case R.id.main_attention:
                typeid = 1;
                main_hot.setBackgroundResource(R.drawable.corners_left_unselected);
                setTextColor(main_hot, false);
                main_attention.setBackgroundResource(R.drawable.corners_right_selected);
                setTextColor(main_attention, true);
                showFragment(transaction, R.id.main_attention);
                break;
        }
    }
    private void setTextColor(TextView view, boolean isWhite) {
        view.setTextColor(getResources().getColor(isWhite ? R.color.white : R.color.black));
    }
    private void showFragment(FragmentTransaction transaction, int tag) {
        hideFragments(transaction);
        switch (tag) {
            case R.id.main_hot:
                if (hotFragment == null) {
                    hotFragment=new HotFragment();
                    transaction.add(R.id.main_container, hotFragment);
                } else {
                    transaction.show(hotFragment);
                }
                break;

            case R.id.main_attention:
                if (attentionFragment==null){
                    attentionFragment=new AttentionFragment();
                    transaction.add(R.id.main_container,attentionFragment);
                }else{
                    transaction.show(attentionFragment);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
//        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (hotFragment != null) {
            transaction.hide(hotFragment);
        }
        if (attentionFragment!=null){
            transaction.hide(attentionFragment);
        }
    }
}

