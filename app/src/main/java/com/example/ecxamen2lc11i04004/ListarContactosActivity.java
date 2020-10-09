package com.example.ecxamen2lc11i04004;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ListarContactosActivity extends AppCompatActivity {

    DataBase _database;
    ContactoModel _contactoModel;
    Cursor _cursor;
    ArrayList<ContactoModel> _listaPuntaje;
    ContactoAdaptador _adapter;
    RecyclerView _recycled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contactos);

        _recycled = (RecyclerView)findViewById(R.id.recycledviewLista);
        _recycled.setHasFixedSize(true);
        _recycled.setLayoutManager(new LinearLayoutManager(this));

        _database = new DataBase(ListarContactosActivity.this);
        _cursor = _database.Select(_contactoModel.SELECT_TABLA);
        try{
            _listaPuntaje = new ArrayList<>();
            //_cursor.moveToFirst();
            while(_cursor.moveToNext()){
                _contactoModel = new ContactoModel(
                        _cursor.getString(0),
                        _cursor.getString(1),
                        _cursor.getString(2),
                        _cursor.getString(3),
                        _cursor.getInt(4),
                        _cursor.getInt(5),
                        _cursor.getInt(6)
                );
                _listaPuntaje.add(_contactoModel);
            }

            _adapter =new ContactoAdaptador(_listaPuntaje, new ContactoAdaptador.ItemClickListener() {
                @Override
                public void onItemClick(ContactoModel _puntaje) {
                    /*Toast.makeText(PuntajesActivity.this,
                            String.format("Puntaje de %s", _puntaje.get_nick()),
                            Toast.LENGTH_SHORT).show();*/
                }
            });
            _recycled.setAdapter(_adapter);

        }catch (SQLiteException _err){
            _cursor.close();
        }finally {
            _cursor.close();
        }
    }

}
