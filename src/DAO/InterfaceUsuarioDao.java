package DAO;

import java.util.ArrayList;
import model.Usuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author pablo
 */
public interface InterfaceUsuarioDao {
    public void crearUsuario(String nombre, int id, boolean esProveedor);
    public void eliminarUsuario(String id, boolean proveedor);
    public void actualizarPersona(String nombre,  Usuario usu );
    //public ArrayList<Usuario> getUsuarios(String tipo);
    //public Usuario getPersona(String nombre, boolean esProveedor);
    
}
