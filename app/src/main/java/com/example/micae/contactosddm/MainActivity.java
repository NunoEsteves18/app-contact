package com.example.main.contactosddm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    ContactosBD mContactAdapter;

    ContactosBD mContactBD;
    View.OnClickListener mClickHandler;
    View.OnKeyListener mKeyHandler;
    AmUtil mUtil;
    EditText mEtNome, mEtNumero;
    TextView mTvFeedback;
    Button mBtnEdit;
    Button mBtnNovo;
    Button mBtnDelete;
    ContactosBD mContactosBD;
    ListView mLvFeedback;
    AdapterView.OnItemLongClickListener mTratadorDeLongClicksEmElsDaLv;

    //------------------------------------------------------------------------
    String listarTodosOsContactos(){
        Contacto[] todos= mContactosBD.selectTodosContactos();
        String texto="";
        if (todos!=null){
            for (Contacto c:todos){
                String s=c.toString()+"\n";
                texto+=s;
            }//for
            ArrayAdapter<Contacto> adapterContactos = new ArrayAdapter<Contacto>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    todos
            );
            mLvFeedback.setAdapter(adapterContactos);
        }
        else{
        }
        return texto;
    }//listarTodosOsContactos
    //------------------------------------------------------------------------
    void init(){
        mUtil=new AmUtil(this);
        mContactosBD =new ContactosBD(this);
        mLvFeedback = (ListView)findViewById(R.id.id_lvFeedback);
        mEtNome=(EditText)findViewById(R.id.id_etNome);
        mEtNumero=(EditText)findViewById(R.id.id_etNumero);
        mBtnNovo=(Button)findViewById(R.id.id_btnNovo);
        mBtnDelete=(Button)findViewById(R.id.id_btnDelete);
        mBtnEdit=(Button)findViewById(R.id.id_btnEdit);
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sId=mEtNumero.getText().toString();
                if (!sId.isEmpty()){
                    int idDoContactoARemover=Integer.parseInt(sId);
                    int resultado = mContactosBD.removerContactoPorId(idDoContactoARemover);
                    String msg="Foram apagados "+resultado+" registo(s)";
                    mUtil.showShortMsg(msg, MainActivity.this);
                    listarTodosOsContactos();
                }
                else{
                    mUtil.showShortMsg("Escreva um nº", MainActivity.this);
                }
            }
        });
        mKeyHandler = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v,// View onde o key event ocorreu (por ex a edittext para input)
                                 int keyCode,// codigo android da tecla pressionada
                                 KeyEvent event)  //o que foi feito com a tecla (down)
            {

                Boolean bTeclaFoiEnter = keyCode == KeyEvent.KEYCODE_ENTER;
                Boolean bTeclaDown = event.getAction() == KeyEvent.ACTION_DOWN;
                Boolean bConsumir = bTeclaFoiEnter && bTeclaDown;

                if (bConsumir) {
                    return true;
                }//bConsumir


                return false;// evento não foi consumido/nao foi alterado
            }
        };

        mEtNome.setOnKeyListener(mKeyHandler);


        ///// End Keyboard/////

        mBtnNovo.setOnClickListener(mClickHandler);

        mBtnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, numero;
                nome=mEtNome.getText().toString();
                numero=mEtNumero.getText().toString();
                long resultado = mContactosBD.insertContacto(nome, numero);
                String msg=(resultado>=0)?
                        "Novo contacto inserido @"+resultado
                        :
                        "Fracasso ao inserir contacto";
                mUtil.showShortMsg(msg, MainActivity.this);
                listarTodosOsContactos();

            }//onClick
        });//setOnClickListener
        mBtnEdit.setOnClickListener(mClickHandler);

        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(MainActivity.this, EditarContacto.class);
                edit.putExtra("nome",mEtNome.getText().toString());
                edit.putExtra("numero",mEtNumero.getText().toString());

                MainActivity.this.startActivity(edit);


            }
        });
        listarTodosOsContactos();

            }//init

            //----------------------------------------------------------------------------------------------------------------------

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                init();
            }//onCreate

            //------------------------------------------------------------------------
            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                return true;
            }

            //------------------------------------------------------------------------
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                int id = item.getItemId();
                return super.onOptionsItemSelected(item);
            }
        }

