package com.example.wanandroid.system.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.system.SystemDataBean;

import java.util.List;

public class SystemRecAapter extends RecyclerView.Adapter<SystemRecAapter.ViewHoudler> {
    List<SystemDataBean.DataBean> mDataList ;
     Context context;

    public SystemRecAapter(List<SystemDataBean.DataBean> mDataList, Context context) {
        this.mDataList = mDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoudler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_system, null);
        ViewHoudler viewHoudler = new ViewHoudler(inflate);
        return viewHoudler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoudler holder, final int position) {
        List<SystemDataBean.DataBean.ChildrenBean> children = mDataList.get(position).getChildren();
        StringBuffer stringBuffer = new StringBuffer();

        for (SystemDataBean.DataBean.ChildrenBean child : children) {
            String name = child.getName();
            stringBuffer.append(name+"  ");
        }
        String name = String.valueOf(stringBuffer);
        holder.mTitlefirst.setText(mDataList.get(position).getName());
        holder.mTitlesecond.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickerList.onClicker(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHoudler extends RecyclerView.ViewHolder {

        private final TextView mTitlefirst;
        private final TextView mTitlesecond;

        public ViewHoudler(@NonNull View itemView) {
            super(itemView);

            mTitlefirst = itemView.findViewById(R.id.title_first);
            mTitlesecond = itemView.findViewById(R.id.title_second);
        }
    }

    private OnClickerList onClickerList;

    public void setOnClickerList(OnClickerList onClickerList) {
        this.onClickerList = onClickerList;
    }

    public interface OnClickerList{
        void onClicker(int position);
    }
}
