/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pablo
 */
public class CompraUsu {
    String nombreProducto; 
    String nombreProveedor; 
    String idComprador; 
    int cantidad; 
    int totalCompra; 

    public CompraUsu(String nombreProducto, String nombreProveedor, int cantidad, int totalCompra, String idComprador) {
        this.nombreProducto = nombreProducto;
        this.nombreProveedor = nombreProveedor;
        this.cantidad = cantidad;
        this.totalCompra = totalCompra;
        this.idComprador = idComprador;
    }

    public String getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(String idComprador) {
        this.idComprador = idComprador;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(int totalCompra) {
        this.totalCompra = totalCompra;
    }
    
    
}
