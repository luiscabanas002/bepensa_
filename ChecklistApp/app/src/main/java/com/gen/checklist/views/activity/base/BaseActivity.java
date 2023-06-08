package com.gen.checklist.views.activity.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gen.checklist.R;
import com.gen.checklist.views.utils.Utils;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseActivity extends AppCompatActivity {

    private Dialog mProgressDialogCustom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String position = getString(R.string.position);
        try {
            if (position.contentEquals("land")) {
                // if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                //  }
            } else {
                //  if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                //  }
            }
        }catch (Exception ex){

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideNavigations(this);
    }

    public interface OnFinishInputDialog{
        void onFinish(String text);
    }

    public void configureViewEmpty(int idIcon, int icon){
        ImageView mImageViewIconEmpty = (ImageView) findViewById(idIcon);
        if(mImageViewIconEmpty != null) {
            mImageViewIconEmpty.setImageResource(icon);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideKeyboard();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideKeyboard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideKeyboard();
    }

    AlertDialog alert = null;
    public void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Habilidar servicios de ubicación")
                .setCancelable(false)
                .setPositiveButton("Habilitar", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }).setCancelable(false);

        alert = builder.create();
        alert.show();
    }

    public void showConfirmDialog(String title, String msj , DialogInterface.OnClickListener mOnClickListenerYes){
        AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(BaseActivity.this);
        dialogConfirm.setTitle(title);
        dialogConfirm.setMessage(msj);
        dialogConfirm.setPositiveButton("Si", mOnClickListenerYes);
        dialogConfirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.cancel();
            }
        });
        dialogConfirm.show();
    }

    public void showConfirmDialog(String title, String msj , DialogInterface.OnClickListener mOnClickListenerYes, DialogInterface.OnClickListener mOnClickListenerNo){
        AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(BaseActivity.this);
        dialogConfirm.setTitle(title);
        dialogConfirm.setMessage(msj);
        dialogConfirm.setCancelable(false);
        dialogConfirm.setPositiveButton("Si", mOnClickListenerYes);
        dialogConfirm.setNegativeButton("No", mOnClickListenerNo);
        dialogConfirm.show();
    }

    public void showConfirmDialog(String title, String msj, String button1, String button2 , DialogInterface.OnClickListener mOnClickListenerYes, DialogInterface.OnClickListener mOnClickListenerNo){
        AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(BaseActivity.this);
        dialogConfirm.setTitle(title);
        dialogConfirm.setMessage(msj);
        dialogConfirm.setCancelable(false);
        dialogConfirm.setPositiveButton(button1, mOnClickListenerYes);
        dialogConfirm.setNegativeButton(button2, mOnClickListenerNo);
        dialogConfirm.show();
    }

    public void showInfoDialog(String title, String msj, String button){
        AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(BaseActivity.this);
        dialogConfirm.setTitle(title);
        dialogConfirm.setMessage(msj);
        dialogConfirm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialogConfirm.show();
    }

    public void showInfoDialog(String title, String msj, String button, DialogInterface.OnClickListener mOnClickListener){
        AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(BaseActivity.this);
        dialogConfirm.setTitle(title);
        dialogConfirm.setMessage(msj);
        dialogConfirm.setPositiveButton(button, mOnClickListener);
        dialogConfirm.setCancelable(false);
        dialogConfirm.show();
    }

    public void showInfoDialogListener(String title, String msj, String button){
        AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(BaseActivity.this);
        dialogConfirm.setTitle(title);
        dialogConfirm.setMessage(msj);
        dialogConfirm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialogConfirm.show();
    }

    public void showInputDialog(String title, String textHint, String textButton, String textEdit, final OnFinishInputDialog mOnFinishInputDialog){

        AlertDialog.Builder dialogInput = new AlertDialog.Builder(BaseActivity.this);
        dialogInput.setTitle(title);
        final EditText input = new EditText(BaseActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint(textHint);
        input.setFocusableInTouchMode(true);
        input.setTextColor(getResources().getColor(R.color.colorPrimary));
        input.setText(textEdit);
        dialogInput.setView(input);

        dialogInput.setPositiveButton(textButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mOnFinishInputDialog != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                    mOnFinishInputDialog.onFinish(input.getText().toString());
                }
            }
        });

        dialogInput.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                dialog.cancel();
            }
        });

        dialogInput.create();
        dialogInput.show();
    }

    public void showInputDialog(String title, String textHint, String textButton, String textEdit, final OnFinishInputDialog mOnFinishInputDialog, boolean showCancel){
        try {
            AlertDialog.Builder dialogInput = new AlertDialog.Builder(BaseActivity.this);
            dialogInput.setTitle(title);
            dialogInput.setCancelable(false);
            final EditText input = new EditText(BaseActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setHint(textHint);
            input.setFocusableInTouchMode(true);
            input.setTextColor(getResources().getColor(R.color.colorPrimary));
            input.setText(textEdit);
            input.setMaxLines(4);

            int maxLength = 140;
            InputFilter[] fArray = new InputFilter[1];
            fArray[0] = new InputFilter.LengthFilter(maxLength);
            input.setFilters(fArray);

            input.setSingleLine(false);
            input.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(250);
            input.setFilters(FilterArray);

            dialogInput.setView(input);

            dialogInput.setPositiveButton(textButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (mOnFinishInputDialog != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                        mOnFinishInputDialog.onFinish(input.getText().toString());
                    }
                }
            });
            if (showCancel) {
                dialogInput.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                        dialog.cancel();
                    }
                });
            }
            dialogInput.create();
            dialogInput.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showInputDialogNumber(String title, String textHint, String textButton, String textEdit, final OnFinishInputDialog mOnFinishInputDialog, boolean showCancel){

        AlertDialog.Builder dialogInput = new AlertDialog.Builder(BaseActivity.this);
        dialogInput.setTitle(title);
        dialogInput.setCancelable(false);
        final EditText input = new EditText(BaseActivity.this);
        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.setHint(textHint);
        input.setFocusableInTouchMode(true);
        input.setTextColor(getResources().getColor(R.color.colorPrimary));
        input.setText(textEdit);
        input.setWidth(50);
        input.setSingleLine(true);
        input.setImeOptions(EditorInfo.IME_ACTION_DONE);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(250);
        input.setFilters(FilterArray);

        int maxLength = 20;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        input.setFilters(fArray);

        input.setSelection(input.getText().length());
        dialogInput.setView(input);

        dialogInput.setPositiveButton(textButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mOnFinishInputDialog != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                    mOnFinishInputDialog.onFinish(input.getText().toString());
                }
            }
        });
        if(showCancel) {
            dialogInput.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                    dialog.cancel();
                }
            });
        }
        dialogInput.create();
        dialogInput.show();
    }

    public void showItemsDialog(String title, CharSequence[] items, DialogInterface.OnClickListener mOnClickListener){
        AlertDialog.Builder dialogInput = new AlertDialog.Builder(BaseActivity.this);
        dialogInput.setTitle(title);
        dialogInput.setItems(items, mOnClickListener);
        dialogInput.create();
        dialogInput.show();
    }

  //  private GifLoadingView mGifLoadingView;
    public void showOnProgressDialog(String message){
        try {
            if (mProgressDialogCustom != null) {
                mProgressDialogCustom.dismiss();
            }
            mProgressDialogCustom = new Dialog(BaseActivity.this);
            mProgressDialogCustom.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialogCustom.setContentView(R.layout.progress_dialog);
            TextView progressText = (TextView) mProgressDialogCustom.findViewById(R.id.progress_text);
            progressText.setText(message);

            mProgressDialogCustom.setCancelable(false);
            mProgressDialogCustom.setCanceledOnTouchOutside(false);
            mProgressDialogCustom.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dismissProgressDialog(){
        try {
            if (mProgressDialogCustom != null) {
                mProgressDialogCustom.dismiss();
            }
           /*  if (mGifLoadingView != null) {
                mGifLoadingView.dismiss();
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showToastMessage(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_SHORT).show();
    }

    public void showToastLongMessage(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG).show();
    }

    public void showSnackbar(String message){
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }

    public void showDialog(String title, String msj, String buttonClose){
        AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(BaseActivity.this);
        dialogConfirm.setTitle(title);
        dialogConfirm.setMessage(msj);
        dialogConfirm.setNegativeButton(buttonClose, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.cancel();
            }
        });
        dialogConfirm.show();
    }

    public void showDialog(String title, String msj, String buttonClose, DialogInterface.OnClickListener mOnClickListener){
        AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(BaseActivity.this);
        dialogConfirm.setTitle(title);
        dialogConfirm.setMessage(msj);
        dialogConfirm.setNegativeButton(buttonClose, mOnClickListener);
        dialogConfirm.show();
    }

    public void showMessage(String title, String message, String button){
        Toast.makeText(BaseActivity.this,message, Toast.LENGTH_LONG).show();
    }

    public Boolean checkWifi() {
        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean checkDatasMovil(){
        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public <T> boolean isListEmpty(List<T> list){
        if (list == null || list.size() == 0)
            return true;
        else
            return false;
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setFontButton(Button button, String fontPath){
        Typeface mTypeface = Typeface.createFromAsset(getAssets(), fontPath);
        button.setTypeface(mTypeface);
    }

    public void setFontEditText(EditText editText, String fontPath){
        Typeface mTypeface = Typeface.createFromAsset(getAssets(), fontPath);
        editText.setTypeface(mTypeface);
    }


    //Validaciones
    public boolean isEmptyEditText(EditText mEditText){

        boolean empty = false;
        String text = mEditText.getText().toString();
        if(TextUtils.isEmpty(text)){
            empty = true;
        }

        return empty;
    }

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValidEmail(String email) {

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    //endregion
    private ProgressDialog mProgressDialog;
    private ConnectivityManager mConnectivityManager;


    public void showTapView(String title, String description, int idControl, int colorCircle, int targetCircleColor, int titleTextColor, int descriptionTextColor, Drawable icon, TapTargetView.Listener clickListener){
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(idControl), title, description)
                        // All options below are optional
                        .outerCircleColor(colorCircle)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(targetCircleColor)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(titleTextColor)      // Specify the color of the title text
                        .descriptionTextSize(15)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(descriptionTextColor)  // Specify the color of the description text
                        .textColor(titleTextColor)            // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(android.R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                        .icon(icon)                     // Specify a custom drawable to draw as the target
                        .targetRadius(60),                  // Specify the target radius (in dp)
                         clickListener);
    }
}
