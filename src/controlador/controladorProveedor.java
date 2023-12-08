/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Producto;
import vista.LoginVista;
import vista.PrincipalProvVista;
import vista.ProveedorStadisticasVista;

/**
 *
 * @author pablo
 */
public class controladorProveedor {
    
    private UsuarioDao modelo;
    private PrincipalProvVista vista;

    public controladorProveedor(UsuarioDao modelo, PrincipalProvVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.addBtnCrearListener(new CrearProducto());
        vista.addBtnSalirListener(new SalirListener());
        vista.addBtnGananciasListener(new GananciasListener());
        agregarRegistrosLista();
        
    }
    
    
    class CrearProducto implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Object spinnerValueCantidad = vista.getjSpinnerCantidad().getModel().getValue();
            int cantidad = Integer.parseInt(spinnerValueCantidad.toString());
            Object spinnerValuePrecio = vista.getjSpinnerPrecio().getModel().getValue();
            int precio = Integer.parseInt(spinnerValuePrecio.toString());
            System.out.println(cantidad);
            modelo.crearProducto(vista.getNombreJField().getText(), vista.getIdJField().getText(), modelo.getUsuario().getIdentificador(), cantidad, precio);
            vista.getIdJField().setText("");
            vista.getNombreJField().setText("");
            vista.getjSpinnerCantidad().setValue(1);
            agregarRegistrosLista();
        }
    }
    
    
    class SalirListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginVista ventana = new LoginVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorLogin cont = new controladorLogin(modelo, ventana);
        }
    
    
    }
    
    
    class GananciasListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ProveedorStadisticasVista ventana = new ProveedorStadisticasVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorStatsProveedor cont = new controladorStatsProveedor(modelo, ventana);
        }
    
    
    }
    
    
    class ActualizarProductoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    
    
    }
    
    void agregarRegistrosLista(){
        vista.getModeloTabla().setRowCount(0);
        for(Producto producto : modelo.getTienda().getProductos()){
            if(producto != null && producto.getIdProveedor() == modelo.getUsuario().getIdentificador()){
                
                agregarFilaTabla(producto);
            }
        }
        
    }
    
    void agregarFilaTabla(Producto producto) {
           
        Object[] fila = {
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getCantidad(),
                producto.getPrecio()
        };
        vista.getModeloTabla().addRow(fila);
    }
    
}
