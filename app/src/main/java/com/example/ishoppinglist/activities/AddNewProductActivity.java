package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.dataBase.DataBase;
import com.example.ishoppinglist.models.Product;

public class AddNewProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias de los componentes
        TextView tvID = findViewById(R.id.tvAddId);
        TextView tvName = findViewById(R.id.etAddName);
        TextView tvNote = findViewById(R.id.etAddNote);
        Switch swPending = findViewById(R.id.swAddPending);
        Switch swLactosa = findViewById(R.id.swAddLactosa);
        Switch swGluten = findViewById(R.id.swAddGluten);
        Button btnSave = findViewById(R.id.btnAddSave);
        Button btnBack = findViewById(R.id.btnAddBack);

        // Mostramos el id que se va a poner al nuevo producto
        tvID.setText(String.valueOf(DataBase.getLastIdByProductList()));

        // boton para volver
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // boton para guardar
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();

                product.setId(Integer.valueOf(tvID.getText().toString()));
                product.setName(tvName.getText().toString());
                product.setNote(tvNote.getText().toString());
                product.setState(swPending.isChecked());
                product.setLactosa(swLactosa.isChecked());
                product.setGluten(swGluten.isChecked());

                if (product.getName().isEmpty()){
                    Toast toas = new Toast(AddNewProductActivity.this);
                    toas.setText("El nombre del producto no puede estar vacío");
                    toas.show();
                    return;

                }

                DataBase.addProduct(product,AddNewProductActivity.this);
                tvID.setText(String.valueOf(DataBase.getLastIdByProductList()));
                tvName.setText("");
                tvNote.setText("");
                swPending.setChecked(false);
                swLactosa.setChecked(false);
                swGluten.setChecked(false);
            }
        });
    }
}