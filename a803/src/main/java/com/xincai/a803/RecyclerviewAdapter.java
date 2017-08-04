package com.xincai.a803;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luo on 2017/8/3.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    private List<String> list;
    private Context mContext;
    private LayoutInflater inflater;

    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public RecyclerviewAdapter(Context context, List<String> list) {
        this.list = list;
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(itemView, getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemLongClickListener != null) {
                        itemLongClickListener.onItemLongClick(new MyViewHolder(itemView), getAdapterPosition());
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 添加item
     *
     * @param position
     */
    public void addDate(int position) {
        list.add(position, "AAA");
        notifyItemInserted(position);
    }

    /**
     * 删除item
     *
     * @param position
     */
    public void deleteDate(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

}
