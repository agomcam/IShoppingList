package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class ProductPendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_pending);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSaveProduct = findViewById(R.id.btnSaveProductPending);
        Button btnCancelProduct = findViewById(R.id.btnCancelPending);
        Spinner spProducts = findViewById(R.id.spProducts);

        ProductAdapter productAdapter = new ProductAdapter(ProductPendingActivity.this, 0, DataBase.getProductListNotPending());
        spProducts.setAdapter(productAdapter);

        btnCancelProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCancel = new Intent(ProductPendingActivity.this, MainActivity.class);
                startActivity(intentCancel);
            }
        });

        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = (Product) spProducts.getSelectedItem();
                // Ponemos el estado a pendiente
                product.setState(true);
                Intent intent = new Intent(ProductPendingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}