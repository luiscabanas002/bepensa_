package com.gen.checklist.views.activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gen.checklist.R;
import com.gen.checklist.data.controller.CheckListDataController;
import com.gen.checklist.model.Area;
import com.gen.checklist.model.Divisiones;
import com.gen.checklist.model.Factores;
import com.gen.checklist.model.RegistroToc;
import com.gen.checklist.model.Sucursal;
import com.gen.checklist.model.Usuario;
import com.gen.checklist.presenter.CapturaRespuestasPresenter;
import com.gen.checklist.views.activity.base.BaseActivity;
import com.gen.checklist.views.adapter.AreaAdapter;
import com.gen.checklist.views.adapter.DividerDecoration;
import com.gen.checklist.views.adapter.FactorAdapter;
import com.gen.checklist.views.adapter.holder.AreaHolder;
import com.gen.checklist.views.adapter.holder.FactorHolder;
import com.gen.checklist.views.adapter.holder.base.BaseViewHolder;
import com.gen.checklist.views.utils.SharedPrefsUtils;
import com.gen.checklist.views.utils.Utils;
import com.gen.checklist.views.viewcontroller.CapturaRespuestasViewController;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CapturaRespuestasActivityV1 extends BaseActivity implements CapturaRespuestasViewController {

    @BindView(R.id.ButtonRegresar)
    ImageView mFloatingActionButtonRegresar;

    @BindView(R.id.ButtonSiguiente)
    ImageView mFloatingActionButtonSiguiente;

    @BindView(R.id.editTextArea)
    EditText mEditTextArea;

    @BindView(R.id.editTextFecha)
    EditText mEditTextFecha;

    @BindView(R.id.editTextMaquina)
    EditText mEditTextMaquina;

 /*   @BindView(R.id.textInputLayoutFecha)
    TextInputLayout mTextInputLayoutFecha;*/

 /*   @BindView(R.id.textInputLayoutArea)
    TextInputLayout mTextInputLayoutArea;*/

    @BindView(R.id.buttonMicrofono)
    ImageView mImageViewMicrofono;

    @BindView(R.id.recyclerViewArea)
    RecyclerView mRecyclerViewArea;

    @BindView(R.id.recyclerViewActoInseguro)
    RecyclerView mRecyclerViewcoInseguro;

    @BindView(R.id.buttonMicrofonoArea)
    ImageView mImageViewMicrofonoArea;

    @BindView(R.id.relativeLayoutFondo)
    RelativeLayout mRelativeLayoutBackgroundTop;

    @BindView(R.id.textViewNombre)
    TextView mTextViewTipo;

    @BindView(R.id.title)
    TextView mTextViewTitulo;

 /*   @BindView(R.id.textInputLayoutName)
    TextInputLayout mTextInputLayoutMaquina;*/

  /*  @BindView(R.id.bepensa)
    ImageView mImageViewLogo;*/

    private AreaAdapter mAreaAdapter;
    private FactorAdapter mFactorAdapter;
    private CapturaRespuestasPresenter mCapturaRespuestasPresenter;
    private CheckListDataController mCheckListDataController;

    List<Area> listAreasActiviy;
    List<Factores> listFactoresActiviy;

    int idSucursal = -2;
    int idArea = -1;
    int idRiesgo = -1;
    int idOrigenObservacion = 2;
    int idActoInseguro = -1;
    int idUsuario = -1;
    Sucursal mSucursal;
    Usuario mUsuario;

    String fecha = "01-01-1990";
    String tipo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_respuestas_v1);
        ButterKnife.bind(this);
        mCheckListDataController = new CheckListDataController(CapturaRespuestasActivityV1.this);
        this.tipo = SharedPrefsUtils.getStringPreference(getApplicationContext(), Area.TIPO);

        validateBundle(savedInstanceState);

        if(SharedPrefsUtils.getIntegerPreference(getApplicationContext(), Divisiones.APLICA_SUCURSAL,-1) == 1) {
            mImageViewMicrofonoArea.setVisibility(View.GONE);
        }else{
            mImageViewMicrofonoArea.setVisibility(View.VISIBLE);
        }
        String position = getString(R.string.position);
        switch (this.tipo){
            case Area.TIPO_ACTO_INSEGURO:
                mTextViewTipo.setText("Comportamiento");
                break;
            case Area.TIPO_CONDICIONES:
                mTextViewTipo.setText("Condiciones");
                mTextViewTitulo.setText("Registro de Condiciones");
                if(position.contentEquals("land")){
                    mRelativeLayoutBackgroundTop.setBackgroundResource(R.drawable.ic_fondo_rojo_tablet);
                }else {
                    mRelativeLayoutBackgroundTop.setBackgroundResource(R.drawable.ic_fondo_rojo);
                }
                break;
            case  Area.TIPO_AMBIENTAL:
                mTextViewTipo.setText("Comportamiento");
                mTextViewTitulo.setText("Registro Ambiental");
                mEditTextMaquina.setHint("Nombre de la Zona, maquinaria, equipo o unidad");
                //mEditTextMaquina.setHint("");
                if(position.contentEquals("land")){
                    mRelativeLayoutBackgroundTop.setBackgroundResource(R.drawable.ic_fondo_verde_tablet);
                }else {
                    mRelativeLayoutBackgroundTop.setBackgroundResource(R.drawable.ic_fondo_verde);
                }
                //mImageViewLogo.setImageResource(R.drawable.ic_bepensa2);
                break;
        }

        mCapturaRespuestasPresenter = new CapturaRespuestasPresenter(this, CapturaRespuestasActivityV1.this);

        mFloatingActionButtonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mEditTextFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        mOnDateSetListener,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.setMinDate(now);
                dpd.setAccentColor("#F37733");
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });


        mEditTextArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(SharedPrefsUtils.getIntegerPreference(getApplicationContext(), Divisiones.APLICA_SUCURSAL,-1) == 1) {
                     /*   Intent mIntent = new Intent(CapturaRespuestasActivityV1.this, ListaItemActivity.class);
                        Bundle parameters = new Bundle();
                        parameters.putInt(ListaItemActivity.TIPO_LISTA, ListaItemActivity.SUCURSAL);
                        mIntent.putExtras(parameters);
                        startActivityForResult(mIntent, ListaItemActivity.SUCURSAL);*/

                        List<Sucursal> mSucursals = mCheckListDataController.getSucursales();

                        List<String> mItems = new ArrayList<>();
                        for (Sucursal mItem : mSucursals) {
                            mItems.add(mItem.nombre);
                        }
                        CharSequence[] items = mItems.toArray(new CharSequence[mItems.size()]);
                        showItemsDialog("Seleccione la planta", items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Sucursal mSucursal = mSucursals.get(i);
                                idSucursal = mSucursal.id;
                                mEditTextArea.setText(mSucursal.nombre);
                            }
                        });

                    }
                } catch (Exception ex) {
                    showDialog("", "Ocurrió un error al realizar la operación, intente nuevamente", "OK");
                    ex.toString();
                }
            }
        });

        mFloatingActionButtonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateField()) {
                    RegistroToc mRegistroToc = new RegistroToc();
                    mRegistroToc.id_usuario = idUsuario;
                    mRegistroToc.id_area = idArea;
                    mRegistroToc.id_factor = idActoInseguro;
                    mRegistroToc.id_origen_observacion = idOrigenObservacion;
                    mRegistroToc.id_riesgo_identificado = idRiesgo;
                    if(SharedPrefsUtils.getIntegerPreference(getApplicationContext(), Divisiones.APLICA_SUCURSAL,-1) == 0){
                        mRegistroToc.id_sucursal = -1;
                        mRegistroToc.adicional_sucursal = mEditTextArea.getText().toString();

                    }else{
                        mRegistroToc.id_sucursal = idSucursal;
                    }
                    mRegistroToc.maquina_equipo = mEditTextMaquina.getText().toString();
                    mRegistroToc.maquina_equipo_zona = mEditTextMaquina.getText().toString();

                    Intent mIntent = new Intent(CapturaRespuestasActivityV1.this, CapturaComentariosActivity.class);
                    mIntent.putExtra(Usuario.class.getName(),mUsuario);
                    mIntent.putExtra(RegistroToc.class.getName(), mRegistroToc);
                    startActivity(mIntent);
                }
            }
        });

        mImageViewMicrofono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInput();
            }
        });

        mImageViewMicrofonoArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInputArea();
            }
        });

        mFloatingActionButtonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog("Confirmar", "¿Desea salir del cuestionario?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent mIntent = new Intent(CapturaRespuestasActivityV1.this, LoginUserActivity.class);
                        startActivity(mIntent);
                        finish();
                    }
                });
            }
        });

        mEditTextArea.setText("-Seleccione la planta-");

        mEditTextFecha.setText(Utils.getDateString());
        //mTextInputLayoutFecha.setHint("Fecha");

        mEditTextMaquina.requestFocus();


        if(mSucursal != null){
            mEditTextArea.setText(mSucursal.nombre);
            this.idSucursal = mSucursal.id;
        }
       //mTextInputLayoutFecha.setEnabled(false);
        mCapturaRespuestasPresenter.CargarVista(this.tipo);

        //getInstrucciones();

        if(SharedPrefsUtils.getIntegerPreference(getApplicationContext(), Divisiones.APLICA_SUCURSAL,-1) == 0){
            mEditTextArea.setFocusable(true);
            mEditTextArea.setFocusableInTouchMode(true);

            mEditTextArea.setText("");
            mEditTextArea.setHint("Planta");
            mEditTextArea.setCompoundDrawables(null,null,null,null);
            mEditTextArea.requestFocus();
        }else{
            mEditTextArea.setFocusable(true);
        }

        hideKeyboard();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideKeyboard();
    }

    private void validateBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Usuario.ID)) {
                this.idUsuario = extras.getInt(Usuario.ID);
            }

            if (extras != null && extras.containsKey(Usuario.class.getName())) {
                mUsuario = extras.getParcelable(Usuario.class.getName());
                //showToastMessage("Bienvenido " + mUsuario.nombre);
                showInfoDialogListener("BIENVENIDO: " + mUsuario.nombre, mCheckListDataController.Mensaje1(this.tipo), "Continuar");
                mSucursal = mCheckListDataController.getSucursal(mUsuario.idSucursal);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ListaItemActivity.SUCURSAL:
                    try {
                        this.idSucursal = data.getIntExtra("id", -1);
                        mEditTextArea.setText(data.getStringExtra("nombre"));
                    } catch (Exception ex) {
                        this.idSucursal = -2;
                        mEditTextArea.setText("-Seleccione la planta-");
                        showDialog("", "Ocurrió un error al realizar la operación, intente nuevamente", "OK");
                        ex.toString();
                    }
                    break;
                case 99:
                    try {
                        if (null != data) {
                            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                            mEditTextMaquina.setText(result.get(0));
                        }
                    } catch (Exception ex) {
                        showDialog("", "Ocurrió un error al realizar la operación, intente nuevamente", "OK");
                        ex.toString();
                    }
                    break;

                case 98:
                    try {
                        if (data != null) {
                            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                            mEditTextArea.setText(result.get(0));
                        }
                    } catch (Exception ex) {
                        showDialog("", "Ocurrió un error al realizar la operación, intente nuevamente", "OK");
                        ex.toString();
                    }
                    break;
            }
        }
    }

    private void getInstrucciones(){
  /*      showTapView("Selección de planta","Presione el control con el siguiente icono para desplegar la lista y seleccionar una planta", R.id.editTextArea,R.color.colorPrimary,android.R.color.white,android.R.color.white,android.R.color.white, ContextCompat.getDrawable(CapturaRespuestasActivityV1.this,R.drawable.ic_map_marker),new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                showTapView("Maquinaria y Equipo", "Puede capturar en caso de ser necesario la maquinaria o equipo" , R.id.editTextMaquina,R.color.colorPrimary,android.R.color.white,android.R.color.white,android.R.color.white,ContextCompat.getDrawable(CapturaRespuestasActivityV1.this,R.drawable.ic_settings_outline),new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        showTapView("Escritura por voz", "Si desea capturar la escritura por voz, presione el control con el siguiente icono" , R.id.editTextMaquina,R.color.colorPrimary,android.R.color.white,android.R.color.white,android.R.color.white,ContextCompat.getDrawable(CapturaRespuestasActivityV1.this,R.drawable.ic_microphone),new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                            @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);
                                showTapView("Seleccionar área de observación", "Para seleccionar un elemento presione alguno de los iconos que se encuentran en la sección de áreas de observación" , R.id.recyclerViewArea,R.color.colorPrimary,android.R.color.white,android.R.color.white,android.R.color.white,ContextCompat.getDrawable(CapturaRespuestasActivityV1.this,R.drawable.ic_gesture_tap),new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                                    @Override
                                    public void onTargetClick(TapTargetView view) {
                                        super.onTargetClick(view);
                                        showTapView("Seleccionar acto inseguro", "Para seleccionar un elemento presione alguno de los iconos que se encuentran en la sección de acto inseguro" , R.id.recyclerViewActoInseguro,R.color.colorPrimary,android.R.color.white,android.R.color.white,android.R.color.white,ContextCompat.getDrawable(CapturaRespuestasActivityV1.this,R.drawable.ic_gesture_tap),new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                                            @Override
                                            public void onTargetClick(TapTargetView view) {
                                                super.onTargetClick(view);
                                                showTapView("Regresar o salir", "Presione el siguiente icono para regresar o salir" , R.id.ButtonRegresar,R.color.colorPrimary,android.R.color.white,android.R.color.white,android.R.color.white,ContextCompat.getDrawable(CapturaRespuestasActivityV1.this,R.drawable.ic_arrow_left),new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                                                    @Override
                                                    public void onTargetClick(TapTargetView view) {
                                                        super.onTargetClick(view);
                                                        showTapView("Continuar", "Presione el siguiente icono para continuar" , R.id.ButtonSiguiente,R.color.colorPrimary,android.R.color.white,android.R.color.white,android.R.color.white,ContextCompat.getDrawable(CapturaRespuestasActivityV1.this,R.drawable.ic_arrow_right),new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                                                            @Override
                                                            public void onTargetClick(TapTargetView view) {
                                                                super.onTargetClick(view);

                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });*/
    }

    private void startVoiceInputArea() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "¿Cual es el nombre de la planta?");
        try {
            startActivityForResult(intent, 98);
        } catch (ActivityNotFoundException a) {

        }
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "¿Cual es el nombre de la máquina?");
        try {
            startActivityForResult(intent, 99);
        } catch (ActivityNotFoundException a) {

        }
    }

    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            fecha = "" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "/" + ((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)) + "/" + year;
            Date mDate = Utils.convertStringToDate(fecha, "00:00:00");
            mEditTextFecha.setText(Utils.getDateString(mDate));
            //mTextInputLayoutFecha.setHint("Fecha");
        }
    };


    private boolean validateField() {
        if (this.idOrigenObservacion == -1) {
            showInfoDialog("Información requerida", "Por favor seleccione el origen de la observación", "OK");
            return false;
        }

   /*     if (mEditTextMaquina.getText().toString().isEmpty()) {
            showInfoDialog("Información requerida", "Por favor indique el nombre de la maquinaria y equipo", "OK");
            return false;
        }*/

        if (this.idSucursal == -2 && SharedPrefsUtils.getIntegerPreference(getApplicationContext(), Divisiones.APLICA_SUCURSAL,-1) == 1) {
            showInfoDialog("Información requerida", "Por favor seleccione la planta", "OK");
            return false;
        }

        if (this.idArea == -1) {
            showInfoDialog("Información requerida", "Por favor seleccione el área de observación", "OK");
            return false;
        }

        if (this.idActoInseguro == -1) {
            showInfoDialog("Información requerida", "Por favor seleccione el acto inseguro", "OK");
            return false;
        }

        if(SharedPrefsUtils.getIntegerPreference(getApplicationContext(), Divisiones.APLICA_SUCURSAL,-1) == 0){
            if (mEditTextArea.getText().toString().isEmpty()) {
                showInfoDialog("Información requerida", "Por favor indique el nombre de la planta", "OK");
                mEditTextArea.requestFocus();
                return false;
            }
        }

        return true;
    }

    AreaHolder.AreaListener mAreaListener = new AreaHolder.AreaListener() {
        @Override
        public void Seleccionar(Area item) {
            idArea = item.id;
            for (Area itemArea : listAreasActiviy) {
                itemArea.seleccionado = (item.id == itemArea.id ? true : false);
            }
            mAreaAdapter.notifyDataSetChanged();
        }
    };

    FactorHolder.FactorListener mFactorListener = new FactorHolder.FactorListener() {
        @Override
        public void Seleccionar(Factores item) {
            idActoInseguro = item.id;
            for (Factores itemFactor : listFactoresActiviy) {
                itemFactor.seleccionado = (item.id == itemFactor.id ? true : false);
            }
            mFactorAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void failureWS(String message) {
        showDialog("Información", message, "OK");
    }

    @Override
    public void successWS(RegistroToc parametro) {

    }

    @Override
    public void showProgressDialog(String message) {
        showOnProgressDialog(message);
    }

    @Override
    public void dissmissProgressDialog() {
        dismissProgressDialog();
    }

    @Override
    public void cargarGrid(List<Area> listAreas, List<Factores> listFactores){
        if (listAreas != null && listAreas.size() > 0) {
            this.listAreasActiviy = listAreas;
            Collections.sort(listAreasActiviy);
            mAreaAdapter = new AreaAdapter(CapturaRespuestasActivityV1.this,this.tipo, listAreasActiviy, new BaseViewHolder.OnViewHolderClickListener() {
                @Override
                public void onViewHolderClick(View view, int position) {

                }
            },mAreaListener);

            LinearLayoutManager layoutManager = new LinearLayoutManager(CapturaRespuestasActivityV1.this, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerViewArea.setLayoutManager(layoutManager);
            mRecyclerViewArea.setHasFixedSize(true);
            mRecyclerViewArea.addItemDecoration(new DividerDecoration(CapturaRespuestasActivityV1.this));
            mRecyclerViewArea.setItemAnimator(new DefaultItemAnimator());
            mRecyclerViewArea.setAdapter(mAreaAdapter);
        } else {
            mRecyclerViewArea.setAdapter(null);
        }


        if (listFactores != null && listFactores.size() > 0) {
            this.listFactoresActiviy = listFactores;
            Collections.sort(listFactoresActiviy);
            mFactorAdapter = new FactorAdapter(CapturaRespuestasActivityV1.this,this.tipo, listFactoresActiviy, new BaseViewHolder.OnViewHolderClickListener() {
                @Override
                public void onViewHolderClick(View view, int position) {

                }
            }, mFactorListener);

            mRecyclerViewcoInseguro.setLayoutManager(new GridLayoutManager(this, 4));
            mRecyclerViewcoInseguro.setHasFixedSize(true);
            mRecyclerViewcoInseguro.setItemAnimator(new DefaultItemAnimator());
            mRecyclerViewcoInseguro.setAdapter(mFactorAdapter);
        } else {
            mRecyclerViewcoInseguro.setAdapter(null);
        }
    }
}
