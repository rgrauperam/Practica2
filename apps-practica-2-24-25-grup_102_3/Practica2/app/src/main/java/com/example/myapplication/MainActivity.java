package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etTelefono, etEdad, etDireccion;
    private CheckBox cbAceptar;
    private Button btnGuardar, btnEsborrar, btnVisualitzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.et_nombre);
        etEmail = findViewById(R.id.et_email);
        etTelefono = findViewById(R.id.et_telefono);
        etEdad = findViewById(R.id.et_edad);
        etDireccion = findViewById(R.id.et_direccion);
        cbAceptar = findViewById(R.id.cb_aceptar);
        btnGuardar = findViewById(R.id.btn_guardar);
        btnEsborrar = findViewById(R.id.btn_esborrar);
        btnVisualitzar = findViewById(R.id.btn_visualitzar);

        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarCampos()) {
                    mostrarDialogoConfirmacion();
                    // Send data to ViewActivity
                    intent.putExtra("nombre", etNombre.getText().toString());
                    intent.putExtra("email", etEmail.getText().toString());
                    intent.putExtra("telefono", etTelefono.getText().toString());
                    intent.putExtra("edad", etEdad.getText().toString());
                    intent.putExtra("direccion", etDireccion.getText().toString());
                    intent.putExtra("aceptar", cbAceptar.isChecked());
                }
            }
        });

        btnEsborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });

        btnVisualitzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    Toast.makeText(MainActivity.this, "Obrint pantalla de visualització...", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    showAlert();
                }
            }
        });
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error en els camps");
        builder.setMessage("Algun camp de text està buit o és erroni!");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Do something when user clicks OK
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean validarCampos() {
        boolean esValido = true;

        if (etNombre.getText().toString().isEmpty()) {
            etNombre.setError("El nom és obligatori");
            esValido = false;
        }
        if (etEmail.getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            etEmail.setError("Introduïu un correu vàlid");
            esValido = false;
        }
        if (etTelefono.getText().toString().isEmpty()) {
            etTelefono.setError("El telèfon és obligatori");
            esValido = false;
        }
        if (etEdad.getText().toString().isEmpty()) {
            etEdad.setError("L'edat és obligatòria");
            esValido = false;
        }
        if (!cbAceptar.isChecked()) {
            Toast.makeText(this, "Heu d'acceptar els termes", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        return esValido;
    }

    private void mostrarDialogoConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmació");
        builder.setMessage("Desitja guardar la informació?");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            Toast.makeText(MainActivity.this, "Informació guardada", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etEmail.setText("");
        etTelefono.setText("");
        etEdad.setText("");
        etDireccion.setText("");
        cbAceptar.setChecked(false);
    }
}