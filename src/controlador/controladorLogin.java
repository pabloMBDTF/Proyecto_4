/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;
import vista.CrearUsuVista;
import vista.LoginVista;
import vista.PrincipalProvVista;

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
        vista.addBtnIngresarPerfilListener(new btnIngresarListener());
    }
    
    
    class btnCrearPerfilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CrearUsuVista view = new CrearUsuVista();
            view.setVisible(true);
            controladorCrearUsuario cont = new controladorCrearUsuario(modelo, view);
            vista.dispose();
        }
    }
    
    class btnIngresarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (modelo != null) {
                ArrayList<Usuario> usuarios = modelo.getTienda().getUsuarios();

                
                if (!usuarios.isEmpty()) {

                    
                    String nombre = vista.getNombreJField().getText();
                    String identificador = vista.getIdentificadorJField().getText();

                    if (!nombre.isEmpty() && !identificador.isEmpty()) {
                        for (Usuario usu : usuarios) {
                            if (usu.getIdentificador().equals(identificador) && usu.getNombre().equals(nombre)){
                                System.out.println("si");
                                if (usu.isEsProveedor() == true){
                                    modelo.setUsuario(usu);
                                    PrincipalProvVista ventana = new PrincipalProvVista();
                                    ventana.setVisible(true);
                                    vista.dispose();
                                    controladorProveedor cont = new controladorProveedor(modelo, ventana);
                                    
                                    
                                    
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Nombre o identificador incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre e Identificador no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "No se encontraron datos disponibles, cargue los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
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
