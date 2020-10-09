package com.example.ecxamen2lc11i04004;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class OrdenadaAdaptador extends RecyclerView.Adapter<OrdenadaAdaptador.MyViewHolder> {

    Context _contexto;
    ArrayList<ContactoModel> _lista;
    ItemClickListener MyItemClickListener;
    static ContactoModel _itemSeleccionado;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView _textviewNombres;
        public TextView _textviewTelefono1;
        public TextView _textviewTelefono2;
        public ImageView _imagenAvatar;
        public ImageButton _imagenbuttonEliminar;
        public Switch _favorito;

        public MyViewHolder(View v) {
            super(v);
            _textviewNombres = (TextView) v.findViewById(R.id.textViewNombre);
            _textviewTelefono1 = (TextView) v.findViewById(R.id.textViewTelefono1);
            _textviewTelefono2 = (TextView) v.findViewById(R.id.textViewTelefono1);
            _imagenAvatar = (ImageView) v.findViewById(R.id.imageViewAvatar);
            _favorito = (Switch)v.findViewById(R.id.switchFavorito);
            _imagenbuttonEliminar = (ImageButton)v.findViewById(R.id.imagebuttonEliminar);
        }

        public void bind(final ContactoModel _puntaje, final ItemClickListener _listener) {
            _itemSeleccionado = _puntaje;
            _textviewNombres.setText(_puntaje.getFullNombre());
            _textviewTelefono1.setText(_puntaje.getTelefono1());
            _textviewTelefono2.setText(_puntaje.getTelefono2());
            _imagenAvatar.setImageResource(R.drawable.ic_person_pin_black_24dp);
            if(_puntaje.getFavorito() == 1)
                _favorito.setChecked(true);
            else
                _favorito.setChecked(false);

            _imagenbuttonEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(MyItemClickListener != null)
                        MyItemClickListener.onItemClick(_puntaje);
                }
            });
        }
    }

    public OrdenadaAdaptador(ArrayList<ContactoModel> _lista, ItemClickListener _listener) {
        this._lista = _lista;
        this.MyItemClickListener = _listener;
    }

    @NonNull
    @Override
    public OrdenadaAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View _view = (View) LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_lista_ordenada, viewGroup, false);
        this._contexto = viewGroup.getContext();
        return new MyViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdenadaAdaptador.MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(_lista.get(position), MyItemClickListener);
    }

    @Override
    public int getItemCount() {
        return _lista.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.MyItemClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(ContactoModel _puntaje);
    }
}