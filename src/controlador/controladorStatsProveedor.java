/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import vista.ProveedorStadisticasVista;

/**
 *
 * @author pablo
 */
public class controladorStatsProveedor {
    private UsuarioDao modelo;
    private ProveedorStadisticasVista vista;

    public controladorStatsProveedor(UsuarioDao modelo, ProveedorStadisticasVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        cargarInfo();
    }
    
    public void cargarInfo(){
        vista.getLblNombreProducto().setText(modelo.getUsuario().getNomProductosProveedor().get(0));
        vista.getLblCantidad().setText(String.valueOf(modelo.getUsuario().getCantidadProductosProveedor().get(0)));
        vista.getLblNomComprador().setText(String.valueOf(modelo.getUsuario().getProductosProveedor().get(0)));
        vista.getLblTotal().setText(String.valueOf(modelo.getUsuario().getDinero()));
        
    
    }
    
}
