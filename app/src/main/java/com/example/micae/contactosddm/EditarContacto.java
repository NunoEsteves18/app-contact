package com.example.main.contactosddm;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by main on 19/01/2018.
 */

public class EditarContacto extends AppCompatActivity {
    Context mContext;

    AmUtil mUtil;

    ContactosBD mContactos;

    MainActivity Contactosmain;

    EditText mNome, mNumero;

    Button mBtnEdit, mBtnCancelEdit;

    View.OnClickListener mClickHandler;
    String nomeOld;
    String numeroOld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        init();
    }//onCreate
//--------------------------------------------------------------------------------------------------------
    private void init() {
        mUtil=new AmUtil(this);
        mNome = findViewById(R.id.idEtNomeEdit);
        mNumero = findViewById(R.id.idEtNumeroEdit);
        mBtnEdit = findViewById(R.id.idBtnConfirmEdit);
        mBtnCancelEdit = findViewById(R.id.idBtnCancelEdit);
        mContactos = new ContactosBD(this);
        Intent bundle = getIntent();
        nomeOld = bundle.getStringExtra("nome");
        numeroOld = bundle.getStringExtra("numero");

        mNome.setText(nomeOld);
        mNumero.setText(numeroOld);


        //-------------------------------------------------------------------------------------------

        mBtnEdit.setOnClickListener(mClickHandler);

        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.idBtnConfirmEdit) {


                    String nome = mNome.getText().toString();
                    String numero = mNumero.getText().toString();
                    mContactos.updateContact(nomeOld, nome, numeroOld, numero);
                    createIntente();


                }
                ;
            }

        });
        //-------------------------------------------------------------------------------------------
        mBtnCancelEdit.setOnClickListener(mClickHandler);
                mBtnCancelEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent edit = new Intent(EditarContacto.this, MainActivity.class);
                        EditarContacto.this.startActivity(edit);
                    }
                });




//----------------------------------------------------------------------------------------------------------

    }

    void createIntente() {

        Intent Recuar = new Intent(EditarContacto.this, MainActivity.class);
        EditarContacto.this.startActivity(Recuar);
    }

}