/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ActualizarProductoVista;
import vista.PrincipalProvVista;

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
        vista.addBtnActualizarListener(new btnActualizarProducto());
        vista.addBtnVolverListener(new btnVolverProducto());
        cardarFields();
    }
    
    
    public void cardarFields(){
        vista.getNombreJField().setText(modelo.getProductoActual().getNombre());
        vista.getCantidadJField().setText(String.valueOf(modelo.getProductoActual().getCantidad()));
        vista.getPrecioJField().setText(String.valueOf(modelo.getProductoActual().getPrecio()));
        
    
    }
    
    class btnActualizarProducto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            modelo.actualizarProductos(vista.getNombreJField().getText(), Integer.parseInt(vista.getCantidadJField().getText()), Integer.parseInt(vista.getPrecioJField().getText()));
            modelo.setProductoActual(null);
            PrincipalProvVista ventana = new PrincipalProvVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorProveedor cont = new controladorProveedor(modelo, ventana);
        }
    
    }
    
    
    class btnVolverProducto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("hola");
        }
    
    }
    
    
    
    
}
