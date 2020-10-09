package com.example.ecxamen2lc11i04004;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button _buttonJugar;
    Button _buttonPuntajes;
    Button _buttonConfig;
    TextView _conteoContactos;
    TextView _conteoFavoritos;
    TextView _conteoNoFavoritos;

    DataBase _database;
    ContactoModel _contactoModel;
    Cursor _cursor;
    ArrayList<ContactoModel> _listaPuntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _buttonJugar = (Button)findViewById(R.id.buttonAgregar);
        _buttonPuntajes = (Button)findViewById(R.id.buttonListar);
        _buttonConfig = (Button)findViewById(R.id.buttonOrdenar);

        _buttonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _actividad = new Intent(MainActivity.this, AgregarContactoActivity.class);
                startActivity(_actividad);
            }
        });

        _buttonPuntajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _actividad = new Intent(MainActivity.this, ListarContactosActivity.class);
                startActivity(_actividad);
            }
        });

        _buttonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _actividad = new Intent(MainActivity.this, ListarOrdenadosActivity.class);
                startActivity(_actividad);
            }
        });

        _conteoContactos = (TextView)findViewById(R.id.textViewContactos);
        _conteoFavoritos = (TextView)findViewById(R.id.textViewFavoritos);
        _conteoNoFavoritos = (TextView)findViewById(R.id.textViewNoFavoritos);

        RecargarDatos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecargarDatos();
    }

    private void RecargarDatos(){
        int _totFavoritos = 0;
        int _totNoFavoritos = 0;
        _database = new DataBase(MainActivity.this);
        _cursor = _database.Select(_contactoModel.SELECT_TABLA_FAVORITOS);
        try{
            _listaPuntaje = new ArrayList<>();
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
                if(_contactoModel.getFavorito() == 1)
                    _totFavoritos++;
                else
                    _totNoFavoritos++;
            }

            _conteoContactos.setText(String.format("Total contactos: %s", _listaPuntaje.size()));
            _conteoFavoritos.setText(String.format("Favoritos: %s", _totFavoritos));
            _conteoNoFavoritos.setText(String.format("No Favoritos: %s", _totNoFavoritos));
        }catch (SQLiteException _err){
            _cursor.close();
        }finally {
            _cursor.close();
        }
    }
}
