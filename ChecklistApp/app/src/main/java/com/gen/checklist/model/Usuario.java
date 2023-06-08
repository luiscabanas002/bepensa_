package com.gen.checklist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Usuario implements Parcelable {

    public static final String ID = "id_usuario";
    public static final String CLAVE = "clave";
    public static final String NOMBRE = "nombre";
    public static final String CLAVE_SUCURSAL = "clave_sucursal";
    public static final String NOMBRE_SUCURSAL = "nombre_sucursal";
    public static final String CLAVE_PUESTO = "clave_puesto";
    public static final String NOMRE_PUESTO = "nombre_puesto";
    public static final String DEPARTAMENTO = "departamento";
    public static final String PROCESO = "proceso";
    public static final String SUB_PROCESO = "sub_proceso";
    public static final String ID_ESTATUS = "id_estatus";
    public static final String ID_DIVISION = "Id_division";
    public static final String ID_SUCURSAL = "id_sucursal";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(CLAVE)
    @DatabaseField(columnName = CLAVE)
    public String clave;

    @SerializedName(NOMBRE)
    @DatabaseField(columnName = NOMBRE)
    public String nombre;

    @SerializedName(CLAVE_SUCURSAL)
    @DatabaseField(columnName = CLAVE_SUCURSAL)
    public String clave_sucursal;

    @SerializedName(NOMBRE_SUCURSAL)
    @DatabaseField(columnName = NOMBRE_SUCURSAL)
    public String nombre_sucursal;

    @SerializedName(CLAVE_PUESTO)
    @DatabaseField(columnName = CLAVE_PUESTO)
    public String clave_puesto;

    @SerializedName(NOMRE_PUESTO)
    @DatabaseField(columnName = NOMRE_PUESTO)
    public String nombre_puesto;

    @SerializedName(DEPARTAMENTO)
    @DatabaseField(columnName = DEPARTAMENTO)
    public String departamento;

    @SerializedName(PROCESO)
    @DatabaseField(columnName = PROCESO)
    public String proceso;

    @SerializedName(SUB_PROCESO)
    @DatabaseField(columnName = SUB_PROCESO)
    public String sub_proceso;

    @SerializedName(ID_ESTATUS)
    @DatabaseField(columnName = ID_ESTATUS)
    public int id_estatus;

    @SerializedName(ID_DIVISION)
    @DatabaseField(columnName = ID_DIVISION)
    public int id_division;

    @SerializedName(ID_SUCURSAL)
    @DatabaseField(columnName = ID_SUCURSAL)
    public int idSucursal;

    public Usuario() {
    }

    protected Usuario(Parcel in) {
        id = in.readInt();
        clave = in.readString();
        nombre = in.readString();
        clave_sucursal = in.readString();
        nombre_sucursal = in.readString();
        clave_puesto = in.readString();
        nombre_puesto = in.readString();
        departamento = in.readString();
        proceso = in.readString();
        sub_proceso = in.readString();
        id_estatus = in.readInt();
        id_division = in.readInt();
        idSucursal = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(clave);
        dest.writeString(nombre);
        dest.writeString(clave_sucursal);
        dest.writeString(nombre_sucursal);
        dest.writeString(clave_puesto);
        dest.writeString(nombre_puesto);
        dest.writeString(departamento);
        dest.writeString(proceso);
        dest.writeString(sub_proceso);
        dest.writeInt(id_estatus);
        dest.writeInt(id_division);
        dest.writeInt(idSucursal);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
