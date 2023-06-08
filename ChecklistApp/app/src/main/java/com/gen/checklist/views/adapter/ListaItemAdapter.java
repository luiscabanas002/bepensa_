package com.gen.checklist.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gen.checklist.R;
import com.gen.checklist.model.ViewModelItem;
import com.gen.checklist.views.adapter.holder.ListaItemHolder;
import com.gen.checklist.views.adapter.holder.base.BaseViewHolder;

import java.util.List;

public class ListaItemAdapter extends RecyclerView.Adapter<ListaItemHolder> {

    List<ViewModelItem> mViewModelItemList;
    BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener;

    public ListaItemAdapter(List<ViewModelItem> mViewModelItemList, BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener) {
        this.onViewHolderClickListener  = onViewHolderClickListener;
        this.mViewModelItemList = mViewModelItemList;
    }

    @NonNull
    @Override
    public ListaItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View master = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        ListaItemHolder mHolder = new ListaItemHolder(master, onViewHolderClickListener);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaItemHolder listaItemHolder, int i) {
        ViewModelItem mViewModelItem = getItemAtPosition(i);
        listaItemHolder.config(mViewModelItem);
    }

    @Override
    public int getItemCount() {
        return (mViewModelItemList == null ? 0 : mViewModelItemList.size());
    }

    public ViewModelItem getItemAtPosition(int position) {
        if (position >= 0 && position < mViewModelItemList.size()) {
            return mViewModelItemList.get(position);
        }
        return null;
    }
}
