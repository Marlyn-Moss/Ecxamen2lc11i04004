package com.example.ecxamen2lc11i04004;

import android.content.ContentValues;

public class ContactoModel {
    private String nombre;
    private String apellido;
    private String telefono1;
    private String telefono2;
    private int imagen;
    private int favorito;
    private int autoId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public ContactoModel() {
    }

    public ContactoModel(String nombre, String apellido, String telefono1, String telefono2, int imagen, int favorito, int autoId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.imagen = imagen;
        this.favorito = favorito;
        this.autoId = autoId;
    }

    public ContactoModel(String nombre, String apellido, String telefono1, String telefono2, int imagen, int favorito) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.imagen = imagen;
        this.favorito = favorito;
        this.autoId = 0;
    }

    public static final String NOMBRE_TABLA         = "ContactoModel";
    public static final String COLUMNA_AUTOID       = "autoId";
    public static final String COLUMNA_NOMBRES      = "nombres";
    public static final String COLUMNA_APELLIDOS    = "apellidos";
    public static final String COLUMNA_TELEFONO1    = "telefono1";
    public static final String COLUMNA_TELEFONO2    = "telefono2";
    public static final String COLUMNA_IMAGEN       = "imagenResource";
    public static final String COLUMNA_FAVORITO     = "favorito";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOMBRE_TABLA + "("
                    + COLUMNA_AUTOID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMNA_NOMBRES + " TEXT,"
                    + COLUMNA_APELLIDOS + " TEXT,"
                    + COLUMNA_TELEFONO1 + " TEXT,"
                    + COLUMNA_TELEFONO2 + " TEXT,"
                    + COLUMNA_IMAGEN + " INTEGER, "
                    + COLUMNA_FAVORITO + " INTEGER"
                    + ")";
    public static final String DELETE_REGISTROS =
            "DELETE FROM " + NOMBRE_TABLA
                    +"WHERE autoId = %s";

    public static final String SELECT_TABLA =
            "SELECT nombres,apellidos,telefono1,telefono2,imagenResource,favorito,autoId FROM "
                    + NOMBRE_TABLA
                    +" ORDER BY "
                    +COLUMNA_APELLIDOS
                    +", "
                    +COLUMNA_NOMBRES
                    +" ASC;";

    public static final String SELECT_TABLA_FAVORITOS =
            "SELECT nombres,apellidos,telefono1,telefono2,imagenResource,favorito,autoId FROM "
                    + NOMBRE_TABLA
                    +" ORDER BY "
                    +COLUMNA_FAVORITO
                    +" DESC, "
                    +COLUMNA_APELLIDOS
                    +", "
                    +COLUMNA_NOMBRES
                    +" ASC;";

    public ContentValues Valores(){
        ContentValues _valores = new ContentValues();
        _valores.put(COLUMNA_NOMBRES,nombre);
        _valores.put(COLUMNA_APELLIDOS,apellido);
        _valores.put(COLUMNA_TELEFONO1,telefono1);
        _valores.put(COLUMNA_TELEFONO2,telefono2);
        _valores.put(COLUMNA_IMAGEN, imagen);
        _valores.put(COLUMNA_FAVORITO, favorito);
        //_valores.put(COLUMNA_AUTOID,autoId);
        return _valores;
    }

    public String getFullNombre(){
        return String.format("%s, %s",apellido,nombre);
    }
}
