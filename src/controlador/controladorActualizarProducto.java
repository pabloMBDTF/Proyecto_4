/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import vista.ActualizarProductoVista;

/**
 *
 * @author pablo
 */
public class controladorActualizarProducto {
    
    private UsuarioDao modelo;
    private ActualizarProductoVista vista;

    public controladorActualizarProducto(UsuarioDao modelo, ActualizarProductoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    
    
    
}
