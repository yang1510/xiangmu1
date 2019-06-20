package com.example.wanandroid.official;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.home.bean.DatasBean;

import java.nio.file.Path;
import java.util.ArrayList;

public class MyOfficialListAdapter extends RecyclerView.Adapter<MyOfficialListAdapter.ViewHandler> {
    private Context context;
    private ArrayList<DatasBean> list;
    private final ArrayList<Boolean> booleans;

    //-----------------------------
    private OnClick click;

    public void setClick(OnClick click) {
        this.click = click;
    }

    public interface OnClick {
        void Click(View v, int i, boolean isChbox);
    }
    //-----------------------------


    public MyOfficialListAdapter(Context context, ArrayList<DatasBean> list) {
        this.context = context;
        this.list = list;
        booleans = new ArrayList<>();
    }

    public void setList(ArrayList<DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_officili2, null);
        return new ViewHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, final int position) {
        holder.textView11.setText(list.get(position).getAuthor());
        holder.textView12.setText(list.get(position).getNiceDate());
        holder.textView13.setText(list.get(position).getTitle());
        holder.textView14.setText(list.get(position).getSuperChapterName() + "/");
        holder.textView15.setText(list.get(position).getChapterName());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if (onClickerList!=null) {
                  onClickerList.onClicker(position);
              }
          }
      });
        final int finalNewPosition5 = position;
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                click.Click(buttonView, finalNewPosition5, isChecked);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        private TextView textView11;
        private TextView textView12;
        private TextView textView13;
        private TextView textView14;
        private TextView textView15;
        private CheckBox checkBox;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            textView11 = itemView.findViewById(R.id.textView11);
            textView12 = itemView.findViewById(R.id.textView12);
            textView13 = itemView.findViewById(R.id.textView13);
            textView14 = itemView.findViewById(R.id.textView14);
            textView15 = itemView.findViewById(R.id.textView15);
            checkBox = itemView.findViewById(R.id.checkbox);

        }
    }

    private onClickerList onClickerList;

    public void setOnClickerList(MyOfficialListAdapter.onClickerList onClickerList) {
        this.onClickerList = onClickerList;
    }

    public interface onClickerList {
        void onClicker(int position);
    }
}
