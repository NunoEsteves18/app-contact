package com.example.main.contactosddm;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by main on 15/01/2018.
 */

public class AmUtil {

    Activity mActivity;

    public  AmUtil(Activity pA ){

        this.mActivity = pA;

    }

    final String TAG="@AmUtil";
    public final static String DATA_SEPARADOR ="-";
    private final int DATA_QUANTIDADE_DE_PARTES =6;
    public enum LOCALE_KEYS {DisplayName, Country, DisplayCountry, Language, DisplayLanguage,
        DisplayVariant, Iso3Country, Iso3Language};
    int readIntFromEditText (EditText et) throws Exception {
        int ret=0;
        String etString=et.getText().toString();
        etString=etString.trim();
        if (!etString.isEmpty()){
            try{
                ret=Integer.parseInt(etString);
            }//try
            catch (Exception e){
                String msg=e.getMessage().toString();
                Log.e(TAG, msg);
                throw new Exception (msg);
            }//catch
        }
        return ret;
    }//readIntFromEditText
    public Map<String, String> getActiveLocaleInfo(){
        Map<String, String> ret=new HashMap<String, String>();
        Locale l=Locale.getDefault();
        String sDisplayName=l.getDisplayName();
        String sCountry=l.getCountry();
        String sDisplayCountry=l.getDisplayCountry();
        String sLanguage=l.getLanguage();
        String sDisplayLanguage=l.getDisplayLanguage();
        String sDisplayVariant=l.getDisplayVariant();
        String sIso3Country="";
        try {
            sIso3Country = l.getISO3Country();
        }
        catch(Exception e){
            sIso3Country=e.toString();
        }
        String sIso3Language="";
        try{
            sIso3Language=l.getISO3Language();
        }
        catch(Exception e){
            sIso3Language=e.toString();
        }

        ret.put(LOCALE_KEYS.DisplayName.toString(), sDisplayName);
        ret.put(LOCALE_KEYS.Country.toString(),sCountry);
        ret.put(LOCALE_KEYS.DisplayCountry.toString(), sDisplayCountry);
        ret.put(LOCALE_KEYS.Language.toString(), sLanguage);
        ret.put(LOCALE_KEYS.DisplayLanguage.toString(), sDisplayLanguage);
        ret.put(LOCALE_KEYS.DisplayVariant.toString(), sDisplayVariant);
        ret.put(LOCALE_KEYS.Iso3Country.toString(), sIso3Country);
        ret.put(LOCALE_KEYS.Iso3Language.toString(), sIso3Language);
        return ret;
    }//getActiveLocaleInfo
    public static String ymdhms(){
        return ymdhms(DATA_SEPARADOR);
    }//NowString
    public static String ymdhms(String separador){
        String
                sFormato="yyyy"+separador+"MM"+separador+"dd"+separador+"HH"+separador+"mm"+separador+"ss";
        SimpleDateFormat sdfFormato=new SimpleDateFormat(sFormato);

        Calendar calAtual=Calendar.getInstance();
        Date tempoAtual=calAtual.getTime();
        String sAgora=sdfFormato.format(tempoAtual);
        return sAgora;
    }//NowString
    public int[] dataAtualPorPartesEmArraySimples(){
        String strDataAtual=this.ymdhms();
        String[] strPartes=strDataAtual.split(this.DATA_SEPARADOR);
        if (strPartes.length==this.DATA_QUANTIDADE_DE_PARTES){
            String strAno=strPartes[0];
            String strMes=strPartes[1];
            String strDia=strPartes[2];
            String strHoras=strPartes[3];
            String strMinutos=strPartes[4];
            String strSegundos=strPartes[5];
            int ano=Integer.parseInt(strAno, 10);
            int mes=Integer.parseInt(strMes, 10);
            int dia=Integer.parseInt(strDia, 10);
            int horas=Integer.parseInt(strHoras, 10);
            int minutos=Integer.parseInt(strMinutos, 10);
            int segundos=Integer.parseInt(strSegundos, 10);
            int[] ret={ano, mes, dia, horas, minutos, segundos};
            return ret;
        }//if
        return null;
    }//dataAtualPorPartesEmArraySimples
    public Map<String, Integer> dataAtualPorPartesEmMapStringInteger(){
        String strDataAtual=this.ymdhms();
        String[] strPartes=strDataAtual.split(this.DATA_SEPARADOR);
        if (strPartes.length==this.DATA_QUANTIDADE_DE_PARTES){
            String strAno=strPartes[0];
            String strMes=strPartes[1];
            String strDia=strPartes[2];
            String strHoras=strPartes[3];
            String strMinutos=strPartes[4];
            String strSegundos=strPartes[5];
            Integer ano=Integer.parseInt(strAno, 10);
            Integer mes=Integer.parseInt(strMes, 10);
            Integer dia=Integer.parseInt(strDia, 10);
            Integer horas=Integer.parseInt(strHoras, 10);
            Integer minutos=Integer.parseInt(strMinutos, 10);
            Integer segundos=Integer.parseInt(strSegundos, 10);
            Map<String, Integer> ret=new HashMap<String, Integer>();
            ret.put("ano", ano);
            ret.put("mes", mes);
            ret.put("dia", dia);
            ret.put("horas", horas);
            ret.put("minutos", minutos);
            ret.put("segundos", segundos);
            return ret;
        }//if
        return null;
    }//dataAtualPorPartesEmMapStringInteger
    public String numeroXComNDigitos(int x, int n){
        String sX=x+"";
        while(sX.length()<n){
            sX="0"+sX;
        }//while
        return sX;
    }//numeroXComNDigitos
    public Map<String, String> dataAtualPorPartesEmMapStringString(){
        String strDataAtual=this.ymdhms();
        String[] strPartes=strDataAtual.split(this.DATA_SEPARADOR);
        if (strPartes.length==this.DATA_QUANTIDADE_DE_PARTES){
            String strAno=strPartes[0];
            String strMes=strPartes[1];
            String strDia=strPartes[2];
            String strHoras=strPartes[3];
            String strMinutos=strPartes[4];
            String strSegundos=strPartes[5];
            Integer ano=Integer.parseInt(strAno, 10);
            Integer mes=Integer.parseInt(strMes, 10);
            Integer dia=Integer.parseInt(strDia, 10);
            Integer horas=Integer.parseInt(strHoras, 10);
            Integer minutos=Integer.parseInt(strMinutos, 10);
            Integer segundos=Integer.parseInt(strSegundos, 10);
            strAno=numeroXComNDigitos(ano, 4);
            strMes=numeroXComNDigitos(mes, 2);
            strDia=numeroXComNDigitos(dia, 2);
            strHoras=numeroXComNDigitos(horas, 2);
            strMinutos=numeroXComNDigitos(minutos, 2);
            strSegundos=numeroXComNDigitos(segundos, 2);
            Map<String, String> ret=new HashMap<String, String>();
            ret.put("ano", strAno);
            ret.put("mes", strMes);
            ret.put("dia", strDia);
            ret.put("horas", strHoras);
            ret.put("minutos", strMinutos);
            ret.put("segundos", strSegundos);
            return ret;
        }//if
        return null;
    }//dataAtualPorPartesEmMapStringString
    //////////////////////////////////////////////////////////////////////
    public String floatToPortuguese(float f){
        String ret="";
        Locale localePortugal;
        localePortugal=new Locale("pt", "PRT");
        NumberFormat numberFormatPortugal = NumberFormat.getNumberInstance(localePortugal);
        numberFormatPortugal.setGroupingUsed(true);
        DecimalFormat decimalFormatPortugal = (DecimalFormat)numberFormatPortugal ;
        ret=decimalFormatPortugal.format(f);
        return ret;
    }//floatToPortuguese
    public char getDecimalSeparatorInCurrentDeviceLocale (){
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat)nf;
        DecimalFormatSymbols simbolos = df.getDecimalFormatSymbols();
        char cSeparadorDecimal = simbolos.getDecimalSeparator();
        return cSeparadorDecimal;
    }//getDecimalSeparatorInCurrentDeviceLocale
    public char[] getAcceptableDecimalFormatChars (){
//char[] ret=new char[11];
        char[] ret={'0','1','2','3','4','5','6','7','8','9','?'};
        ret[10]=getDecimalSeparatorInCurrentDeviceLocale();
        return ret;
    }//getAcceptableDecimalFormatChars
    public String bundleExaminer (Bundle b){
        String ret="";
        if(b!= null){
            Set<String> set = b.keySet();
            Object bArray[] = set.toArray();
            for(int i=0; i<bArray.length; i++){
                String bundleKey = bArray[i].toString();
                String bundleValueAsString = b.get(bundleKey).toString();
                String line="entry #"+i+" : Bundle @ "+bundleKey+" = "+bundleValueAsString;
                ret+=line+"\n";

            }
        } else {
            ret+="Bundle is null";

        }
        return ret;
    }//bundleExaminer
    public void showLongMsg (String msg, Activity context){
        Toast t= Toast.makeText(context, msg, Toast.LENGTH_LONG);
        t.show();
    }//showMsg
    public void showShortMsg (String msg, Activity context){
        Toast t= Toast.makeText(context, msg, Toast.LENGTH_LONG);
        t.show();
    }//showMsg
    public void showLongMsg (String msg, Context context){
        Toast t= Toast.makeText(context, msg, Toast.LENGTH_LONG);
        t.show();
    }//showMsg
    public void showShortMsg (String msg, Context context){
        Toast t= Toast.makeText(context, msg, Toast.LENGTH_LONG);
        t.show();
    }//showMsg
    public int showAsNotification (Context c, String title, String msg, int icon) {

        NotificationCompat.Builder nb = new NotificationCompat.Builder(c);
        nb.setContentTitle(title);
        nb.setAutoCancel(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

        } else {

        }

        nb.setSmallIcon(icon);
        nb.setContentText(msg);
        Notification n=nb.build();
        NotificationManager notificationManager = (NotificationManager)
                c.getSystemService(c.NOTIFICATION_SERVICE);
        int iNotificationId=(int) System.currentTimeMillis();
        notificationManager.notify(iNotificationId, n);
        return iNotificationId;
    }//showAsNotification
    public void quitToHome(Context c){
        Intent i=new Intent (Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(i);
    }//quitToHome
    String io_is_writeToFile (String nomeFicheiro, Context c, String... conteudo){
        String ret="";
        File rootDaInternalStorage = c.getFilesDir(); //
        if (rootDaInternalStorage!=null){
            try{

                FileOutputStream fos = c.openFileOutput(nomeFicheiro, Context.MODE_APPEND);
                for (String coisa : conteudo) fos.write(coisa.getBytes());
                fos.close();
                String caminhoAbsoluto =
                        rootDaInternalStorage.getAbsolutePath()+"/"+nomeFicheiro;
                return caminhoAbsoluto;

            }//try
            catch (Exception e){
                String msg=e.toString();
                Log.e("@io_is_writeToFile", msg);
            }//catch
        }//
        return ret;
    }//io_is_writeToFile

    String io_is_WriteFile (String pastaOndeQueremosFicheiro, String nomeFicheiro, Context c,
                            String... conteudo){
        String ret="";
        File rootIS = c.getFilesDir();
        if (rootIS!=null){

            pastaOndeQueremosFicheiro = rootIS + "/" + pastaOndeQueremosFicheiro;
            File dirACriar = new File (pastaOndeQueremosFicheiro);
            if (dirACriar!=null){
                boolean fezDir = dirACriar.mkdirs();
                boolean sucesso = fezDir || dirACriar.isDirectory();
                if (sucesso){
                    String dirBase = dirACriar.getAbsolutePath();
                    File ficheiroACriar = new File (dirBase, nomeFicheiro);
                    try{
                        FileOutputStream fos = new FileOutputStream (ficheiroACriar);
                        for (String coisa : conteudo) fos.write (coisa.getBytes());
                        fos.close();
                        String caminhoAbsolutoDoFileCriado =
                                ficheiroACriar.getAbsolutePath();
                        ret=caminhoAbsolutoDoFileCriado;
                    }//try
                    catch (Exception e){
                        String msg=e.toString();
                        Log.e("@io_is_writeToFile", msg);
                    }//catch
                }//if
            }//if
        }//if
        return ret;
    }//io_is_EscreverFicheiro
    public String io_is_ReadFileFromRoot (String nomeFicheiro, Context c){
        String ret="";
        File rootDaIs = c.getFilesDir(); // /data/data/com.example.filesystem2pl/files
        String sRootDaIs = rootDaIs.getAbsolutePath();
        File ficheiro = new File (sRootDaIs, nomeFicheiro);
        boolean bFicheiroExiste = ficheiro.isFile();
        if (bFicheiroExiste){
            try{
                FileInputStream fis = new FileInputStream (ficheiro);
                if (fis!=null){
                    int iSimbolo;
                    char simbolo;
                    while ((iSimbolo=fis.read())!=-1){
                        simbolo=(char)iSimbolo;
                        ret+=simbolo;
                    }//while
                    fis.close();
                }//if
            }//try
            catch (Exception e){
                String msg=e.toString();
                Log.e("@io_is_ReadFileFromRoot", msg);
            }//catch
        }//if
        return ret;
    }//io_is_ReadFileFromRoot

    public String io_is_ReadFileFromDir (String caminho, String nomeFicheiro, Context c){
        String ret="";
        File ficheiro=null;
        String sRootDaIs = c.getFilesDir().getAbsolutePath();
        if (nomeFicheiro.startsWith ("/")){
            ficheiro = new File (nomeFicheiro);
        }
        else{
            String sLocalizacaoAbsolutaDoCaminho = sRootDaIs + "/" + caminho;
            ficheiro = new File (sLocalizacaoAbsolutaDoCaminho, nomeFicheiro);
        }
        boolean bFicheiroExiste = ficheiro.isFile();
        if (bFicheiroExiste){
            try{
                FileInputStream fis = new FileInputStream (ficheiro);
                if (fis!=null){
                    int iSimbolo; char simbolo;
                    while ((iSimbolo=fis.read())!=-1){
                        simbolo=(char)iSimbolo;
                        ret+=simbolo;
                    }//while
                    fis.close();
                }//if
            }//try
            catch (Exception e){
                String msg=e.toString();
                Log.e("@io_is_ReadFileFromDir", msg);
            }//catch
        }//if
        return ret;
    }//io_is_ReadFileFromDir
    ////////////////////////////////// EXTERNAL STORAGE ///////////////////////
    public String io_es_AbsolutePath (){
        String ret="";
        File rootDaEs = Environment.getExternalStorageDirectory();
        ret = rootDaEs.getAbsolutePath();
        return ret;
    }//io_es_AbsolutePath
    public String io_es_State(){
        String ret="";
        ret = Environment.getExternalStorageState(); //Environment.MEDIA_REMOVED,MEDIA_MOUNTED, MEDIA_MOUNTED_READ_ONLY
        return ret;
    }//io_es_State
    public boolean io_es_CanWrite(){
        boolean ret=false;
        ret = io_es_State().equals(Environment.MEDIA_MOUNTED);
        return ret;
    }//io_es_CanWrite
    public boolean io_es_CanRead(){
        boolean ret=false;
        boolean bPossoEscrever = io_es_CanWrite();
        ret = bPossoEscrever || io_es_State().equals(Environment.MEDIA_MOUNTED_READ_ONLY);
        return ret;
    }//io_es_CanRead
    public boolean io_es_Usable(){
        return io_es_CanRead() || io_es_CanWrite();
    }//io_es_Usable
    String io_es_WriteFileToRoot (String nomeDoFicheiro, String... conteudo){
        String ret="";
        boolean bPossoEscrever=io_es_CanWrite();
        if (bPossoEscrever){
            String rootDaEs = io_es_AbsolutePath();
            File ficheiro = new File (rootDaEs, nomeDoFicheiro);
            if (ficheiro!=null){
                try{
                    FileOutputStream fos=new FileOutputStream(ficheiro);
                    if (fos!=null){
                        for (String coisa : conteudo) fos.write(coisa.getBytes());
                        fos.close();
                        ret=rootDaEs+"/"+nomeDoFicheiro; //mnt/sdcard/ex1.txt
                    }//if
                }//try
                catch (Exception e){
                    String msg=e.toString();
                    Log.e(TAG, msg);
                }//catch
            }//if
        }//if
        return ret;
    }//io_es_WriteFileToRoot
    public String io_es_WriteFileToDir (String nomeDir, String nomeFicheiro, String...
            conteudo){
        String ret="";
        if (io_es_CanWrite()){
            String rootDaEs = io_es_AbsolutePath();
            String sCaminhoDesejado = rootDaEs+"/"+ nomeDir;
            File caminhoDesejado = new File (sCaminhoDesejado);
            boolean caminhoJaExiste = caminhoDesejado.isDirectory();
            boolean caminhoCriado = caminhoDesejado.mkdirs();
            boolean caminhoDisponivel = caminhoJaExiste || caminhoCriado;
            if (caminhoDisponivel){
                File ficheiro = new File (sCaminhoDesejado, nomeFicheiro);
                if (ficheiro!=null){
                    try{
                        FileOutputStream fos = new FileOutputStream (ficheiro);
                        for (String coisa : conteudo) fos.write(coisa.getBytes());
                        fos.close();
                        ret = sCaminhoDesejado+"/"+nomeFicheiro;
                    }//try
                    catch(Exception e){
                        String msg=e.toString();
                        Log.e(TAG, msg);
                    }//catch
                }//if
            }//if
        }//if
        return ret;
    }//io_es_WriteFileToDir
    public String io_es_ReadFileFromRoot (String nomeFicheiro){
        String ret="";
        File ficheiro;
        if (nomeFicheiro.startsWith("/")){
            ficheiro = new File (io_es_AbsolutePath(), nomeFicheiro);
        }
        else{
            String caminhoAbsolutoAteFicheiro = io_es_AbsolutePath()+"/"+nomeFicheiro;
            ficheiro = new File (caminhoAbsolutoAteFicheiro);
        }
        boolean bFicheiroExiste = ficheiro.isFile();
        if (bFicheiroExiste){
            try{
                FileInputStream fis = new FileInputStream (ficheiro);
                if (fis!=null){
                    char simbolo; int iSimbolo;
                    while ((iSimbolo=fis.read())!=-1){
                        simbolo=(char)iSimbolo;
                        ret+=simbolo;
                    }//while
                    fis.close();
                }//if
            }//try
            catch (Exception e){
                String msg=e.toString();
                Log.e(TAG, msg);
            }//catch
        }//if
        return ret;
    }//io_es_ReadFileFromRoot
    public String io_es_ReadFileFromDir (String caminho, String nomeFicheiro){
        String ret="";
        String sRootEs = io_es_AbsolutePath();
        String caminhoAbsoluto = sRootEs + "/"+caminho;
        File fCaminho = new File (caminhoAbsoluto);
        boolean bCaminhoExiste = fCaminho.isDirectory();
        if (bCaminhoExiste){
            File ficheiro = null;
            if (nomeFicheiro.startsWith("/")){
                ficheiro = new File (nomeFicheiro);
            }
            else{
                ficheiro = new File (caminhoAbsoluto, nomeFicheiro);
            }
            boolean bFicheiroExiste = ficheiro.isFile();
            if (bFicheiroExiste){
                char simbolo; int iSimbolo;
                try{
                    FileInputStream fis = new FileInputStream (ficheiro);
                    while ((iSimbolo=fis.read())!=-1){
                        simbolo=(char)iSimbolo;
                        ret+=simbolo;
                    }//while
                    fis.close();
                }
                catch (Exception e){
                    String msg=e.toString();
                    Log.e(TAG, msg);
                }//catch
            }//if
        }//if
        return ret;
    }//io_es_ReadFileFromDir
    public void fb(String pFrase){
        Toast t = Toast.makeText(mActivity,pFrase,Toast.LENGTH_LONG);
        t.show();
    }
    public static int arrayStringContainsElement(
            String[] pA,
            String pEl
    ){
        int iRet = -1;

        Boolean bCheck = pA.length>0;

        if (bCheck){
            for (int idx=0; idx<pA.length; idx++){
                String strCurrent = pA[idx];
                Boolean bFound = strCurrent.compareTo(pEl)==0;
                if (bFound) return idx;
            }//for
        }//if

        return iRet;
    }//arrayStringContainsElement
}//AmUtil

