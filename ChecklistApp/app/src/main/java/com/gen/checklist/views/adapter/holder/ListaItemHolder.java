package com.gen.checklist.views.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.gen.checklist.R;
import com.gen.checklist.model.ViewModelItem;
import com.gen.checklist.views.adapter.holder.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaItemHolder extends BaseViewHolder {

    @BindView(R.id.textViewItem)
    TextView mTextViewItem;

    public ListaItemHolder(View itemView, OnViewHolderClickListener onViewHolderClickListener) {
        super(itemView, onViewHolderClickListener);
        ButterKnife.bind(this, itemView);
    }

    public ListaItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void config(ViewModelItem item){
        mTextViewItem.setText(item.nombre);
    }
}
