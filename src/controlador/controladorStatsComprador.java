/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.CompradorEstadisticasVista;
import vista.PrincipalProvVista;
import vista.PrincipalUsuVista;

/**
 *
 * @author pablo
 */
public class controladorStatsComprador {
    
    private UsuarioDao modelo;
    private CompradorEstadisticasVista vista;
    private int compras, comprasActual;

    public controladorStatsComprador(UsuarioDao modelo, CompradorEstadisticasVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        compras = 0;
        comprasActual = 0;
        
        vista.addBtnSiguienteListener(new btnSiguiente());
        vista.addBtnAtrasListener(new btnAtras());
        vista.addBtnVolverListener(new btnVolver());
        vista.getLblNombre().setText(modelo.getUsuario().getNombre());
        
        cargarInfo(comprasActual);
        cargarVentas();
        activarBotones();
    }
    
    public void cargarInfo(int ventaIndex){
        vista.getLblNombreProducto().setText(modelo.getUsuario().getNombreProductosComprador().get(ventaIndex));
        vista.getLblCantidad().setText(String.valueOf(modelo.getUsuario().getCantidadProductosComprador().get(ventaIndex)));
        vista.getLblTotalGastado().setText(String.valueOf(modelo.getUsuario().getDinero()));
    }
    
    class btnSiguiente implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            comprasActual +=1;
            cargarInfo(comprasActual);
            activarBotones();
        }
    
    }
    
    class btnAtras implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            comprasActual -=1;
            cargarInfo(comprasActual);
            activarBotones();
        }
    
    }
    
    
    class btnVolver implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            PrincipalUsuVista ventana = new PrincipalUsuVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorComprador cont = new controladorComprador(modelo, ventana);
        }
    
    }
    
    
    public void cargarVentas(){
        compras = modelo.getUsuario().getCantidadProductosComprador().size();
    } 
    
    
    public void activarBotones(){
        if (comprasActual == 0) {
            vista.getBtnAtras().setEnabled(false);
        }else{
            vista.getBtnAtras().setEnabled(true);
        }
        
        
        if (comprasActual == compras-1) {
            vista.getBtnSiguiente().setEnabled(false);
        }else{
            vista.getBtnSiguiente().setEnabled(true);
        }
        
    }
    
    
}
