package com.example.wanandroid.navi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.home.WebActivity;
import com.example.wanandroid.navi.bean.NaviBean;
import com.example.wanandroid.util.FlowLayout;
import com.example.wanandroid.widget.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class MyNaviAdapter extends RecyclerView.Adapter<MyNaviAdapter.ViewHandler> {
    private Context context;
    private ArrayList<NaviBean.DataBean> list;

    public MyNaviAdapter(Context context, ArrayList<NaviBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<NaviBean.DataBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_navi_rl, null);
        return new ViewHandler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, final int position) {
        holder.mFl.removeAllViews();
        NaviBean.DataBean dataBean = list.get(position);
        final List<NaviBean.DataBean.ArticlesBean> articles = dataBean.getArticles();
        for (int i = 0; i < articles.size(); i++) {
            //获取视图，视图可以自定义，可以添加自己想要的效果
            View inflate = View.inflate(context, R.layout.layout_navi_item, null);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            //获取数据
            String title = articles.get(i).getTitle();
            //绑定数据
            tv_title.setText(title);
            //加到容器中。
            setTextColor(title, tv_title, i);
            holder.mFl.addView(inflate);
            final int i1 = i;
            final int finalI = i;
            tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  onClickerList.onClicker(position,i1);
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("web",articles.get(finalI).getLink());
                    context.startActivity(intent);
                }
            });

        }
        int index=0;
        for (int j=index;j<articles.size();j++){
            String chapterName = articles.get(j).getChapterName();
            holder.mTitle.setText(chapterName);
            index++;
            return;
        }
    }
    private void setTextColor(String title, TextView label, int i) {
        String s = label.getText().toString();
        if (title.length() > 0 && title.length() < 3) {
            label.setTextColor(UIUtils.getColor(R.color.Purple));
        } else if (title.length() >= 3 && title.length() < 6) {
            label.setTextColor(UIUtils.getColor(R.color.colorPrimaryDark));
        } else if (title.length() >= 6 && title.length() < 9) {
            label.setTextColor(UIUtils.getColor(R.color.Red));
        } else if (title.length() >= 9 && title.length() < 12) {
            label.setTextColor(UIUtils.getColor(R.color.Blue));
        } else if (title.length() >= 12) {
            label.setTextColor(UIUtils.getColor(R.color.Green));
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
       private TextView mTitle;
       private FlowLayout mFl;
        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.navi_title);
            mFl=itemView.findViewById(R.id.fll);
        }
    }
    private OnClickerList onClickerList;

    public void setOnClickerList(OnClickerList onClickerList) {
        this.onClickerList = onClickerList;
    }

    public interface OnClickerList{
        void onClicker(int position,int newPosition);
    }
    public  interface OnClicker{
        void onClikcer(int position);
    }
}
