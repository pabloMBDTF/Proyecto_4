/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
    private boolean datosCargados = false;
    

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
    public void eliminarUsuario() {
        tienda.getUsuarios().remove(usuarioActual);
        System.out.println("eliminado");
    }

    @Override
    public void actualizarPersona(String nombre,  String direccion, String telefono) {
        usuarioActual.setNombre(nombre);
        usuarioActual.setTelefono(telefono);
        usuarioActual.setDireccion(direccion);
        System.out.println("done");
        
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
                System.out.println(producto.getCantidad());
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
                usuarioActual.sumarDinero(producto.getPrecio()*cantidad);
                CompraUsu compra = new CompraUsu(producto.getNombre(),prov.getNombre(), cantidad, producto.getPrecio()*cantidad, usuarioActual.getIdentificador());
                tienda.getCompras().add(compra);
                producto.setCantidad(cantidadCompra);
                guardarCompraEnArchivo();
                guardarUsuarioEnArchivo();
                
            
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            

            for (Usuario usuario : tienda.getUsuarios()) {
                bw.write("Proveedor: " + usuario.isEsProveedor());
                bw.newLine();
                bw.write("Nombre: " + usuario.getNombre());
                bw.newLine();
                bw.write("Identificador: " + usuario.getIdentificador());
                bw.newLine();
                bw.write("Teléfono: " + usuario.getTelefono());
                bw.newLine();
                bw.write("Dirección: " + usuario.getDireccion());
                bw.newLine();
                bw.write("Dinero: " + usuario.getDinero());
                bw.newLine();
                bw.newLine(); 
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarProductoEnArchivo() {
        String archivo = "src/archivosPersistentes/productos.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            

            for (Producto producto : tienda.getProductos()) {
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
                bw.newLine(); 
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarCompraEnArchivo() {
        String archivo = "src/archivosPersistentes/compras.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            

            for (CompraUsu compra : tienda.getCompras()) {
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
                bw.newLine(); 
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarVentaEnArchivo() {
        String archivo = "src/archivosPersistentes/ventas.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            

            for (VentaProv venta : tienda.getVentas()) {
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
                bw.newLine(); 
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarProducto(String idProducto) {
        ArrayList<Producto> productos = tienda.getProductos();

        for (Producto producto : productos) {
            if (producto.getIdProducto().equals(idProducto)) {
                productos.remove(producto);
                break;  
            }
        }
    }
    
    private void cargarUsuariosDesdeArchivo() {
        String archivoUsuarios = "src/archivosPersistentes/usuarios.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Proveedor:")) {
                    boolean esProveedor = Boolean.parseBoolean(linea.split(":")[1].trim());
                    String nombre = br.readLine().split(":")[1].trim();
                    String identificador = br.readLine().split(":")[1].trim();
                    String telefono = br.readLine().split(":")[1].trim();
                    String direccion = br.readLine().split(":")[1].trim();
                    int dinero = Integer.parseInt(br.readLine().split(":")[1].trim());

                    if (esProveedor) {
                        UsuProveedor prov = new UsuProveedor(nombre, identificador, telefono, direccion, true);
                        prov.setDinero(dinero);
                        tienda.getUsuarios().add(prov);
                    } else {
                        UsuComprador usuComp = new UsuComprador(nombre, identificador, telefono, direccion, false);
                        usuComp.setDinero(dinero);
                        tienda.getUsuarios().add(usuComp);
                    
                    }
                    

                    
                    br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void cargarProductosDesdeArchivo() {
        String archivoProductos = "src/archivosPersistentes/productos.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoProductos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                
                if (linea.startsWith("Nombre:")) {
                    
                    String nombre = linea.split(":")[1].trim();
                    String idProducto = br.readLine().split(":")[1].trim();
                    String idProveedor = br.readLine().split(":")[1].trim();
                    System.out.println(idProveedor);// Asegurar que no haya espacios en blanco
                    int cantidad = Integer.parseInt(br.readLine().split(":")[1].trim());
                    int precio = Integer.parseInt(br.readLine().split(":")[1].trim());

                    
                    Producto nuevoProducto = new Producto(nombre, idProducto, idProveedor, cantidad, precio);
                    tienda.getProductos().add(nuevoProducto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void cargarComprasDesdeArchivo() {
        String archivoCompras = "src/archivosPersistentes/compras.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCompras))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String nombreProducto = linea.split(":")[1].trim();

                            String proveedorLinea = br.readLine();
                String proveedor = proveedorLinea.split(":")[1].trim();


                String cantidadLinea = br.readLine();
                int cantidad = Integer.parseInt(cantidadLinea.split(":")[1].trim());


                String totalLinea = br.readLine();
                double totalCompra = Double.parseDouble(totalLinea.split(":")[1].trim());


                String compradorLinea = br.readLine();
                String idComprador = compradorLinea.split(":")[1].trim();


                CompraUsu compra = new CompraUsu(nombreProducto, proveedor, cantidad, (int) totalCompra, idComprador);


                tienda.getCompras().add(compra);


                br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


private void cargarVentasDesdeArchivo() {
    String archivoVentas = "src/archivosPersistentes/ventas.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(archivoVentas))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("Producto:")) {
                String nombreProducto = linea.split(":")[1].trim();

                
                String compradorLinea = br.readLine();
                String idComprador = compradorLinea.split(":")[1].trim();

                
                String direccionLinea = br.readLine();
                String direccion = direccionLinea.split(":")[1].trim();

                
                String telefonoLinea = br.readLine();
                String numero = telefonoLinea.split(":")[1].trim();

                
                String proveedorLinea = br.readLine();
                String idVendedor = proveedorLinea.split(":")[1].trim();

                
                String cantidadLinea = br.readLine();
                int cantidad = Integer.parseInt(cantidadLinea.split(":")[1].trim());

                
                String totalVentaLinea = br.readLine();
                double totalVenta = Double.parseDouble(totalVentaLinea.split(":")[1].trim());

                
                br.readLine();

                VentaProv venta = new VentaProv(nombreProducto, idComprador, direccion, numero, idVendedor, cantidad, (int) totalVenta);
                tienda.getVentas().add(venta);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void cargarDatosDesdeArchivo() {
        if (!datosCargados) {
            cargarUsuariosDesdeArchivo();
            cargarProductosDesdeArchivo();
            cargarComprasDesdeArchivo();
            cargarVentasDesdeArchivo();
            datosCargados = true;
        }
        

    }
    
    
    
    
    
    
    
}
