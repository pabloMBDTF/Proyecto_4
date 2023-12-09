/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.CompraUsu;
import model.Producto;
import vista.ActualizarUsuarioVista;
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
        vista.addBtnEditarPerfilListener(new btnModificarUsuario());
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
            // Verificar si hay compras asociadas al usuario actual
        String idUsuarioActual = modelo.getUsuario().getIdentificador();
        boolean tieneCompras = tieneComprasAsociadas(idUsuarioActual);

            if (tieneCompras) {
                // Si hay compras, abrir la ventana de estadísticas
                CompradorEstadisticasVista ventana = new CompradorEstadisticasVista();
                ventana.setVisible(true);
                vista.dispose();
                controladorStatsComprador cont = new controladorStatsComprador(modelo, ventana);
            } else {
                // Mostrar ventana de advertencia
                JOptionPane.showMessageDialog(null, "No hay compras asociadas al usuario actual", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
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
    
    class btnModificarUsuario implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ActualizarUsuarioVista ventana = new ActualizarUsuarioVista();
            ventana.setVisible(true);
            vista.dispose();
            controladorActualizarUsuario cont = new controladorActualizarUsuario(modelo, ventana);
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
    
    
    private boolean tieneComprasAsociadas(String idUsuario) {
        for (CompraUsu compra : modelo.getTienda().getCompras()) {
            if (compra.getIdComprador().equals(idUsuario)) {
                return true; // Hay compras asociadas al usuario
            }
        }
        return false; // No hay compras asociadas al usuario
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
