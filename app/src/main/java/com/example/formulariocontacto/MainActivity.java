package com.example.formulariocontacto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Calendar calendario = Calendar.getInstance();
    TextInputEditText etNombre, etFecha, etTelefono, etEmail, etDescripcion;
    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (TextInputEditText) findViewById(R.id.etNombre);
        etFecha = (TextInputEditText) findViewById(R.id.etFecha);
        etTelefono = (TextInputEditText) findViewById(R.id.etTelefono);
        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etDescripcion = (TextInputEditText) findViewById(R.id.etDescripcion);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,
                        AlertDialog.THEME_HOLO_LIGHT,
                        date,
                        calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);

                intent.putExtra(getResources().getString(R.string.dnombre), etNombre.getText().toString());
                intent.putExtra(getResources().getString(R.string.dfecha), etFecha.getText().toString());
                intent.putExtra(getResources().getString(R.string.dtelefono), etTelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.demail), etEmail.getText().toString());
                intent.putExtra(getResources().getString(R.string.ddescripcion), etDescripcion.getText().toString());

                startActivityForResult(intent, 0);
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            actualizarInput();
        }
    };

    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        etFecha.setText(sdf.format(calendario.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String nombre = data.getStringExtra(getResources().getString(R.string.dnombre));
                String fecha = data.getStringExtra(getResources().getString(R.string.dfecha));
                String telefono = data.getStringExtra(getResources().getString(R.string.dtelefono));
                String email = data.getStringExtra(getResources().getString(R.string.demail));
                String descripcion = data.getStringExtra(getResources().getString(R.string.ddescripcion));

                etNombre.setText(nombre);
                etFecha.setText(fecha);
                etTelefono.setText(telefono);
                etEmail.setText(email);
                etDescripcion.setText(descripcion);
            }
        }
    }
}