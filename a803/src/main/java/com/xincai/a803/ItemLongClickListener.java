package com.xincai.a803;

import android.support.v7.widget.RecyclerView;

/**
 * 长按监听
 * Created by luo on 2017/8/4.
 */

public interface ItemLongClickListener {
    void onItemLongClick(RecyclerView.ViewHolder view, int position);
}
