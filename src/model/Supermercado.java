/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */

/*
Pablo Becerrra G. - 2243506 - pablo.becerra@correounivalle.edu.co
Tiffany Torres F. - 2241747 - tiffany.torre@correounivalle.edu.do
David Rengifo J. - 2241016 - julian.david.rengifo@correounivalle.edu.co

Fundamentos de programacion orientada a eventos

*/
public class Supermercado {
    
    
    private ArrayList<Usuario> usuarios;
    private ArrayList<Producto> productos;
    private ArrayList<CompraUsu> Compras;
    private ArrayList<VentaProv> ventas;

    public Supermercado() {
        this.usuarios = new ArrayList<Usuario>();
        this.productos = new ArrayList<Producto>();
        this.Compras = new ArrayList<CompraUsu>();
        this.ventas = new ArrayList<VentaProv>();
    }

    public ArrayList<CompraUsu> getCompras() {
        return Compras;
    }

    public void setCompras(ArrayList<CompraUsu> Compras) {
        this.Compras = Compras;
    }

    public ArrayList<VentaProv> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<VentaProv> ventas) {
        this.ventas = ventas;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    
    
    
    
}
