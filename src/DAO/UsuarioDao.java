/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import model.Usuario;

/**
 *
 * @author pablo
 */
public class UsuarioDao implements InterfaceUsuarioDao{

    @Override
    public void crearUsuario(String nombre, String id, boolean esProveedor) {
        
    }

    @Override
    public void eliminarUsuario(String id, boolean proveedor) {
       
    }

    @Override
    public void actualizarPersona(String nombre, Usuario usu) {
        
    }

    @Override
    public ArrayList<Usuario> getUsuarios(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario getPersona(String nombre, boolean esProveedor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
