/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_4;

import DAO.UsuarioDao;
import controlador.controladorLogin;
import vista.LoginVista;

/**
 *
 * @author pablo
 */
public class Proyecto_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        LoginVista vista = new LoginVista();
        vista.setVisible(true);
        
        UsuarioDao dao = new UsuarioDao();
        
        controladorLogin controlador = new controladorLogin(dao, vista);
    }
    
}
