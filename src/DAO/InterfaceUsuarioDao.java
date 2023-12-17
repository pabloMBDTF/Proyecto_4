package DAO;

import java.util.ArrayList;
import model.CompraUsu;
import model.Producto;
import model.UsuProveedor;
import model.Usuario;
import model.VentaProv;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


/*
Pablo Becerrra G. - 2243506 - pablo.becerra@correounivalle.edu.co
Tiffany Torres F. - 2241747 - tiffany.torre@correounivalle.edu.do
David Rengifo J. - 2241016 - julian.david.rengifo@correounivalle.edu.co

Fundamentos de programacion orientada a eventos

*/
/**
 *
 * @author pablo
 */
public interface InterfaceUsuarioDao {
    public void crearUsuario(String nombre, String id, String telefono, String Direccion, boolean esProveedor);
    public void eliminarUsuario();
    public void eliminarProducto(String idProducto);
    public void actualizarPersona(String nombre,  String direccion, String telefono );
    public void crearProducto(String nombre, String idProducto, String idProveedor, int cantidad, int precio );
    public Usuario getProvedor(String id);
    public void realizarCompra(String idProdducto, int cantidad, String idVendedor);
    public Producto getProducto(String idproducto);
    public void actualizarProductos(String nombre, int cantidad, int Precio);
    
    public void guardarUsuarioEnArchivo();
    public void guardarProductoEnArchivo();
    public void guardarCompraEnArchivo();
    public void guardarVentaEnArchivo();
}
