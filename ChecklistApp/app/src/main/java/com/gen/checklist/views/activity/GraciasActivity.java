package com.gen.checklist.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gen.checklist.R;
import com.gen.checklist.model.Area;
import com.gen.checklist.model.RegistroToc;
import com.gen.checklist.views.activity.base.BaseActivity;
import com.gen.checklist.views.utils.SharedPrefsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraciasActivity extends BaseActivity {

    @BindView(R.id.buttonSalir)
    Button mButtonSalir;

    @BindView(R.id.textViewContador)
    TextView mTextViewContador;

    @BindView(R.id.relativeLayoutFondo)
    RelativeLayout mRelativeLayoutBackgroundTop;

    /*@BindView(R.id.bepensa)
    ImageView mImageViewLogo;*/

    String tipo = "";

    private final int DURACION_SPLASH = 8000;
    private String msj = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gracias);
        ButterKnife.bind(this);
        validateBundle(savedInstanceState);
      /*  if(msj != -1) {
            mTextViewContador.setText("¡Llevas " + msj + " " + (msj > 1 ? "levantamientos" : "levantamiento") + "!");
        }else{
            mTextViewContador.setText("¡Ya terminaste!");
        }*/
        String position = getString(R.string.position);
        this.tipo = SharedPrefsUtils.getStringPreference(getApplicationContext(), Area.TIPO);
        switch (this.tipo) {
            case Area.TIPO_ACTO_INSEGURO:
                break;
            case Area.TIPO_CONDICIONES:
                mButtonSalir.setBackgroundResource(R.drawable.btn_rounded_red);
                if(position.contentEquals("land")){
                    mRelativeLayoutBackgroundTop.setBackgroundResource(R.drawable.ic_salir_rojo_tablet);
                }else {
                    mRelativeLayoutBackgroundTop.setBackgroundResource(R.drawable.ic_salir_rojo);
                }
                break;
            case Area.TIPO_AMBIENTAL:
                if(position.contentEquals("land")){
                    mRelativeLayoutBackgroundTop.setBackgroundResource(R.drawable.ic_salir_verde_tablet);
                }else {
                    mRelativeLayoutBackgroundTop.setBackgroundResource(R.drawable.ic_salir_verde);
                }
                //mImageViewLogo.setImageResource(R.drawable.ic_bepensa2);
                mButtonSalir.setBackgroundResource(R.drawable.btn_rounded_green);
                break;
        }
        mTextViewContador.setText(msj);

        mButtonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(GraciasActivity.this, SplashActivity.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mIntent);
                finish();
            }
        });

  /*      new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mIntent = new Intent(GraciasActivity.this, LoginUserActivity.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mIntent);
                finish();
            }

            ;
        }, DURACION_SPLASH);*/
    }

    private void validateBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(RegistroToc.ID)) {
                this.msj = extras.getString(RegistroToc.ID);
            }
        }
    }
}
