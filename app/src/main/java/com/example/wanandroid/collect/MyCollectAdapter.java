package com.example.wanandroid.collect;

import android.content.Context;
import android.view.LayoutInflater;
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
import com.example.wanandroid.official.MyOfficialListAdapter;

import java.util.ArrayList;

public class MyCollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList <CollectBean.DataBean.DatasBean> list;

    private MyOfficialListAdapter.OnClick click;
    private OnItemClickListener onItemClickListener;

    public void setClick(MyOfficialListAdapter.OnClick click) {
        this.click = click;
    }

    public interface OnClick{
        void Click(View v,int i,boolean isChbox);
    }

    public MyCollectAdapter(Context context, ArrayList<CollectBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<CollectBean.DataBean.DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        if (viewType ==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_item1_1, null);
            holder=new Item1ViewHandler(inflate);
        }else{
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_2_2, null);
            holder=new Item2ViewHandler(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof Item1ViewHandler){

            final Item1ViewHandler item1ViewHandler= (Item1ViewHandler) holder;
            item1ViewHandler.textView.setText(list.get(position).getAuthor());
            item1ViewHandler.textView2.setText(list.get(position).getNiceDate());
            item1ViewHandler.textView3.setText(list.get(position).getTitle());
            item1ViewHandler.textView4.setText(list.get(position).getChapterName()+"/");
            item1ViewHandler.textView9.setText(list.get(position).getChapterName());

            item1ViewHandler.imageView.setChecked(true);
            item1ViewHandler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!=null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
           item1ViewHandler.imageView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   click.Click(buttonView,position,isChecked);
               }
           });

        }
        else if (holder instanceof Item2ViewHandler){

            final Item2ViewHandler item2ViewHandler= (Item2ViewHandler) holder;
            item2ViewHandler.textView5.setText(list.get(position).getAuthor());
            item2ViewHandler.textView6.setText(list.get(position).getNiceDate());
            item2ViewHandler.textView7.setText(list.get(position).getTitle());
            item2ViewHandler.textView8.setText(list.get(position).getChapterName()+"/");
            item2ViewHandler.textView10.setText(list.get(position).getChapterName());
            Glide.with(context).load(list.get(position).getEnvelopePic()).into(item2ViewHandler.imageView2);
            item2ViewHandler.imageView.setChecked(true);
            item2ViewHandler.imageView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    click.Click(buttonView,position,isChecked);
                }
            });
            item2ViewHandler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!=null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getEnvelopePic().equals("")){
            return 1;
        }else{
            return 2;
        }
    }
    class Item1ViewHandler extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView9;
        private CheckBox imageView;


        public Item1ViewHandler(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
            textView4=itemView.findViewById(R.id.textView4);
            imageView=itemView.findViewById(R.id.checkbox1);
            textView9=itemView.findViewById(R.id.textView9);
        }
    }
    class Item2ViewHandler extends RecyclerView.ViewHolder{
        private TextView textView5;
        private TextView textView6;
        private TextView textView7;
        private TextView textView8;
        private TextView textView10;
        private CheckBox imageView;
        private ImageView imageView2;
        public Item2ViewHandler(@NonNull View itemView) {
            super(itemView);
            textView5=itemView.findViewById(R.id.textView5);
            textView6=itemView.findViewById(R.id.textView6);
            textView7=itemView.findViewById(R.id.textView7);
            textView8=itemView.findViewById(R.id.textView8);
            textView10=itemView.findViewById(R.id.textView10);
            imageView=itemView.findViewById(R.id.checkbox2);
            imageView2=itemView.findViewById(R.id.imageView2);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(  int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
