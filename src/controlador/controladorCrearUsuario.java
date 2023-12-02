/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.CrearUsuVista;

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
    }
    
    class btnCrearUsulistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           
            modelo.crearUsuario(vista.getNombreJField().getText(), Integer.parseInt(vista.getIdentificadorJField().getText()), true);
            System.out.println(modelo.getTienda().getUsuarios());
        }
    
    }
    
    
    
}
