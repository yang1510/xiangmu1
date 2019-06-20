package com.example.wanandroid.system.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.system.System_Clild;

import java.util.ArrayList;

public class SystemCliledAdapter extends RecyclerView.Adapter<SystemCliledAdapter.ViewHandler> {
   private Context context;
   private ArrayList<System_Clild.DataBean.DatasBean> list;

    public SystemCliledAdapter(Context context, ArrayList<System_Clild.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<System_Clild.DataBean.DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_system_clilid_item, null);
        return new ViewHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, final int position) {
        holder.textView.setText(list.get(position).getAuthor());
        holder.textView2.setText(list.get(position).getNiceDate());
        holder.textView3.setText(list.get(position).getTitle());
        holder.textView4.setText(list.get(position).getSuperChapterName());
        holder.textView9.setText(list.get(position).getChapterName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickerList.onClicker(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView9;
        private ImageView imageView;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
            textView4=itemView.findViewById(R.id.textView4);
            textView9=itemView.findViewById(R.id.textView9);
            imageView=itemView.findViewById(R.id.imageView);
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
