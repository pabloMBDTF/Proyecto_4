/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import vista.CrearUsuVista;
import vista.LoginVista;

/**
 *
 * @author pablo
 */

/*
Pablo Becerrra G. - 2243506 - pablo.becerra@correounivalle.edu.co
Tiffany Torres F. - 2241747 - tiffany.torre@correounivalle.edu.do
David Rengifo J. - 2241016 - julian.david.rengifo@correounivalle.edu.co

Fundamentos de programacion orientada a eventos

*/
public class controladorCrearUsuario {
    private UsuarioDao modelo;
    private CrearUsuVista vista;

    public controladorCrearUsuario(UsuarioDao modelo, CrearUsuVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.addBtnCrearListener(new btnCrearUsulistener());
        vista.addBtnVolverListener(new btnVolverListener());
    }
    
    class btnCrearUsulistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           
            
            String nombre = vista.getNombreJField().getText();
            String identificador = vista.getIdentificadorJField().getText();
            String telefono = vista.getTelefonoJField().getText();
            String direccion = vista.getDireccionJField().getText();
            boolean esProveedor = vista.getjCheckBoxProveedor().isSelected();


            if (nombre.isEmpty() || identificador.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si hay campos vacíos
            }


            if (existeUsuarioConIdentificador(identificador)) {
                JOptionPane.showMessageDialog(null, "El identificador ya existe. Por favor, elija otro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; 
            }


            if (!esNumero(telefono)) {
                JOptionPane.showMessageDialog(null, "El teléfono debe ser un número válido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; 
            }


            modelo.crearUsuario(nombre, identificador, telefono, direccion, esProveedor);
            modelo.guardarUsuarioEnArchivo();
            System.out.println(modelo.getTienda().getUsuarios());


            LoginVista ventana = new LoginVista();
            ventana.setVisible(true);
            controladorLogin cont = new controladorLogin(modelo, ventana);
            vista.dispose();
        }
    }
    
    class btnVolverListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginVista ventana = new LoginVista();
            ventana.setVisible(true);
            controladorLogin cont = new controladorLogin(modelo, ventana);
            vista.dispose();
        }
    }
    
    private boolean existeUsuarioConIdentificador(String identificador) {
        for (Usuario usuario : modelo.getTienda().getUsuarios()) {
            if (usuario.getIdentificador().equals(identificador)) {
                return true; 
            }
        }
        return false;
    }
    
    
    private boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    
}
