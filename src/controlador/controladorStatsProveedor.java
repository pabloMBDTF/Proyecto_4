/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.VentaProv;
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
    private ArrayList<VentaProv> ventass;
    
    
    public controladorStatsProveedor(UsuarioDao modelo, ProveedorStadisticasVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        ventass = new ArrayList<VentaProv>();
        ventas = 0;
        ventaActual = 0;
        cargarVentass();
        
        vista.addBtnSiguienteListener(new btnSiguiente());
        vista.addBtnAtrasListener(new btnAtrasListener());
        vista.addBtnVolverListener(new btnVolver());
        
        cargarInfo(ventaActual);
        cargarVentas();
        activarBotones();
    }
    
    public void cargarInfo(int ventaIndex){
        vista.getLblNombreProducto().setText(ventass.get(ventaIndex).getNombreProducto());
        vista.getLblCantidad().setText(String.valueOf(ventass.get(ventaIndex).getCantidad()));
        vista.getLblNomComprador().setText(ventass.get(ventaIndex).getNombreComprador());
        vista.getLblTotal().setText(String.valueOf(modelo.getUsuario().getDinero()));
        vista.getLblDireccion().setText(ventass.get(ventaIndex).getDireccionComprador());
        vista.getLblTelefono().setText(ventass.get(ventaIndex).getNumero());
        vista.getLblTotalVenta().setText(String.valueOf(ventass.get(ventaIndex).getTotalVenta()));
        
    
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
        ventas = ventass.size();
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
    
    
 public void cargarVentass() {
    ventass.clear(); // Limpiar la lista antes de cargar nuevas ventas
    for (VentaProv venta : modelo.getTienda().getVentas()) {
        if (modelo.getUsuario().getIdentificador().equals(venta.getIdVendedor())) {
            ventass.add(venta);
        }
    }
}   
}
