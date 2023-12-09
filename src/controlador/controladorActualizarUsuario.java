/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.ActualizarUsuarioVista;
import vista.LoginVista;
import vista.PrincipalProvVista;
import vista.PrincipalUsuVista;

/**
 *
 * @author pablo
 */
public class controladorActualizarUsuario {
    
    
    private UsuarioDao modelo;
    private ActualizarUsuarioVista vista;

    public controladorActualizarUsuario(UsuarioDao modelo, ActualizarUsuarioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.addBtnActualizarListener(new btnActualizar());
        vista.addBtnEliminarListener(new btnEliminarListener());
        vista.addBtnVolverListener(new btnVolverListener());
        cargarDatos();
    }
    
    class btnEliminarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar Su cuenta?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                modelo.eliminarUsuario();
                modelo.guardarUsuarioEnArchivo();
                LoginVista ventana = new LoginVista();
                ventana.setVisible(true);
                vista.dispose();
                controladorLogin cont = new controladorLogin(modelo, ventana); 
            }          
        }
    }
    
    
    class btnActualizar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = vista.getNombreJField().getText();
            String direccion = vista.getDireccionJField().getText();
            String telefono = vista.getTelefonoJField().getText();

            // Verificar que todos los campos estén llenos
            if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }

            // Verificar que el campo de teléfono sea un número
            try {
                Long.parseLong(telefono); // Intentar convertir a número
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El teléfono debe ser un número válido.");
                return;
            }

            // Realizar la actualización de la persona
            modelo.actualizarPersona(nombre, direccion, telefono);
            modelo.guardarUsuarioEnArchivo();

            // Crear y mostrar la nueva ventana y controlador según el tipo de usuario
            if (modelo.getUsuario().isEsProveedor()) {
                PrincipalProvVista ventana = new PrincipalProvVista();
                ventana.setVisible(true);
                vista.dispose();
                controladorProveedor cont = new controladorProveedor(modelo, ventana);
            } else {
                PrincipalUsuVista ventana = new PrincipalUsuVista();
                ventana.setVisible(true);
                vista.dispose();
                controladorComprador cont = new controladorComprador(modelo, ventana);
            }
        }
    }
    
    class btnVolverListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (modelo.getUsuario().isEsProveedor() == true) {
                PrincipalProvVista ventana = new PrincipalProvVista();
                ventana.setVisible(true);
                vista.dispose();
                controladorProveedor cont = new controladorProveedor(modelo, ventana);
            }else{
                PrincipalUsuVista ventana = new PrincipalUsuVista();
                ventana.setVisible(true);
                vista.dispose();
                controladorComprador cont = new controladorComprador(modelo, ventana);
            }
        }
    }
    
    
    public void cargarDatos(){
        vista.getNombreJField().setText(modelo.getUsuario().getNombre());
        vista.getTelefonoJField().setText(modelo.getUsuario().getTelefono());
        vista.getDireccionJField().setText(modelo.getUsuario().getDireccion());
    }
    
}
