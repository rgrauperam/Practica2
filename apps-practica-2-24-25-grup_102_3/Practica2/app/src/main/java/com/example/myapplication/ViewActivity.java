package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    private TextView tvNombre, tvEmail, tvTelefono, tvEdad, tvDireccion, tvAceptar;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualitzar);

        tvNombre = findViewById(R.id.tv_nombre);
        tvEmail = findViewById(R.id.tv_email);
        tvTelefono = findViewById(R.id.tv_telefono);
        tvEdad = findViewById(R.id.tv_edad);
        tvDireccion = findViewById(R.id.tv_direccion);
        tvAceptar = findViewById(R.id.tv_aceptar);
        btnEdit = findViewById(R.id.btn_edit);

        // Retrieve data from MainActivity
        Intent intent = getIntent();

        if (intent != null) {
            tvNombre.setText("Nom: " + intent.getStringExtra("nombre"));
            tvEmail.setText("Correu electrònic: " + intent.getStringExtra("email"));
            tvTelefono.setText("Telèfon: " + intent.getStringExtra("telefono"));
            tvEdad.setText("Edat: " + intent.getStringExtra("edad"));
            tvDireccion.setText("Direcció: " + intent.getStringExtra("direccion"));
            boolean accepted = intent.getBooleanExtra("aceptar", false);
            tvAceptar.setText(accepted ? "Acceptat" : "No acceptat");
        }

        // Button to return to MainActivity
        btnEdit.setOnClickListener(v -> {
            Intent backIntent = new Intent(ViewActivity.this, MainActivity.class);
            startActivity(backIntent);
            finish();
        });
    }
}
