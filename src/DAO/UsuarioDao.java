/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import model.CompraUsu;
import model.Producto;
import model.Supermercado;
import model.UsuComprador;
import model.UsuProveedor;
import model.Usuario;
import model.VentaProv;

/**
 *
 * @author pablo
 */
public class UsuarioDao implements InterfaceUsuarioDao{
    
    private Supermercado tienda = new Supermercado(); 
    private Usuario usuarioActual;
    private Producto productoActual;
    

    @Override
    public void crearUsuario(String nombre, String id, String telefono, String direccion, boolean esProveedor) {
        if (esProveedor == true){
            tienda.getUsuarios().add(new UsuProveedor(nombre, id, telefono, direccion, esProveedor));
            System.out.println("usuario agregado");
        }else{
            tienda.getUsuarios().add(new UsuComprador(nombre, id, telefono, direccion, esProveedor));
            System.out.println("usuario agregado");
        }
        
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

    public Producto getProductoActual() {
        return productoActual;
    }

    public void setProductoActual(Producto productoActual) {
        this.productoActual = productoActual;
    }
    
    
    

    @Override
    public void crearProducto(String nombre, String idProducto, String idProveedor, int cantidad, int precio) {
        Producto producto = new Producto(nombre, idProducto, idProveedor, cantidad, precio);
        tienda.getProductos().add(producto);
        System.out.println("producto agregado.");
        
    }

    @Override
    public Usuario getProvedor(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El identificador no puede ser nulo");
        }

        for (Usuario usuario : tienda.getUsuarios()) {
            if (id.equalsIgnoreCase(usuario.getIdentificador())) {
                return usuario;
            }
        }

        throw new NoSuchElementException("Proveedor no encontrado con el identificador: " + id);
    }

    @Override
    public void realizarCompra(String idProdducto, int cantidad, String idVendedor) {
        for(Producto producto : tienda.getProductos()){
            if(producto.getIdProducto().equals(idProdducto)){
                UsuProveedor prov =(UsuProveedor) getProvedor(idVendedor);
                int cantidadActual = producto.getCantidad();
                int cantidadCompra = cantidadActual-cantidad;
                //producto.setCantidad(cantidadCompra);
                System.out.println(producto.getCantidad());
                //prov.getNomProductosProveedor().add(producto.getNombre());
                //prov.getCantidadProductosProveedor().add(producto.getCantidad());
                //prov.getProductosProveedor().add(usuarioActual.getNombre());
                prov.sumarDinero(producto.getPrecio()*cantidad);
                
                VentaProv venta = new VentaProv(producto.getNombre(),
                        usuarioActual.getNombre(),
                        usuarioActual.getDireccion(),
                        usuarioActual.getTelefono(), 
                        prov.getIdentificador(), 
                        cantidad, 
                        cantidad*producto.getPrecio());
                tienda.getVentas().add(venta);
                
                
                
                
                
                //System.out.println(prov.getNomProductosProveedor());
                //System.out.println(prov.getCantidadProductosProveedor());
                //System.out.println(prov.getProductosProveedor());
                // para el comprador 
                //usuarioActual.getNombreProductosComprador().add(producto.getNombre());
                //usuarioActual.getCantidadProductosComprador().add(producto.getCantidad());
                usuarioActual.sumarDinero(producto.getPrecio()*cantidad);
                
                
                
                CompraUsu compra = new CompraUsu(producto.getNombre(),prov.getNombre(), cantidad, producto.getPrecio()*cantidad, usuarioActual.getIdentificador());
                tienda.getCompras().add(compra);
                
                producto.setCantidad(cantidadCompra);
                
            
            }
        }
    }

    @Override
    public Producto getProducto(String idproducto) {
        if (idproducto == null) {
            throw new IllegalArgumentException("El identificador no puede ser nulo");
        }

        for (Producto producto : tienda.getProductos()) {
            if (idproducto.equalsIgnoreCase(producto.getIdProducto())) {
                return producto;
            }
        }

        throw new NoSuchElementException("Proveedor no encontrado con el identificador: " + idproducto);
        
    }

    @Override
    public void actualizarProductos(String nombre, int cantidad, int precio) {
        productoActual.setNombre(nombre);
        productoActual.setCantidad(cantidad);
        productoActual.setPrecio(precio);
        System.out.println("tirnqui");
    }

    
    
    
    
    
    
}
