package com.example.wwq_123.readhub.view.news.common;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wwq_123.readhub.R;
import com.example.wwq_123.readhub.db.NewsDB;
import com.example.wwq_123.readhub.db.PreferencesUtil;
import com.example.wwq_123.readhub.eventbus.Event;
import com.example.wwq_123.readhub.model.bean.CommonDataItem;
import com.example.wwq_123.readhub.umeng.UMengShare;
import com.example.wwq_123.readhub.view.WebActivity;
import com.example.wwq_123.readhub.util.TimeUtil;
import org.greenrobot.eventbus.EventBus;


public class CommonViewHolder extends RecyclerView.ViewHolder{
    private CardView cardView;
    private TextView news_title;
    private TextView news_summary;
    private TextView news_time;
    private TextView news_author;
    private ImageButton news_delete;
    private ImageButton news_collect;
    private ImageButton news_share;
    private boolean collectStatus = false;
    private NewsDB newsDB;
    private Context context;
    private PreferencesUtil util;
    public CommonViewHolder(Context context, View itemView){
        super(itemView);
        this.context = context;
        cardView = itemView.findViewById(R.id.news_common_cardview);
        news_title = itemView.findViewById(R.id.news_common_item_title);
        news_summary = itemView.findViewById(R.id.news_common_item_summary);
        news_time = itemView.findViewById(R.id.news_common_item_time);
        news_author = itemView.findViewById(R.id.news_common_item_author);
        news_delete = itemView.findViewById(R.id.news_common_item_delete);
        news_collect = itemView.findViewById(R.id.news_common_item_collect);
        news_share = itemView.findViewById(R.id.news_common_item_share);
        util = PreferencesUtil.getInstance(context);
    }

    public void onBind(CommonDataItem item){
        news_title.setText(item.getTitle());
        news_summary.setText(item.getSummary());
        news_time.setText(TimeUtil.TimeDifference(item.getPublishDate()));
        news_author.setText(item.getSiteName() +"/"+item.getAuthorName());
        cardView.setOnClickListener((v)->{
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url",item.getMobileUrl());
                context.startActivity(intent);
        });
        news_delete.setOnClickListener((v)->{
            newsDB = new NewsDB(context);
            newsDB.delete(item);
            Event.News event = new Event.News();
            event.item = item;
            EventBus.getDefault().post(event);
        });
        news_collect.setOnClickListener((v)->{
            if (!util.loginStatus()) {
                Toast.makeText(context,"请先登录",Toast.LENGTH_SHORT).show();
            }else {
                newsDB = new NewsDB(context);
                if (collectStatus){
                    Toast.makeText(context,"取消收藏",Toast.LENGTH_SHORT).show();
//                news_collect.setImageResource(R.mipmap.collect);
                    collectStatus = false;
                    newsDB.delete(item);
                }else {
                    Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
//                news_collect.setImageResource(R.mipmap.collect_press);
                    collectStatus = true;
                    newsDB.insert(item);
                }
            }

        });
        news_share.setOnClickListener((v)->{ new UMengShare(context).share(item); });
    }

    public void showDelete(){
        news_delete.setVisibility(View.VISIBLE);
        news_time.setVisibility(View.GONE);
        news_collect.setVisibility(View.GONE);
    }
}
