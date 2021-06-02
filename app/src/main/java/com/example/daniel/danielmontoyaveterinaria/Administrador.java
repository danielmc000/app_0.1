package com.example.daniel.danielmontoyaveterinaria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;


public class Administrador extends SQLiteOpenHelper
{

    public Administrador(View.OnClickListener context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super((Context) context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table informacion(rut int primary key, nombreDueño text, telefono text, nombreMascota text, categoria text, raza text)");
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("drop table if exists personas");
        sqLiteDatabase.execSQL("create table informacion(rut int primary key, nombreDueño text, telefono text, nombreMascota text, categoria text, raza text)");
    }
}
