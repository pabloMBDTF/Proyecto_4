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
public class controladorLogin {
    
    private UsuarioDao modelo;
    private LoginVista vista;

    public controladorLogin(UsuarioDao modelo, LoginVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.addBtnCrearPerfilListener(new btnCrearPerfilListener());
    }
    
    
    class btnCrearPerfilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CrearUsuVista view = new CrearUsuVista();
            view.setVisible(true);
            controladorCrearUsuario cont = new controladorCrearUsuario(modelo, view);
        }
    
    
    }
    

    public UsuarioDao getModelo() {
        return modelo;
    }

    public void setModelo(UsuarioDao modelo) {
        this.modelo = modelo;
    }

    public LoginVista getVista() {
        return vista;
    }

    public void setVista(LoginVista vista) {
        this.vista = vista;
    }
    
    
    
    
}
