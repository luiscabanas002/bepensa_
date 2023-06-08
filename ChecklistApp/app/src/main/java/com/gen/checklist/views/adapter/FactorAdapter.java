package com.gen.checklist.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gen.checklist.R;
import com.gen.checklist.model.Factores;
import com.gen.checklist.views.adapter.holder.FactorHolder;
import com.gen.checklist.views.adapter.holder.base.BaseViewHolder;

import java.util.List;

public class FactorAdapter extends RecyclerView.Adapter<FactorHolder> {

    List<Factores> mFactorList;
    BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener;
    FactorHolder.FactorListener mFactorListener;
    Context mContext;
    String tipo;

    public FactorAdapter(Context mContext,String tipo, List<Factores> mFactorList, BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener, FactorHolder.FactorListener mFactorListener) {
        this.onViewHolderClickListener  = onViewHolderClickListener;
        this.mFactorList = mFactorList;
        this.mFactorListener = mFactorListener;
        this.mContext = mContext;
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public FactorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View master = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_factor, viewGroup, false);
        FactorHolder mHolder = new FactorHolder(master, onViewHolderClickListener);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FactorHolder listaItemHolder, int i) {
        Factores mArea = getItemAtPosition(i);
        listaItemHolder.config(mContext,tipo, mArea, mFactorListener);
    }

    @Override
    public int getItemCount() {
        return (mFactorList == null ? 0 : mFactorList.size());
    }

    public Factores getItemAtPosition(int position) {
        if (position >= 0 && position < mFactorList.size()) {
            return mFactorList.get(position);
        }
        return null;
    }
}
