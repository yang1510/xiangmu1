package com.example.wanandroid.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.home.bean.BannerBean;
import com.example.wanandroid.home.bean.DaBean;
import com.example.wanandroid.home.bean.DatasBean;
import com.example.wanandroid.official.MyOfficialListAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MyHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<BannerBean.DataBean> bannerList;
    private ArrayList<DatasBean> list;
    private int newposition;
    private int newposition2;
    private final ArrayList<Boolean> booleans;

    private MyOfficialListAdapter.OnClick click;

    public void setClick(MyOfficialListAdapter.OnClick click) {
        this.click = click;
    }

    public interface OnClick {
        void Click(View v, int i, boolean isChbox);
    }

    public MyHomeAdapter(Context context, ArrayList<BannerBean.DataBean> bannerList, ArrayList<DatasBean> list) {
        this.context = context;
        this.bannerList = bannerList;
        this.list = list;
        booleans = new ArrayList<>();
    }

    public void setBannerList(ArrayList<BannerBean.DataBean> bannerList) {
        this.bannerList = bannerList;
    }

    public void setList(ArrayList<DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_banner, null);
            holder = new BannerViewHandler(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_item1_1, null);
            holder = new Item1ViewHandler(inflate);
        } else if (viewType == 3) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_2_2, null);
            holder = new Item2ViewHandler(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1) {
            BannerViewHandler bannerViewHandler = (BannerViewHandler) holder;
            ArrayList<String> title = new ArrayList<>();
            for (int i = 0; i < bannerList.size(); i++) {
                title.add(bannerList.get(i).getTitle());
            }
            bannerViewHandler.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            bannerViewHandler.banner.setBannerTitles(title);
            bannerViewHandler.banner.setImages(bannerList);
            bannerViewHandler.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerBean.DataBean bannerBean = (BannerBean.DataBean) path;
                    Glide.with(context).load(bannerBean.getImagePath()).into(imageView);
                }
            }).start();
            bannerViewHandler.banner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("web", bannerList.get(position).getUrl());
                    context.startActivity(intent);
                }
            });

        }
        int newPosition = 0;
        if (bannerList.size() > 0)
            newPosition = position - 1;

        if (itemViewType == 2) {
            final Item1ViewHandler item1ViewHandler = (Item1ViewHandler) holder;
            final Boolean aBoolean = booleans.get(newPosition);
            if (aBoolean) {
                item1ViewHandler.imageView.setChecked(true);
            } else {
                item1ViewHandler.imageView.setChecked(false);
            }
            item1ViewHandler.textView.setText(list.get(newPosition).getAuthor());
            item1ViewHandler.textView2.setText(list.get(newPosition).getNiceDate());
            item1ViewHandler.textView3.setText(list.get(newPosition).getTitle());
            item1ViewHandler.textView4.setText(list.get(newPosition).getSuperChapterName() + "/");
            item1ViewHandler.textView9.setText(list.get(newPosition).getChapterName());
            final int finalNewPosition5 = newPosition;
            /*================================================================================================================*/
            String superChapterName = list.get(newPosition).getSuperChapterName();
            if (position==1) {
                item1ViewHandler.mZhiding.setVisibility(View.VISIBLE);
            }else {
                item1ViewHandler.mZhiding.setVisibility(View.GONE);
            }
            /*
             * 判断是否为最新数据*/
            boolean fresh = list.get(newPosition).getFresh();
            if (fresh) {
                item1ViewHandler.mNews.setVisibility(View.VISIBLE);
            } else {
                item1ViewHandler.mNews.setVisibility(View.GONE);
            }
            /*================================================================================================================*/

            item1ViewHandler.imageView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    click.Click(buttonView, finalNewPosition5, isChecked);
                }
            });


        } else if (itemViewType == 3) {
            final Item2ViewHandler item2ViewHandler = (Item2ViewHandler) holder;
            final Boolean aBoolean = booleans.get(newPosition);
            /*================================================================================================================*/

            if (aBoolean) {
                item2ViewHandler.imageView.setChecked(true);
            } else {
                item2ViewHandler.imageView.setChecked(false);
            }
            item2ViewHandler.textView5.setText(list.get(newPosition).getAuthor());
            item2ViewHandler.textView6.setText(list.get(newPosition).getNiceDate());
            item2ViewHandler.textView7.setText(list.get(newPosition).getTitle());
            item2ViewHandler.textView8.setText(list.get(newPosition).getSuperChapterName() + "/");
            item2ViewHandler.textView10.setText(list.get(newPosition).getChapterName());
            Glide.with(context).load(list.get(newPosition).getEnvelopePic()).into(item2ViewHandler.imageView2);
            /*================================================================================================================*/

            boolean fresh = list.get(newPosition).getFresh();

            item2ViewHandler.mZhiding.setVisibility(View.GONE);

            String superChapterName = list.get(newPosition).getSuperChapterName();
            /*
            * 判断是否为最新数据*/
            if (fresh) {
                item2ViewHandler.mNews.setVisibility(View.VISIBLE);
            } else {
                item2ViewHandler.mNews.setVisibility(View.GONE);
            }

            /*================================================================================================================*/

            final int finalNewPosition4 = newPosition;
            item2ViewHandler.imageView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    click.Click(buttonView, finalNewPosition4, isChecked);
                }
            });
            /*================================================================================================================*/

            if (newPosition == 0) {
                item2ViewHandler.mZhiding.setVisibility(View.VISIBLE);
            } else {
                item2ViewHandler.mZhiding.setVisibility(View.GONE);
            }
            /*================================================================================================================*/
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickerList.onChlicker(newposition);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bannerList.size() > 0) {
            return list.size() + 1;
        } else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (bannerList.size() > 0 && position == 0) {
            return 1;
        }

        if (bannerList.size() > 0) {
            newposition2 = position - 1;
        }
        if (list.get(newposition2).getEnvelopePic().equals("")) {
            return 2;
        } else {
            return 3;
        }

    }

    public void add(DaBean bean) {
        list.addAll(bean.getData().getDatas());
        for (int i = 0; i < bean.getData().getDatas().size(); i++) {
            booleans.add(false);
        }
        notifyDataSetChanged();
    }

    class BannerViewHandler extends RecyclerView.ViewHolder {
        private Banner banner;

        //private TextView banner_text;
        public BannerViewHandler(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            //   banner_text=itemView.findViewById(R.id.banner_text);
        }
    }

    class Item1ViewHandler extends RecyclerView.ViewHolder {
        TextView mZhiding;
        TextView mNews;


        private TextView textView;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView9;
        private CheckBox imageView;


        public Item1ViewHandler(@NonNull View itemView) {
            super(itemView);
            this.mZhiding = itemView.findViewById(R.id.zhiding);
            this.mNews = itemView.findViewById(R.id.news);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            imageView = itemView.findViewById(R.id.checkbox1);
            textView9 = itemView.findViewById(R.id.textView9);
        }
    }

    class Item2ViewHandler extends RecyclerView.ViewHolder {
        TextView mZhiding;
        TextView mNews;


        private TextView textView5;
        private TextView textView6;
        private TextView textView7;
        private TextView textView8;
        private TextView textView10;
        private CheckBox imageView;
        private ImageView imageView2;

        public Item2ViewHandler(@NonNull View itemView) {
            super(itemView);
            this.mZhiding = itemView.findViewById(R.id.zhiding);
            this.mNews = itemView.findViewById(R.id.news);

            textView5 = itemView.findViewById(R.id.textView5);
            textView6 = itemView.findViewById(R.id.textView6);
            textView7 = itemView.findViewById(R.id.textView7);
            textView8 = itemView.findViewById(R.id.textView8);
            textView10 = itemView.findViewById(R.id.textView10);
            imageView = itemView.findViewById(R.id.checkbox2);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }
    }

    private OnClickerList onClickerList;

    public void setOnClickerList(OnClickerList onClickerList) {
        this.onClickerList = onClickerList;
    }

    public interface OnClickerList {
        void onChlicker(int position);
    }

    public void setOnBannerClickerList(OnBannerClickerList onBannerClickerList) {
        this.onBannerClickerList = onBannerClickerList;
    }

    private OnBannerClickerList onBannerClickerList;


    public interface OnBannerClickerList {
        void onBanner(int position);
    }


}
