/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
        String nombre = vista.getNombreJField().getText();
        String cantidadStr = vista.getCantidadJField().getText();
        String precioStr = vista.getPrecioJField().getText();

        // Verificar que todos los campos estén llenos
        if (nombre.isEmpty() || cantidadStr.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

        try {
            // Verificar que cantidad y precio sean números válidos
            int cantidad = Integer.parseInt(cantidadStr);
            int precio = Integer.parseInt(precioStr);

            // Realizar la actualización del producto
            modelo.actualizarProductos(nombre, cantidad, precio);
            modelo.setProductoActual(null);

            // Crear y mostrar la nueva ventana
            PrincipalProvVista ventana = new PrincipalProvVista();
            ventana.setVisible(true);
            vista.dispose();

            // Crear el nuevo controlador
            controladorProveedor cont = new controladorProveedor(modelo, ventana);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La cantidad y el precio deben ser números válidos.");
        }
    }
    
    }
    
    
    class btnVolverProducto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            PrincipalProvVista ventana = new PrincipalProvVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorProveedor cont = new controladorProveedor(modelo, ventana);
        }
    
    }
    
    
    
    
}
