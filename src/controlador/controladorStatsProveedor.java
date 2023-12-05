/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.LoginVista;
import vista.PrincipalProvVista;
import vista.ProveedorStadisticasVista;

/**
 *
 * @author pablo
 */
public class controladorStatsProveedor {
    private UsuarioDao modelo;
    private ProveedorStadisticasVista vista;
    private int ventas, ventaActual;

    public controladorStatsProveedor(UsuarioDao modelo, ProveedorStadisticasVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        ventas = 0;
        ventaActual = 0;
        
        vista.addBtnSiguienteListener(new btnSiguiente());
        vista.addBtnAtrasListener(new btnAtrasListener());
        vista.addBtnVolverListener(new btnVolver());
        
        cargarInfo(ventaActual);
        cargarVentas();
        activarBotones();
    }
    
    public void cargarInfo(int ventaIndex){
        vista.getLblNombreProducto().setText(modelo.getUsuario().getNomProductosProveedor().get(ventaIndex));
        vista.getLblCantidad().setText(String.valueOf(modelo.getUsuario().getCantidadProductosProveedor().get(ventaIndex)));
        vista.getLblNomComprador().setText(String.valueOf(modelo.getUsuario().getProductosProveedor().get(ventaIndex)));
        vista.getLblTotal().setText(String.valueOf(modelo.getUsuario().getDinero()));
        
    
    }
    
    class btnAtrasListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ventaActual -=1;
            cargarInfo(ventaActual);
            activarBotones();
            
        }
    }
    
    class btnVolver implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            PrincipalProvVista ventana = new PrincipalProvVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorProveedor cont = new controladorProveedor(modelo, ventana);
        }
    
    }
    
    
    
    class btnSiguiente implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ventaActual +=1;
            cargarInfo(ventaActual);
            activarBotones();
        }
    
    
    }
    
    
    public void cargarVentas(){
        ventas = modelo.getUsuario().getCantidadProductosProveedor().size();
    } 
    
    
    public void activarBotones(){
        if (ventaActual == 0) {
            vista.getBtnAtras().setEnabled(false);
        }else{
            vista.getBtnAtras().setEnabled(true);
        }
        
        
        if (ventaActual == ventas-1) {
            vista.getBtnSiguiente().setEnabled(false);
        }else{
            vista.getBtnSiguiente().setEnabled(true);
        }
        
    }
    
}
