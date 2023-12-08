/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Producto;
import vista.CompradorEstadisticasVista;
import vista.LoginVista;
import vista.PrincipalUsuVista;
import vista.ProveedorStadisticasVista;

/**
 *
 * @author pablo
 */
public class controladorComprador {
    
    private PrincipalUsuVista vista;
    private UsuarioDao modelo;

    public controladorComprador(UsuarioDao modelo, PrincipalUsuVista vista) {
        this.vista = vista;
        this.modelo = modelo;
        agregarRegistrosLista();
        vista.getLblNombre().setText(modelo.getUsuario().getNombre());
        vista.addBtnComprarListener(new btnComprarListener());
        vista.addBtnSalirListener(new btnSalirListenner());
        vista.addBtnHistorialListener(new btnHistorialListener());
    }
    
    class btnComprarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int filaSeleccionada = vista.getjTableProductos().getSelectedRow();
            
            if (filaSeleccionada != -1) {
                String idProducto = (String) vista.getModelo().getValueAt(filaSeleccionada, 0);
                int cantidadDisponible = Integer.parseInt(vista.getModelo().getValueAt(filaSeleccionada, 3).toString());
                String idComprador = (String) vista.getModelo().getValueAt(filaSeleccionada, 1);

                // Obtener la cantidad ingresada desde la vista
                String cantidadIngresadaTexto = vista.getCantidadJField().getText();

                // Verificar si la cantidad ingresada es un número
                if (esNumero(cantidadIngresadaTexto)) {
                    int cantidadIngresada = Integer.parseInt(cantidadIngresadaTexto);

                    // Verificar si la cantidad ingresada es igual o menor que la cantidad disponible
                    if (cantidadIngresada <= cantidadDisponible) {
                        // Realizar la compra
                        System.out.println(idProducto);
                        System.out.println(cantidadIngresada);
                        System.out.println(idComprador);

                        modelo.realizarCompra(idProducto, cantidadIngresada, idComprador);
                        agregarRegistrosLista();
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad ingresada es mayor que la cantidad disponible", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad ingresada no es un número válido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                // No hay fila seleccionada
                JOptionPane.showMessageDialog(null, "No hay fila seleccionada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    
    }
    
    class btnHistorialListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CompradorEstadisticasVista ventana = new CompradorEstadisticasVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorStatsComprador cont = new controladorStatsComprador(modelo, ventana);
        }
    
    }
    
    class btnSalirListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginVista ventana = new LoginVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorLogin cont = new controladorLogin(modelo, ventana);
        }
    
    }
    
    
    
    
    void agregarRegistrosLista(){
        vista.getModelo().setRowCount(0);
        for(Producto producto : modelo.getTienda().getProductos()){
            agregarFilaTabla(producto);            
        }
        
    }
    
    void agregarFilaTabla(Producto producto) {
           
        Object[] fila = {
                producto.getIdProducto(),
                producto.getIdProveedor(),
                producto.getNombre(),
                producto.getCantidad(),
                producto.getPrecio()
        };
        vista.getModelo().addRow(fila);
    }
    
    
    private boolean esNumero(String cadena) {
    try {
        Integer.parseInt(cadena);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
    

    public PrincipalUsuVista getVista() {
        return vista;
    }

    public void setVista(PrincipalUsuVista vista) {
        this.vista = vista;
    }

    public UsuarioDao getModelo() {
        return modelo;
    }

    public void setModelo(UsuarioDao modelo) {
        this.modelo = modelo;
    }
    
    
    
}
