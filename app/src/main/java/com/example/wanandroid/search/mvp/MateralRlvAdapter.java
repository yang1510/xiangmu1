package com.example.wanandroid.search.mvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.search.bean.AlertionDataBaen;

import java.util.ArrayList;

public class MateralRlvAdapter extends RecyclerView.Adapter<MateralRlvAdapter.ViewHandler> {

    private final Context mContext;
    private final ArrayList<AlertionDataBaen.DataBean.DatasBean> mDatasBeans;
    private OnItemClickListener onItemClickListener;


    public MateralRlvAdapter(Context context, ArrayList<AlertionDataBaen.DataBean.DatasBean> datasBeans) {

        mContext = context;
        mDatasBeans = datasBeans;
    }


    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_officili2, null);
        return new ViewHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, final int position) {
        holder.textView11.setText(mDatasBeans.get(position).getAuthor());
        holder.textView12.setText(mDatasBeans.get(position).getNiceDate());
        holder.textView13.setText(mDatasBeans.get(position).getTitle());
        holder.textView14.setText(mDatasBeans.get(position).getSuperChapterName() + "/");
        holder.textView15.setText(mDatasBeans.get(position).getChapterName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDatasBeans.size();
    }

    public void setDATA(AlertionDataBaen navStricleBaen) {
        mDatasBeans.addAll(navStricleBaen.getData().getDatas());
        notifyDataSetChanged();

    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        private TextView textView11;
        private TextView textView12;
        private TextView textView13;
        private TextView textView14;
        private TextView textView15;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            textView11 = itemView.findViewById(R.id.textView11);
            textView12 = itemView.findViewById(R.id.textView12);
            textView13 = itemView.findViewById(R.id.textView13);
            textView14 = itemView.findViewById(R.id.textView14);
            textView15 = itemView.findViewById(R.id.textView15);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
