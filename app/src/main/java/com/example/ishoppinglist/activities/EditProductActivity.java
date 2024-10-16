package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtenemos los componentes
        TextView tvId = findViewById(R.id.tvEditId);
        EditText etName = findViewById(R.id.etEditName);
        EditText etNote = findViewById(R.id.etEditNote);
        Switch swPending = findViewById(R.id.swEditPending);
        Switch swLactosa= findViewById(R.id.swEditLactosa);
        Switch swGluten = findViewById(R.id.swEditGluten);

        Button btnBack = findViewById(R.id.btnEditBack);
        Button btnSave = findViewById(R.id.btnEditSave);

        // Obtenemos el intent
        Intent intentDetailProduct = getIntent();
        int idProduct = (int) intentDetailProduct.getSerializableExtra("productId");
        Product currentProduct = DataBase.getProductById(idProduct);
        // Asignamos los valores a los campos correspondientes
        tvId.setText(String.valueOf(currentProduct.getId()));
        etName.setText(currentProduct.getName());
        etNote.setText(currentProduct.getNote());
        swPending.setChecked(currentProduct.isState());
        swLactosa.setChecked(currentProduct.isLactosa());
        swGluten.setChecked(currentProduct.isGluten());


        // Asignamos las acciones de los botones

        // Volvemos a la pantalla principal
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Guardar cambios
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty()) {
                    Toast toastName = new Toast(EditProductActivity.this);
                    toastName.setText("El nombre no puede estar vacío");
                    toastName.show();
                    return;
                }
                currentProduct.setName(etName.getText().toString());
                currentProduct.setNote(etNote.getText().toString());
                currentProduct.setState(swPending.isChecked());
                currentProduct.setLactosa(swLactosa.isChecked());
                currentProduct.setGluten(swGluten.isChecked());
                Toast toast = new Toast(EditProductActivity.this);
                toast.setText("Producto editado correctamente");
                toast.show();

            }
        });
    }
}