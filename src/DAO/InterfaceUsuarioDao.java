package DAO;

import java.util.ArrayList;
import model.Producto;
import model.UsuProveedor;
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
    public void crearUsuario(String nombre, String id, String telefono, String Direccion, boolean esProveedor);
    public void eliminarUsuario(String id, boolean proveedor);
    public void actualizarPersona(String nombre,  Usuario usu );
    public void crearProducto(String nombre, String idProducto, String idProveedor, int cantidad, int precio );
    public Usuario getProvedor(String id);
    public void realizarCompra(String idProdducto, int cantidad, String idVendedor);
    public Producto getProducto(String idproducto);
    public void actualizarProductos(String nombre, int cantidad, int Precio);
    //public ArrayList<Usuario> getUsuarios(String tipo);
    //public Usuario getPersona(String nombre, boolean esProveedor);
    
    public void guardarUsuarioEnArchivo();
    public void guardarProductoEnArchivo();
    
}
