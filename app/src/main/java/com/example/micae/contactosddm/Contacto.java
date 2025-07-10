package com.example.main.contactosddm;

import java.io.Serializable;

/**
 * Created by main on 15/01/2018.
 */

public class Contacto implements Serializable {

    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    private String nome;
    private String numero;
    public Contacto (long id, String nome, String numero){
        this.id=id; this.nome=nome; this.numero=numero;
    }//Contacto
    public Contacto (String nome, String numero){
        this.nome=nome; this.numero=numero;
    }//Contacto
    @Override
    public String toString() {
        //return super.toString();
        String ret="id: "+id+" nome: "+nome+" numero: "+numero;
        return ret;
    }//toString
}//Contacto