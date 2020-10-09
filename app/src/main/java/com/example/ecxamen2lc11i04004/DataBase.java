package com.example.ecxamen2lc11i04004;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {
    public static final int _DATABASE_VERSION = 1;
    public static final String _DATABASE_NAME = "examen2db.db";
    Context _contexto;
    SQLiteDatabase _dbw;
    SQLiteDatabase _dbr;

    public DataBase(Context context) {
        super(context, _DATABASE_NAME, null, _DATABASE_VERSION);
        _contexto = context;
        _dbw = getWritableDatabase();
        _dbr = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactoModel.NOMBRE_TABLA);
        db.execSQL(ContactoModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ContactoModel.NOMBRE_TABLA);
            db.execSQL(ContactoModel.CREATE_TABLE);
        }
    }

    public long Insertar(String _tabla, ContentValues _valores){
        long _id =0;
        try{
            _id = _dbw.insert(_tabla,null,_valores);
        }catch (SQLException _err){
            Log.d("Error",_err.getMessage());
        }
        return _id;
    }

    public Cursor Select(String _sql){
        Cursor _cur = _dbr.rawQuery(_sql, null);
        return _cur;
    }

    public int Eliminar(String _tabla, String _where, String[] _criterioWhere){
        return (int) _dbw.delete(_tabla, _where, _criterioWhere);
    }
}
