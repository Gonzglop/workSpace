package com.example.ejemplobasedatos;

public class Datos {
    private String texto1;
    private String texto2;
    private int icono;

    public Datos (String texto1,String texto2){
        this.texto1= texto1;
        this.texto2= texto2;

    }

    public Datos(String texto1, int icono) {
        this.texto1 = texto1;
        this.icono = icono;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getTexto1() {
        return texto1;
    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }

    public String getTexto2() {
        return texto2;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }
}
