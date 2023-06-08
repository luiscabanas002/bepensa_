package com.gen.checklist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class RegistroToc implements Parcelable {
    public static final String OBJ = "ObjRegistro";
    public static final String ID = "id_registro_toc";
    public static final String ID_USUARIO = "id_usuario";
    public static final String ID_AREA = "id_area";
    public static final String ID_FACTOR = "id_factor";
    public static final String ID_ORIGEN_OBSERVACION = "id_origen_observacion";
    public static final String ID_RIESGO_IDENTIFICADO = "id_riesgo_identificado";
    public static final String OBSERVACIONES = "observaciones";
    public static final String ACCIONES_A_TOMAR = "acciones_a_tomar";
    public static final String MAQUINA_EQUIPO = "maquina_equipo";
    public static final String MAQUINA_EQUIPO_ZONA = "maquina_equipo_zona";
    public static final String FECHA_HORA = "fecha_hora_registro";
    public static final String ID_SUCURSAL = "id_sucursal";
    public static final String ADICIONAL_SUC = "adicional_suc";
    public static final String DIVISION = "id_division";
    public static final String ATENDIDO = "atendido";
    public static final String TIPO = "tipo";
    public static final String FECHA_REGISTRO = "fechaRegistro";
    public static final String TOKEN = "token";

    @SerializedName(ID)
    @DatabaseField(columnName = ID, id = true, index = true)
    public int id;

    @SerializedName(ID_USUARIO)
    @DatabaseField(columnName = ID_USUARIO)
    public int id_usuario;

    @SerializedName(ID_AREA)
    @DatabaseField(columnName = ID_AREA)
    public int id_area;

    @SerializedName(ID_FACTOR)
    @DatabaseField(columnName = ID_FACTOR)
    public int id_factor;

    @SerializedName(ID_ORIGEN_OBSERVACION)
    @DatabaseField(columnName = ID_ORIGEN_OBSERVACION)
    public int id_origen_observacion;

    @SerializedName(ID_RIESGO_IDENTIFICADO)
    @DatabaseField(columnName = ID_RIESGO_IDENTIFICADO)
    public int id_riesgo_identificado;

    @SerializedName(ID_SUCURSAL)
    @DatabaseField(columnName = ID_SUCURSAL)
    public int id_sucursal;

    @SerializedName(OBSERVACIONES)
    @DatabaseField(columnName = OBSERVACIONES)
    public String observaciones;

    @SerializedName(ACCIONES_A_TOMAR)
    @DatabaseField(columnName = ACCIONES_A_TOMAR)
    public String acciones_a_tomar;

    @SerializedName(MAQUINA_EQUIPO)
    @DatabaseField(columnName = MAQUINA_EQUIPO)
    public String maquina_equipo;

    @SerializedName(MAQUINA_EQUIPO_ZONA)
    @DatabaseField(columnName = MAQUINA_EQUIPO_ZONA)
    public String maquina_equipo_zona;

    @SerializedName(FECHA_HORA)
    @DatabaseField(columnName = FECHA_HORA)
    public String fecha_hora;

    @SerializedName(ADICIONAL_SUC)
    @DatabaseField(columnName = ADICIONAL_SUC)
    public String adicional_sucursal;

    @SerializedName(DIVISION)
    @DatabaseField(columnName = DIVISION)
    public int id_division;

    @SerializedName(ATENDIDO)
    @DatabaseField(columnName = ATENDIDO)
    public boolean atendido;

    @SerializedName(TIPO)
    @DatabaseField(columnName = TIPO)
    public String tipo;

    @SerializedName(FECHA_REGISTRO)
    @DatabaseField(columnName = FECHA_REGISTRO)
    public String fecha;

    @SerializedName(TOKEN)
    @DatabaseField(columnName = TOKEN)
    public String token;


    public RegistroToc(){

    }

    protected RegistroToc(Parcel in) {
        id = in.readInt();
        id_usuario = in.readInt();
        id_area = in.readInt();
        id_factor = in.readInt();
        id_origen_observacion = in.readInt();
        id_riesgo_identificado = in.readInt();
        id_sucursal = in.readInt();
        observaciones = in.readString();
        acciones_a_tomar = in.readString();
        maquina_equipo = in.readString();
        maquina_equipo_zona = in.readString();
        fecha_hora = in.readString();
        adicional_sucursal = in.readString();
        id_division = in.readInt();
        atendido = in.readByte() != 0;
        tipo = in.readString();
        fecha = in.readString();
        token = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_usuario);
        dest.writeInt(id_area);
        dest.writeInt(id_factor);
        dest.writeInt(id_origen_observacion);
        dest.writeInt(id_riesgo_identificado);
        dest.writeInt(id_sucursal);
        dest.writeString(observaciones);
        dest.writeString(acciones_a_tomar);
        dest.writeString(maquina_equipo);
        dest.writeString(maquina_equipo_zona);
        dest.writeString(fecha_hora);
        dest.writeString(adicional_sucursal);
        dest.writeInt(id_division);
        dest.writeByte((byte) (atendido ? 1 : 0));
        dest.writeString(tipo);
        dest.writeString(fecha);
        dest.writeString(token);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegistroToc> CREATOR = new Creator<RegistroToc>() {
        @Override
        public RegistroToc createFromParcel(Parcel in) {
            return new RegistroToc(in);
        }

        @Override
        public RegistroToc[] newArray(int size) {
            return new RegistroToc[size];
        }
    };
}
