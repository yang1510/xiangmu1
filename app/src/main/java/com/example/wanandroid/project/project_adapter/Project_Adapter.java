package com.example.wanandroid.project.project_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.home.bean.DatasBean;
import com.example.wanandroid.project.bean.project_bean.project_chlider_bean.Project_list_bean;

import java.util.ArrayList;


public class Project_Adapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<DatasBean> dataBean_chliders;
    private setonclick mlistener;
    private OnClick click;

    public void setClick(OnClick click) {
        this.click = click;
    }

    public interface OnClick{
        void Click(View v,int i,boolean isChbox);
    }

    public Project_Adapter(Context context, ArrayList<DatasBean> dataBean_chliders) {
        this.context = context;
        this.dataBean_chliders = dataBean_chliders;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.layout_listproject, null);
        return new project_childer(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final project_childer viewHolder1 = (project_childer) viewHolder;
        Glide.with(context).load(dataBean_chliders.get(position).getEnvelopePic()).into(viewHolder1.childer_image);
        viewHolder1.childer_title.setText(dataBean_chliders.get(position).getTitle());
        viewHolder1.childer_auther.setText(dataBean_chliders.get(position).getAuthor());
        viewHolder1.childer_content.setText(dataBean_chliders.get(position).getDesc());
        viewHolder1.childer_data.setText(dataBean_chliders.get(position).getNiceDate());
        viewHolder1.childer_img.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                click.Click(buttonView,position,isChecked);
            }
        });
//        viewHolder1.childer_img.setChecked(false);
//        viewHolder1.childer_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (viewHolder1.childer_img.isChecked()==false){
//                    DaoSession daoSession = BaseApp.getDaoSession();
//                    daoSession.delete(dataBean_chliders.get(position));
//                    notifyDataSetChanged();
//                    Toast.makeText(context,"取消收藏成功",Toast.LENGTH_LONG).show();
//                }else{
//                    DaoSession daoSession = BaseApp.getDaoSession();
//                   daoSession.insert(dataBean_chliders.get(position));
//                    Toast.makeText(context,"收藏成功",Toast.LENGTH_LONG).show();
//                }
//
//
//
//            }
//        });



        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.setOnclick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBean_chliders.size();
    }

    public void getData(Project_list_bean bean_childer) {
        dataBean_chliders.addAll(bean_childer.getData().getDatas());
        notifyDataSetChanged();
    }


    public class project_childer extends RecyclerView.ViewHolder {

        private final ImageView childer_image;
        private final TextView childer_title;
        private final TextView childer_content;
        private final TextView childer_auther;
        private final TextView childer_data;
        private final CheckBox childer_img;

        public project_childer(@NonNull View itemView) {
            super(itemView);
            childer_image = itemView.findViewById(R.id.project_childer_image);
            childer_title = itemView.findViewById(R.id.project_childer_title);
            childer_content = itemView.findViewById(R.id.project_childer_content);
            childer_auther = itemView.findViewById(R.id.project_childer_auther);
            childer_data = itemView.findViewById(R.id.project_childer_data);
            childer_img = itemView.findViewById(R.id.project_childer_img);

        }
    }

    public interface  setonclick{
        void setOnclick(View v, int position);
    }
    public void ProjectAdapter_Setonclick(setonclick mlistener){
        this.mlistener = mlistener;
    }

}
