package com.example.wanandroid.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.search.bean.SearchBean;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHandler> {
   private ArrayList<SearchBean.DataBean> list;
   private Context context;

    public SearchAdapter(ArrayList<SearchBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<SearchBean.DataBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_navi_item, null);
        return new ViewHandler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, int position) {
     //   holder.mFl.removeAllViews();
        holder.mTitle.setText(list.get(position).getName());

//        for (int i = 0; i < list.size(); i++) {
//            //获取视图，视图可以自定义，可以添加自己想要的效果
//            View inflate = View.inflate(context, R.layout.layout_navi_item, null);
//            TextView tv_title = inflate.findViewById(R.id.tv_title);
//        String name = list.get(position).getName();
//        tv_title.setText(name);
//            //获取数据
//            String title = list.get(i).getName();
//            //绑定数据
//            tv_title.setText(title);
//            //加到容器中。
//            holder.mFl.addView(inflate);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        private TextView mTitle;
    //    private FlowLayout mFl;
        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.tv_title);
     //       mFl=itemView.findViewById(R.id.fll);
        }
    }
}
