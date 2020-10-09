package com.example.ecxamen2lc11i04004;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListarOrdenadosActivity extends AppCompatActivity {

    DataBase _database;
    ContactoModel _contactoModel;
    Cursor _cursor;
    ArrayList<ContactoModel> _listaPuntaje;
    OrdenadaAdaptador _adapter;
    RecyclerView _recycled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ordenados);

        _recycled = (RecyclerView)findViewById(R.id.recycledviewLista);
        _recycled.setHasFixedSize(true);
        _recycled.setLayoutManager(new LinearLayoutManager(this));

        RecargarDatos();
    }

    private void RecargarDatos(){
        _database = new DataBase(ListarOrdenadosActivity.this);
        _cursor = _database.Select(_contactoModel.SELECT_TABLA_FAVORITOS);
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
            _adapter =new OrdenadaAdaptador(_listaPuntaje, new OrdenadaAdaptador.ItemClickListener() {
                @Override
                public void onItemClick(final ContactoModel _puntaje) {
                    new AlertDialog.Builder(ListarOrdenadosActivity.this)
                            .setTitle("ELIMINAR CONTACTO")
                            .setMessage("¿Esta seguro de eliminar el registro?")
                            .setIcon(R.drawable.ic_delete_forever_black_24dp)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String[] _ids = {""+_puntaje.getAutoId()};
                                    if(_database.Eliminar(ContactoModel.NOMBRE_TABLA,"autoId = ?", _ids) > 0) {
                                        Toast.makeText(ListarOrdenadosActivity.this,
                                                String.format("El registro se ha eliminado correctamente"),
                                                Toast.LENGTH_SHORT).show();
                                        RecargarDatos();
                                    }
                                }
                            })
                            .setNegativeButton("Cancelar", null)
                            .show();
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
