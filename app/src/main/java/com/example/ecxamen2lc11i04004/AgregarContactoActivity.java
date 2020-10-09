package com.example.ecxamen2lc11i04004;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AgregarContactoActivity extends AppCompatActivity {

    EditText _nombres;
    EditText _apellidos;
    EditText _telefono1;
    EditText _telefono2;
    Switch _favorito;
    Button _buttonGuardar;
    DataBase _database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        _nombres = (EditText)findViewById(R.id.editTextNombre);
        _apellidos = (EditText)findViewById(R.id.editTextApellido);
        _telefono1 = (EditText)findViewById(R.id.editTextTelefono1);
        _telefono2 = (EditText)findViewById(R.id.editTextTelefono2);
        _favorito = (Switch)findViewById(R.id.switchFavorito);

        _buttonGuardar = (Button)findViewById(R.id.buttonGuardar);
        _buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarDatos();
            }
        });

        //_nombres.setText("Marvin Humberto");
        //_apellidos.setText("Linares Caridad");
        //_telefono1.setText("79862145");
        //_telefono2.setText("79862146");

        //PREPARAMOS LA BASE DE DATOS
        _database = new DataBase(AgregarContactoActivity.this);
    }

    private void GuardarDatos(){
        int _favoritoNum = 0;
        if(_favorito.isChecked())
            _favoritoNum = 1;
        else
            _favoritoNum = 0;

        ContactoModel _nuevo = new ContactoModel(
                _nombres.getText().toString(),
                _apellidos.getText().toString(),
                _telefono1.getText().toString(),
                _telefono1.getText().toString(),
                 R.drawable.ic_person_pin_black_24dp,
                _favoritoNum
        );
        //INSERTAMOS EL REGISITRO
        _database.Insertar(_nuevo.NOMBRE_TABLA, _nuevo.Valores());

        Toast.makeText(AgregarContactoActivity.this,
                "Contacto agregado correctamente!",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
