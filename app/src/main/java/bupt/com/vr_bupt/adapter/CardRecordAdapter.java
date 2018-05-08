package bupt.com.vr_bupt.adapter;

import android.content.Context;
import android.view.ViewGroup;

import bupt.com.vr_bupt.bean.CommonBean;
import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

public  class CardRecordAdapter extends RecyclerAdapter<CommonBean> {
    public Context context;

    public CardRecordAdapter(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public BaseViewHolder<CommonBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new CardRecordHolder(parent,context);
    }
}