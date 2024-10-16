package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.adapters.ProductAdapter;
import com.example.ishoppinglist.dataBase.DataBase;
import com.example.ishoppinglist.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<Product> productsPending = new ArrayList<>();
        List<String> options = new ArrayList<>();
        ListView lvProduct = findViewById(R.id.lvElements);
        Button btnAddPending = findViewById(R.id.btnAddPending);
        Button btnAdd = findViewById(R.id.btnAddSystem);
        Spinner spOption = findViewById(R.id.spOptions);
        // inicializamos la lista
        DataBase.inicializeList();

        // ponemos los datos
        options.add("Todos");
        options.add("Sin lactosa");
        options.add("Sin Gluten.");


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, options);


        spOption.setAdapter(spinnerAdapter);

        // Ajustes del boton
        btnAddPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creamos el intent para cambiar de ventana
                Intent intentPending = new Intent(MainActivity.this, ProductPendingActivity.class);
                startActivity(intentPending);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        // Accedemos a los objetos de la lista
        lvProduct.setOnItemClickListener((adapterView, view, i, l) -> {
            Product product = DataBase.getProductListPending().get(i);

            Intent intentDetail = new Intent(MainActivity.this, DetailProductActivity.class);
            intentDetail.putExtra("product", product.getId());
            startActivity(intentDetail);
        });

        spOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, 0, DataBase.getProductListPending());
                        lvProduct.setAdapter(productAdapter);
                        break;
                    case 1:
                        productAdapter = new ProductAdapter(MainActivity.this, 0, DataBase.getProductLactosa());
                        lvProduct.setAdapter(productAdapter);
                        break;
                    case 2:
                        productAdapter = new ProductAdapter(MainActivity.this, 0, DataBase.getProductGluten());
                        lvProduct.setAdapter(productAdapter);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, 0, DataBase.getProductListPending());
                lvProduct.setAdapter(productAdapter);
            }
        });
    }
}