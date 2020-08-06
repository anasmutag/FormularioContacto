package com.example.formulariocontacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {
    private TextView tvNombreDatos, tvFechaDatos, tvTelefonoDatos, tvEmailDatos, tvDescripcionDatos;
    Button btnEditar;
    static final String STATE_SCORE = "playerScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        btnEditar = (Button) findViewById(R.id.btnEditar);

        Bundle parametros = getIntent().getExtras();

        String nombre = parametros.getString(getResources().getString(R.string.dnombre));
        String fecha = parametros.getString(getResources().getString(R.string.dfecha));
        String telefono = parametros.getString(getResources().getString(R.string.dtelefono));
        String email = parametros.getString(getResources().getString(R.string.demail));
        String descripcion = parametros.getString(getResources().getString(R.string.ddescripcion));

        tvNombreDatos = (TextView) findViewById(R.id.tvNombreDatos);
        tvFechaDatos = (TextView) findViewById(R.id.tvFechaDatos);
        tvTelefonoDatos = (TextView) findViewById(R.id.tvTelefonoDatos);
        tvEmailDatos = (TextView) findViewById(R.id.tvEmailDatos);
        tvDescripcionDatos = (TextView) findViewById(R.id.tvDescripcionDatos);

        tvNombreDatos.setText(nombre);
        tvFechaDatos.setText(" " + fecha);
        tvTelefonoDatos.setText(" " + telefono);
        tvEmailDatos.setText(" " + email);
        tvDescripcionDatos.setText(" " + descripcion);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(getResources().getString(R.string.dnombre), tvNombreDatos.getText().toString());
                intent.putExtra(getResources().getString(R.string.dfecha), tvFechaDatos.getText().toString());
                intent.putExtra(getResources().getString(R.string.dtelefono), tvTelefonoDatos.getText().toString());
                intent.putExtra(getResources().getString(R.string.demail), tvEmailDatos.getText().toString());
                intent.putExtra(getResources().getString(R.string.ddescripcion), tvDescripcionDatos.getText().toString());

                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_SCORE, tvNombreDatos.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }
}