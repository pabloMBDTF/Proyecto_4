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
    private int totalComprado;
    public UsuProveedor(String nombre, String identificador, String telefono, String direccion, boolean esProveedor) {
        super(nombre, identificador, telefono, direccion, esProveedor);      
    }
   
}
