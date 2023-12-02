/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
           
            modelo.crearUsuario(vista.getNombreJField().getText(), vista.getIdentificadorJField().getText(), vista.getjCheckBoxProveedor().isSelected());
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
    
    
}
