package com.gen.checklist.views.adapter.holder.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public interface OnViewHolderClickListener {
        void onViewHolderClick(View view, int position);
    }

    public BaseViewHolder(View itemView, OnViewHolderClickListener onViewHolderClickListener) {
        super(itemView);
        mOnViewHolderClickListener = onViewHolderClickListener;
        itemView.setOnClickListener(mOnClickListener);
    }

    public BaseViewHolder(View itemView) {
        this(itemView, null);
    }

    public void setOnViewHolderClickListener(OnViewHolderClickListener onViewHolderClickListener) {
        mOnViewHolderClickListener = onViewHolderClickListener;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnViewHolderClickListener != null) {
                mOnViewHolderClickListener.onViewHolderClick(v, getPosition());
            }
        }
    };

    private OnViewHolderClickListener mOnViewHolderClickListener;
}

