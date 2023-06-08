package com.gen.checklist.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gen.checklist.R;
import com.gen.checklist.model.Area;
import com.gen.checklist.views.adapter.holder.AreaHolder;
import com.gen.checklist.views.adapter.holder.base.BaseViewHolder;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaHolder> {

    List<Area> mAreaList;
    BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener;
    AreaHolder.AreaListener mAreaListener;
    Context mContext;
    String tipo;

    public AreaAdapter(Context mContext,String tipo, List<Area> mAreaList, BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener, AreaHolder.AreaListener mAreaListener) {
        this.onViewHolderClickListener  = onViewHolderClickListener;
        this.mAreaList = mAreaList;
        this.mAreaListener = mAreaListener;
        this.mContext = mContext;
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public AreaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View master = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_area, viewGroup, false);
        AreaHolder mHolder = new AreaHolder(master, onViewHolderClickListener);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AreaHolder listaItemHolder, int i) {
        Area mArea = getItemAtPosition(i);
        listaItemHolder.config(mContext,tipo, mArea, mAreaListener);
    }

    @Override
    public int getItemCount() {
        return (mAreaList == null ? 0 : mAreaList.size());
    }

    public Area getItemAtPosition(int position) {
        if (position >= 0 && position < mAreaList.size()) {
            return mAreaList.get(position);
        }
        return null;
    }
}
