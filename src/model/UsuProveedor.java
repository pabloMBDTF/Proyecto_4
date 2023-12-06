/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class UsuProveedor extends Usuario {
    
    //private ArrayList<String> nombreProductos;
    //private ArrayList<String> nombreComprador;
    //private ArrayList<Integer> cantidad;
    private int totalComprado;

    public UsuProveedor(String nombre, String identificador, String telefono, String direccion, boolean esProveedor) {
        super(nombre, identificador, telefono, direccion, esProveedor);
        //this.nombreProductos = new ArrayList<String>();
        //this.nombreComprador = new ArrayList<String>();
        //this.cantidad = new ArrayList<Integer>();
        //this.totalComprado = 0;
    }

    /*@Override
    public ArrayList<String> getProductosProveedor() {
        return null;
    }

    @Override
    public ArrayList<String> getNomProductosProveedor() {
        return null;
    }

    @Override
    public ArrayList<Integer> getCantidadProductosProveedor() {
        return null;
    }

    @Override
    public int getGananciasProveedor() {
        return totalComprado;
    }

    @Override
    public ArrayList<String> getNombreProductosComprador() {
        return null;
    }

    @Override
    public ArrayList<Integer> getCantidadProductosComprador() {
        return null;
    }

    @Override
    public int getTotalComprador() {
        return 0;
    }*/
    
    
    
}
