/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
                guardarVentaEnArchivo();
                
                
                
                
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
                guardarCompraEnArchivo();
                
            
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
    
    @Override
    public void guardarUsuarioEnArchivo() {
        String archivo = "src/archivosPersistentes/usuarios.txt";
        for(Usuario usuario: tienda.getUsuarios()){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.write("Nombre: " + usuario.getNombre());
                bw.newLine();
                bw.write("Identificador: " + usuario.getIdentificador());
                bw.newLine();
                bw.write("Teléfono: " + usuario.getTelefono());
                bw.newLine();
                bw.write("Dirección: " + usuario.getDireccion());
                bw.newLine();
                bw.write("Proveedor: " + usuario.isEsProveedor());
                bw.newLine();
                bw.write("Dinero: " + usuario.getDinero());
                bw.newLine();
                bw.newLine(); // Agregar una línea en blanco entre usuarios
                bw.flush();
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

    @Override
    public void guardarProductoEnArchivo() {
        for(Producto producto: tienda.getProductos()){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/archivosPersistentes/productos.txt", true))) {
                bw.write("Nombre: " + producto.getNombre());
                bw.newLine();
                bw.write("ID Producto: " + producto.getIdProducto());
                bw.newLine();
                bw.write("ID Proveedor: " + producto.getIdProveedor());
                bw.newLine();
                bw.write("Cantidad: " + producto.getCantidad());
                bw.newLine();
                bw.write("Precio: " + producto.getPrecio());
                bw.newLine();
                bw.newLine(); // Agregar una línea en blanco entre productos
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void guardarCompraEnArchivo() {
        String archivo = "src/archivosPersistentes/compras.txt";
        for(CompraUsu compra: tienda.getCompras()){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.write("Producto: " + compra.getNombreProducto());
                bw.newLine();
                bw.write("Proveedor: " + compra.getNombreProveedor());
                bw.newLine();
                bw.write("Cantidad: " + compra.getCantidad());
                bw.newLine();
                bw.write("Total: " + compra.getTotalCompra());
                bw.newLine();
                bw.write("Comprador: " + compra.getIdComprador());
                bw.newLine();
                bw.newLine(); // Agregar una línea en blanco entre compras
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   
    }

    @Override
    public void guardarVentaEnArchivo() {
        String archivo = "src/archivosPersistentes/ventas.txt";
        for(VentaProv venta: tienda.getVentas()){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.write("Producto: " + venta.getNombreProducto());
                bw.newLine();
                bw.write("Comprador: " + venta.getNombreComprador());
                bw.newLine();
                bw.write("Dirección: " + venta.getDireccionComprador());
                bw.newLine();
                bw.write("Teléfono: " + venta.getNumero());
                bw.newLine();
                bw.write("Proveedor: " + venta.getIdVendedor());
                bw.newLine();
                bw.write("Cantidad: " + venta.getCantidad());
                bw.newLine();
                bw.write("Total: " + venta.getTotalVenta());
                bw.newLine();
                bw.newLine(); // Agregar una línea en blanco entre ventas
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eliminarProducto(String idProducto) {
        ArrayList<Producto> productos = tienda.getProductos();

        for (Producto producto : productos) {
            if (producto.getIdProducto().equals(idProducto)) {
                productos.remove(producto);
                break;  // Importante: salir del bucle después de eliminar para evitar ConcurrentModificationException
            }
        }
    }
    
    
    
    
    
}
