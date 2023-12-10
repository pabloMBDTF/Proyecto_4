package model;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pablo
 */
abstract public class Usuario {
    private String nombre;
    private String identificador;
    private String Telefono;
    private String Direccion;
    private boolean esProveedor;
    private int dinero;

    public Usuario(String nombre, String identificador, String Telefono, String Direccion, boolean esProveedor) {
        this.nombre = nombre;
        this.dinero = 0;
        this.identificador = identificador;
        //this.productos = new ArrayList<String>();
        //this.cantidades = new ArrayList<Integer>();
        this.esProveedor = esProveedor;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /*public ArrayList<String> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<String> productos) {
        this.productos = productos;
    }

    public ArrayList<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(ArrayList<Integer> cantidades) {
        this.cantidades = cantidades;
    }*/

    public boolean isEsProveedor() {
        return esProveedor;
    }

    public void setEsProveedor(boolean esProveedor) {
        this.esProveedor = esProveedor;
    }
    
    public void sumarDinero(int cantidad ){
        dinero += cantidad;
    }
    
    public int getDinero(){
        return dinero;
    }
    
    public void setDinero(int dinero){
        this.dinero = dinero;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
    
    
    
    //abstract public ArrayList<String> getProductosProveedor();
    //abstract public ArrayList<String> getNomProductosProveedor();
    //abstract public ArrayList<Integer> getCantidadProductosProveedor();
    //abstract public int getGananciasProveedor();
    //abstract public ArrayList<String> getNombreProductosComprador();
    //abstract public ArrayList<Integer> getCantidadProductosComprador();
    //abstract public int getTotalComprador();
    

    
}
