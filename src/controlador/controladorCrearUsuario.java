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
           
            // Obtener los valores de los campos
        String nombre = vista.getNombreJField().getText();
        String identificador = vista.getIdentificadorJField().getText();
        String telefono = vista.getTelefonoJField().getText();
        String direccion = vista.getDireccionJField().getText();
        boolean esProveedor = vista.getjCheckBoxProveedor().isSelected();

        // Verificar que ningún campo esté vacío
        if (nombre.isEmpty() || identificador.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si hay campos vacíos
        }

        // Verificar que el identificador no exista en la lista de usuarios
        if (existeUsuarioConIdentificador(identificador)) {
            JOptionPane.showMessageDialog(null, "El identificador ya existe. Por favor, elija otro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si el identificador ya existe
        }

        // Verificar que el teléfono sea un número
        if (!esNumero(telefono)) {
            JOptionPane.showMessageDialog(null, "El teléfono debe ser un número válido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si el teléfono no es un número válido
        }

        // Crear el usuario si todas las validaciones pasan
        modelo.crearUsuario(nombre, identificador, telefono, direccion, esProveedor);
        modelo.guardarUsuarioEnArchivo();
        System.out.println(modelo.getTienda().getUsuarios());

        // Crear y mostrar la ventana de Login
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
                return true; // El identificador ya existe
            }
        }
        return false; // El identificador no existe
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
