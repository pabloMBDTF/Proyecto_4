/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.CompraUsu;
import model.VentaProv;
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
    private ArrayList<CompraUsu> comprass;

    public controladorStatsComprador(UsuarioDao modelo, CompradorEstadisticasVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        comprass = new ArrayList<CompraUsu>();
        compras = 0;
        comprasActual = 0;
        
        vista.addBtnSiguienteListener(new btnSiguiente());
        vista.addBtnAtrasListener(new btnAtras());
        vista.addBtnVolverListener(new btnVolver());
        vista.getLblNombre().setText(modelo.getUsuario().getNombre());
        
        cargarCompras();
        cargarInfo(comprasActual);
        cargarComprasIndex();
        activarBotones();
    }
    
    public void cargarInfo(int ventaIndex){
        vista.getLblNombreProducto().setText(comprass.get(ventaIndex).getNombreProducto());
        vista.getLblCantidad().setText(String.valueOf(comprass.get(ventaIndex).getCantidad()));
        vista.getLblTotalCompra().setText(String.valueOf(comprass.get(ventaIndex).getTotalCompra()));
        vista.getLblNombreProveedor().setText(comprass.get(ventaIndex).getNombreProveedor());
        
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
    
    
    public void cargarComprasIndex(){
        compras = comprass.size();
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
    
  public void cargarCompras() {
    comprass.clear(); 
    for (CompraUsu compra : modelo.getTienda().getCompras()) {
        if (modelo.getUsuario().getIdentificador().equals(compra.getIdComprador())) {
            comprass.add(compra);
        }
    }
}
    
    
}
