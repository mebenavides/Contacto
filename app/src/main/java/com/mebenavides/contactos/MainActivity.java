package com.mebenavides.contactos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText tvNombre;
    private EditText tvTelefono;
    private EditText tvEmail;
    private EditText tvDescripcion;
    private EditText etFechaNac;

    Calendar calendario = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle parametros = getIntent().getExtras();
        if (parametros != null){
            String nombre = parametros.getString("nombre");//parametros.getString(getResources().getString(R.string.pnombre).toString());

            String telefono = parametros.getString(getResources().getString(R.string.ptelefono).toString());
            String eMail = parametros.getString(getResources().getString(R.string.pemail).toString());
            String fecha = parametros.getString(getResources().getString(R.string.pfecha).toString());
            String descripcion = parametros.getString(getResources().getString(R.string.pdescripcion).toString());

            tvNombre = (EditText) findViewById(R.id.tvNombre);
            tvTelefono = (EditText) findViewById(R.id.tvTelefono);
            tvEmail = (EditText) findViewById(R.id.tvEmail);
            etFechaNac = (EditText) findViewById(R.id.etFechaNac);
            tvDescripcion = (EditText) findViewById(R.id.tvDescripcion);

            tvNombre.setText(nombre);
            tvTelefono.setText(telefono);
            tvEmail.setText(eMail);
            tvDescripcion.setText(descripcion);
            etFechaNac.setText(fecha);
        }

        etFechaNac = findViewById(R.id.etFechaNac);
        etFechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        android.widget.Button btn = (android.widget.Button) findViewById(R.id.btnSiguiente);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (MainActivity.this, DetalleContacto.class);

                EditText txtNombre = findViewById(R.id.tvNombre);
                EditText txtDescripcion = findViewById(R.id.tvDescripcion);
                EditText txtMail = findViewById(R.id.tvEmail);
                EditText txttelefono = findViewById(R.id.tvTelefono);
                EditText txtFecha = findViewById(R.id.etFechaNac);
                intent.putExtra(getResources().getString(R.string.pnombre), txtNombre.getText().toString());
                intent.putExtra(getResources().getString(R.string.ptelefono), txttelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.pemail), txtMail.getText().toString());
                intent.putExtra(getResources().getString(R.string.pdescripcion), txtDescripcion.getText().toString());
                intent.putExtra(getResources().getString(R.string.pfecha), txtFecha.getText().toString());
                startActivity(intent);

            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };

    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        etFechaNac.setText(sdf.format(calendario.getTime()));
    }
}