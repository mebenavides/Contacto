package com.mebenavides.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombreDet;
    private TextView tvTelefonoDet;
    private TextView tvEmailDet;
    private TextView tvDescripcionDet;
    private TextView tvFechaDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString(getResources().getString(R.string.pnombre).toString());
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono).toString());
        String eMail = parametros.getString(getResources().getString(R.string.pemail).toString());
        String fecha = parametros.getString(getResources().getString(R.string.pfecha).toString());
        String descripcion = parametros.getString(getResources().getString(R.string.pdescripcion).toString());


        tvNombreDet = (TextView) findViewById(R.id.tvNombreDet);
        tvTelefonoDet = (TextView) findViewById(R.id.tvTelefonoDet);
        tvEmailDet = (TextView) findViewById(R.id.tvEmailDet);
        tvFechaDet = (TextView) findViewById(R.id.etFechaNacDet);
        tvDescripcionDet = (TextView) findViewById(R.id.tvDescripcionDet);

        tvNombreDet.setText(nombre);
        tvTelefonoDet.setText(telefono);
        tvEmailDet.setText(eMail);
        tvDescripcionDet.setText(descripcion);
        tvFechaDet.setText(fecha);
        android.widget.Button btn = (android.widget.Button) findViewById(R.id.btnEditar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (DetalleContacto.this,  MainActivity.class);

                TextView txtNombre = findViewById(R.id.tvNombreDet);
                TextView txtDescripcion = findViewById(R.id.tvDescripcionDet);
                TextView txtMail = findViewById(R.id.tvEmailDet);
                TextView txttelefono = findViewById(R.id.tvTelefonoDet);
                TextView txtFecha = findViewById(R.id.etFechaNacDet);
                intent.putExtra(getResources().getString(R.string.pnombre), txtNombre.getText().toString());
                intent.putExtra(getResources().getString(R.string.ptelefono), txttelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.pemail), txtMail.getText().toString());
                intent.putExtra(getResources().getString(R.string.pdescripcion), txtDescripcion.getText().toString());
                intent.putExtra(getResources().getString(R.string.pfecha), txtFecha.getText().toString());
                startActivity(intent);

            }
        });
    }

    public void llamar (View v){
        String telefono = tvTelefonoDet.getText().toString();
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }

    public void sendMail (View v){
        String mail = tvEmailDet.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mail);
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
