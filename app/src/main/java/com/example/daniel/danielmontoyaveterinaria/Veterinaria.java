package com.example.daniel.danielmontoyaveterinaria;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Veterinaria extends AppCompatActivity {


    private EditText rutC,nombreC,telefonoC, nombreM;
    private RadioGroup grp_CategoriaM;
    private RadioButton rbtPerro, rbtGato;
    private Spinner spRaza;
    private Button aceptarMasc, cancelarMasc;
    private String razaPerro[] = {"Seleccione una Raza","Bulldog","Beagle","Caniche"};
    private String razaGato[] = {"Seleccione una Raza","Ragdoll","Absinio","Bengala"};
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinaria);
        rutC=(EditText)findViewById(R.id.edt_rut);
        nombreC = (EditText) findViewById(R.id.edt_nombreC);
        telefonoC = (EditText) findViewById(R.id.edt_telefono);
        nombreM = (EditText) findViewById(R.id.edt_NombreM);
        grp_CategoriaM = (RadioGroup) findViewById(R.id.grp_CategoriaM);
        rbtPerro = (RadioButton) findViewById(R.id.rbt_Perro);
        rbtGato = (RadioButton) findViewById(R.id.rbt_Gato);
        spRaza = (Spinner) findViewById(R.id.sp_Raza);
        aceptarMasc = (Button) findViewById(R.id.btn_IngresarMascota);
        cancelarMasc = (Button) findViewById(R.id.btnCancelarMascota);

        grp_CategoriaM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rbtPerro.isChecked()){

                    ArrayAdapter<String> perro = new  ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item,razaPerro);
                    spRaza.setAdapter(perro);

                }else if(rbtGato.isChecked()){
                    ArrayAdapter<String> gato = new  ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item,razaGato);
                    spRaza.setAdapter(gato);
                }
            }
        });

        aceptarMasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Administrador objeto = new Administrador((View.OnClickListener) getApplicationContext(), "ejercicioVeterinaria", null, 1);
                SQLiteDatabase bd = objeto.getWritableDatabase();
                int rut = Integer.parseInt(rutC.getText().toString());
                int telefono = Integer.parseInt(telefonoC.getText().toString());
                String categoria=spRaza.getSelectedItem().toString();
                String raza=spRaza.getSelectedItem().toString();
                String nom=nombreC.getText().toString();
                String mas=nombreM.getText().toString();

                ContentValues registro = new ContentValues();
                registro.put("rut", rut);
                registro.put("nombreDueño", nombreC.getText().toString());
                registro.put("telefono", telefono);
                registro.put("nombreMascota", nombreM.getText().toString());
                registro.put("categoria", categoria);
                registro.put("raza", raza);
                long i = 0;
                i = bd.insert("informacion", null, registro);
                if (i != 0) {
                    Toast.makeText(getApplicationContext(), "producto insertado", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder vista = new AlertDialog.Builder(context);
                    vista.setTitle("Datos ingresados:");
                    vista.setMessage("Nombre Cuidador: "+nom+"\n Rut: "+rut+"\n Telefono: "+telefono+"\n Nombre mascota: "+mas+"\n Categoria: "+categoria+"\n Raza: "+raza);

                    vista.setCancelable(false);
                    vista.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }

                    });
                    vista.create();
                    vista.show();

                }
            }
        });

    }
}
