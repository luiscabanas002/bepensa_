package com.gen.checklist.views.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gen.checklist.R;
import com.gen.checklist.model.Area;
import com.gen.checklist.views.adapter.holder.base.BaseViewHolder;
import com.gen.checklist.views.utils.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AreaHolder extends BaseViewHolder {

    @BindView(R.id.textViewNombre)
    TextView mTextViewNombre;

    @BindView(R.id.imageViewIcon)
    CircleImageView mCircleImageViewIcon;

    @BindView(R.id.imageViewIconSelected)
    ImageView mCircleImageViewIconSelected;

    @BindView(R.id.content)
    RelativeLayout mRelativeLayoutIcons;

    public interface AreaListener {
        void Seleccionar(Area item);
    }

    public AreaHolder(View itemView, OnViewHolderClickListener onViewHolderClickListener) {
        super(itemView, onViewHolderClickListener);
        ButterKnife.bind(this, itemView);
    }

    public AreaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void config(Context mContext, String tipo, final Area item, final AreaListener mAreaListener) {
        mCircleImageViewIconSelected.setVisibility(View.GONE);

        switch (tipo) {
            case Area.TIPO_ACTO_INSEGURO:
                break;
            case Area.TIPO_CONDICIONES:
                break;
            case Area.TIPO_AMBIENTAL:
             //   mCircleImageViewIconSelected.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
        }

        mTextViewNombre.setText(item.nombre);
        mCircleImageViewIconSelected.setVisibility(item.seleccionado ? View.VISIBLE : View.GONE);

        if (item.path_icono != null) {
            ImageLoader.getInstance().displayImage(ImageUtils.getUrlImage(mContext, item.path_icono), mCircleImageViewIcon, (tipo.equals(Area.TIPO_AMBIENTAL)) ? ImageUtils.mDisplayImageOptionsCheckAmbiental : ImageUtils.mDisplayImageOptionsCheck);
        }
        mRelativeLayoutIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAreaListener.Seleccionar(item);
            }
        });
    }
}
