package com.example.main.contactosddm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by main on 15/01/2018.
 */

public class ContactosBD extends SQLiteOpenHelper {
    final static String CONTACTOS_BD_NAME ="contactos.BD";
    final static int CONTACTOS_BD_VERSION =2;
    final static String TABLE_CONTACTOS="contactos";
    final static String COL_ID ="_id";
    final static String COL_NOME ="nome";
    final static String COL_NUMERO ="numero";

    //aqui escreveremos SQLite para criação da tabela contactos
    public final static String[] TABLE_MYCONCT_Cols = {COL_ID,COL_NOME,COL_NUMERO};
    final static String CREATE_TABLE_CONTACTOS="CREATE TABLE IF NOT EXISTS "+
            TABLE_CONTACTOS+"("+
            COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_NOME +" TEXT NOT NULL,"+
            COL_NUMERO +" TEXT NOT NULL"+");";
    final static String DROP_TABLE_CONTACTOS="DROP TABLE IF EXISTS "+ TABLE_CONTACTOS+";";
    //-------------------------------------------------------------------------------------------
    public ContactosBD (Context pContext){
        super(
                pContext,
                CONTACTOS_BD_NAME,
                null, //SQLiteDatabase.CursorFactory: to use for creating cursor objects, or null for the default
                CONTACTOS_BD_VERSION);
    }//BdAjuda
    //--------------------------------------------------------------------------------------------
    public void instalarBd (SQLiteDatabase db){
        try{
            db.execSQL(CREATE_TABLE_CONTACTOS);
        }catch(Exception e){
            Log.e (this.getClass().getName(), e.toString()+" FAILED@instalarBd\n"+CREATE_TABLE_CONTACTOS);
        }
    }//instalarBd
    //-------------------------------------------------------------------------------------------
    public void reinstalarBd(SQLiteDatabase db){

        try{
            db.execSQL(DROP_TABLE_CONTACTOS);
        }
        catch(Exception e){
            Log.e (this.getClass().getName(), e.toString()+" FAILED@reinstalarBd\n"+DROP_TABLE_CONTACTOS);
        }
        instalarBd(db);
    }//reinstalarBd
    //------------------------------------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db) {

        instalarBd(db);
    }//onCreate
    //------------------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        reinstalarBd(db); //os dados não se perderão se de alguma forma forem preservados
    }//onUpgrade
//---------------------------------------------------------------------------------------

    public int removerContactoPorId(long id){
        int contadorDeLinhasAfetadas = 0;
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            String filtros= COL_ID +"= ?";

            String[] argumentosParaFiltros={String.valueOf(id)};
            contadorDeLinhasAfetadas=
                    db.delete(
                            TABLE_CONTACTOS,
                            filtros,
                            argumentosParaFiltros
                    );
            db.close();
        }//try
        catch (Exception e){
        }//catch
        return contadorDeLinhasAfetadas;
    }//removerContactoPorId
//---------------------------------------------------------------------------------------
    public int removerContactoContendoNome(String pNome){
        int contadorDeLinhasAfetadas = 0;
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            String filtros= COL_NOME+" like '%?%'";
            String[] argumentosParaFiltros={pNome};
            contadorDeLinhasAfetadas=
                    db.delete(
                            TABLE_CONTACTOS,
                            filtros,
                            argumentosParaFiltros
                    );
            db.close();
        }//try
        catch (Exception e){
        }//catch
        return contadorDeLinhasAfetadas;
    }//removerContactoContendoNome
//---------------------------------------------------------------------------------------

    public long insertContacto(Contacto c){
        long idOndeFoiFeitoInsert=-1;
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues pares=new ContentValues();
            pares.put(COL_NOME, c.getNome());
            pares.put(COL_NUMERO, c.getNumero());
            idOndeFoiFeitoInsert = db.insert(TABLE_CONTACTOS, null, pares);
            db.close();
        }
        catch (Exception e){
        }
        return idOndeFoiFeitoInsert;
    }//insertContacto
//---------------------------------------------------------------------------------------
public Contacto[] selectTodosContactos(){
    Contacto[] ret=null;
    try{
        SQLiteDatabase db=this.getWritableDatabase();
        String q="SELECT * FROM "+TABLE_CONTACTOS+";";
        String[] filtros=null;
        Cursor resultados = db.rawQuery(q, filtros);
        int quantosResultados = resultados.getCount();
        ArrayList<Contacto> ret2=new ArrayList<Contacto>();
        ret = new Contacto[quantosResultados];
        int idx=0;
        resultados.moveToFirst();
        while(!resultados.isAfterLast()){
            long idEncontrado=
                    resultados.getLong(resultados.getColumnIndex(COL_ID));
            String nomeEncontrado=
                    resultados.getString(resultados.getColumnIndex(COL_NOME));
            String numeroEncontrado=
                    resultados.getString(resultados.getColumnIndex(COL_NUMERO));
            Contacto c=new Contacto(idEncontrado, nomeEncontrado, numeroEncontrado);
            ret[idx]=c; idx++;
            ret2.add(c);
            resultados.moveToNext();
        }//while
        db.close();
    }//try
    catch(Exception e){
    }//catch
    return ret;
}//selectTodosContactos
//---------------------------------------------------------------------------------------------
    public long insertContacto(String nome, String numero){
        long idOndeFoiFeitoInsert=-1;
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues pares=new ContentValues();
            pares.put(COL_NOME, nome);
            pares.put(COL_NUMERO, numero);
            idOndeFoiFeitoInsert = db.insert(TABLE_CONTACTOS, null, pares);
            db.close();
        }//try
        catch(Exception e){
            String msg = e.toString()+"@insertContacto";
            Log.e(this.getClass().getName(), msg);
        }//catch
        return idOndeFoiFeitoInsert;
    }//insertContacto
//---------------------------------------------------------------------------------------

    public boolean updateContact( String nomeOld, String nome, String numeroOld, String numero){
        SQLiteDatabase db=this.getWritableDatabase();
        String [] array = {nomeOld, numeroOld};

        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COL_NOME, nome);
        updatedValues.put(COL_NUMERO, numero);

        return db.update(TABLE_CONTACTOS, updatedValues, COL_NOME+"= ? AND "+COL_NUMERO+"= ? ",array ) !=0;
    }
//-----------------------------------------------------------------------------------------

}//ContactosBD


