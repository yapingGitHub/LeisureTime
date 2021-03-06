package com.yaping.leisureTime.duanzia.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhouwei.library.CustomPopWindow;
import com.yaping.leisureTime.MainActivity;
import com.yaping.leisureTime.R;
import com.yaping.leisureTime.duanzia.Bean.DuanziBean;
import com.yaping.leisureTime.recyclerViewUtil.OnItemLongClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 功能：段子界面的 Adapter
 *
 * @创建： Created by yaping on 2017/11/1 0001.
 */

public class DuanziAdapter extends RecyclerView.Adapter<DuanziAdapter.ViewHolder> {

    private Fragment mFragment;
    private List<DuanziBean> mDuanziList;
    private Context mContext;
    public DuanziAdapter(Context context,Fragment fragment, List<DuanziBean> duanziBeanList) {
        this.mFragment = fragment;
        this.mDuanziList = duanziBeanList;
        this.mContext = context;
    }

    int x;
    //创建item，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_duanzhi, null);
        Log.e("onCreateViewHolder:---", "加载item" );
        return new ViewHolder(view);
    }

    //将数据与界面item进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        DuanziBean duanzi = mDuanziList.get(position);
        Log.e("绑定数据到:---", "item"+position);
        if (duanzi.getGroupBean()!=null){
            Log.e("绑定数据到:---", "item"+position+"头像");
            Glide.with(mFragment).load(duanzi.getGroupBean().getUser().getAvatar_url()).into(holder.mCivAvatar);
            if (duanzi.getGroupBean().getUser().getName()!=null){
                Log.e("绑定数据到:---", "item"+position+"name");
                holder.mTvAuthor.setText(duanzi.getGroupBean().getUser().getName());
                if (duanzi.getGroupBean().getContent()!=null){
                    Log.e("绑定数据到:---", "item"+position+"content");
                    holder.mTvContent.setText(duanzi.getGroupBean().getContent());
                }
            }
        }
        else {
            Log.e("绑定数据到item:---", "item是 NULL,内容用默认的");
        }
        //判断数据是否为null
////        if (!Check.isEmpty(duanzi.getGroupBean().getUser().getAvatar_url())) {
//            Glide.with(mFragment).load(duanzi.getGroupBean().getUser().getAvatar_url()).into(holder.mCivAvatar);
////        }
//        holder.mTvAuthor.setText(duanzi.getGroupBean().getUser().getName());
//        holder.mTvContent.setText(duanzi.getGroupBean().getContent());
        Log.e("onBindViewHolder:------", "holder.mTvContent.setText(" );

        holder.mTvContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getLayoutPosition();
                mOnItemLongClickListener.OnItemLongClick(view,position);
                return false;
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mDuanziList.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mCivAvatar;
        private TextView mTvAuthor;
        private TextView mTvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mCivAvatar = (CircleImageView) itemView.findViewById(R.id.duanzi_civ_avatar);
            mTvAuthor = (TextView) itemView.findViewById(R.id.duanzi_tv_author);
            mTvContent = (TextView) itemView.findViewById(R.id.duanzi_tv_content);
        }
    }

    private OnItemLongClickListener mOnItemLongClickListener ;
    public void setmOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

}
