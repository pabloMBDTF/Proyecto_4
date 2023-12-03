/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import model.Producto;
import model.Supermercado;
import model.Usuario;

/**
 *
 * @author pablo
 */
public class UsuarioDao implements InterfaceUsuarioDao{
    
    private Supermercado tienda = new Supermercado(); 
    private Usuario usuarioActual;
    

    @Override
    public void crearUsuario(String nombre, String id, boolean esProveedor) {
        tienda.getUsuarios().add(new Usuario(nombre,id, esProveedor));
        System.out.println("usuario agregado");
        
    }

    @Override
    public void eliminarUsuario(String id, boolean proveedor) {
       
    }

    @Override
    public void actualizarPersona(String nombre, Usuario usu) {
        
    }

    //@Override
    //public ArrayList<Usuario> getUsuarios(String tipo) {
    //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

    //@Override
    //public Usuario getPersona(String nombre, boolean esProveedor) {
    //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

    public Supermercado getTienda() {
        return tienda;
    }

    public void setTienda(Supermercado tienda) {
        this.tienda = tienda;
    }

    public Usuario getUsuario() {
        return usuarioActual;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    @Override
    public void crearProducto(String nombre, String idProducto, String idProveedor, int cantidad, int precio) {
        Producto producto = new Producto(nombre, idProducto, idProveedor, cantidad, precio);
        tienda.getProductos().add(producto);
        System.out.println("producto agregado.");
        
    }

    
    
    
    
    
    
}
