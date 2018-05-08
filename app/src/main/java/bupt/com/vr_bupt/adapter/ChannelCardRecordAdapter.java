package bupt.com.vr_bupt.adapter;

import android.content.Context;
import android.view.ViewGroup;

import bupt.com.vr_bupt.bean.ChannelBean;
import bupt.com.vr_bupt.bean.CommonBean;
import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;

public  class ChannelCardRecordAdapter extends RecyclerAdapter<ChannelBean> {
    public Context context;

    public ChannelCardRecordAdapter(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public BaseViewHolder<ChannelBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ChannelCardRecordHolder(parent,context);
    }
}