package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.dataBase.DataBase;
import com.example.ishoppinglist.models.Product;

public class DetailProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtenemos los componentes
        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvNote = findViewById(R.id.tvDetailNote);
        Switch swPending = findViewById(R.id.swPending);
        Switch swLactosa = findViewById(R.id.swLactosa);
        Switch swGluten = findViewById(R.id.swGluten);
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnBack = findViewById(R.id.btnBack);

        // Obtenemos el intent que inicio el activity
        Intent intent = getIntent();

        int id = intent.getIntExtra("product",0);
        // Recupero los datos del intent
        Product product = (Product) DataBase.getProductById(id);

        // Asignamos los valores a los campos correspondientes
        tvName.setText(product.getName());
        tvNote.setText(product.getNote());
        swPending.setChecked(product.isState());
        swLactosa.setChecked((product.isLactosa()));
        swGluten.setChecked((product.isGluten()));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creamos el intent para cambiar de ventana
                Intent intent = new Intent(DetailProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProductActivity.this, EditProductActivity.class);
                intent.putExtra("productId", product.getId());
                startActivity(intent);
            }
        });
    }
}