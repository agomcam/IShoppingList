package com.example.ishoppinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.dataBase.DataBase;
import com.example.ishoppinglist.models.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private List<Product> product;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> product) {
        super(context, resource, product);
        this.product = product;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product p = this.product.get(position);

        // En caso de que no se haya creado la vista la creamos nostros
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Obtenemos los componentes
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvId = convertView.findViewById(R.id.tvId);

        // Modificamoslos atributos de los componentes
        tvName.setText(p.getName());
        tvId.setText(String.valueOf(p.getId())); // Convierte el ID a String
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = DataBase.getProductListNotPending().get(position);
        // En caso de que no se haya creado la vista la creamos nostros
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }
        // Obtenemos los componentes
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvId = convertView.findViewById(R.id.tvId);
        // Modificamoslos atributos de los componentes
        tvName.setText(product.getName());
        tvId.setText(String.valueOf(product.getId()));
        return convertView;
    }
}
