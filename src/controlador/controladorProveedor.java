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
import model.VentaProv;
import vista.ActualizarProductoVista;
import vista.ActualizarUsuarioVista;
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
        vista.getLblNombre().setText(modelo.getUsuario().getNombre());
        vista.addBtnCrearListener(new CrearProducto());
        vista.addBtnSalirListener(new SalirListener());
        vista.addBtnGananciasListener(new GananciasListener());
        vista.addBtnActualizarProductoListener(new ActualizarProductoListener());
        vista.addBtnEliminarProductoListener(new btnEliminarProducto());
        vista.addBtnPerfilListener(new btnModificarUsuario());
        agregarRegistrosLista();
        
    }
    
    
    class CrearProducto implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
             // Obtener los valores de los campos
            String nombre = vista.getNombreJField().getText();
            String idProducto = vista.getIdJField().getText();

            // Verificar que ningún campo esté vacío
            if (nombre.isEmpty() || idProducto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nombre e ID del producto no pueden estar vacíos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si hay campos vacíos
            }

            // Verificar que el ID del producto no exista en la lista de productos
            if (existeProductoConId(idProducto)) {
                JOptionPane.showMessageDialog(null, "El ID del producto ya existe. Por favor, elija otro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si el ID del producto ya existe
            }

            // Obtener valores adicionales
            Object spinnerValueCantidad = vista.getjSpinnerCantidad().getModel().getValue();
            int cantidad = Integer.parseInt(spinnerValueCantidad.toString());

            Object spinnerValuePrecio = vista.getjSpinnerPrecio().getModel().getValue();
            int precio = Integer.parseInt(spinnerValuePrecio.toString());

            // Crear el producto si todas las validaciones pasan
            modelo.crearProducto(nombre, idProducto, modelo.getUsuario().getIdentificador(), cantidad, precio);
            modelo.guardarProductoEnArchivo();

            // Limpiar campos
            vista.getIdJField().setText("");
            vista.getNombreJField().setText("");
            vista.getjSpinnerCantidad().setValue(1);

            // Actualizar la lista de productos en la vista
            agregarRegistrosLista();
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
            // Verificar si hay ventas asociadas al usuario actual como proveedor
            String idUsuarioActual = modelo.getUsuario().getIdentificador();
            boolean tieneVentas = tieneVentasAsociadas(idUsuarioActual);

            if (tieneVentas) {
                // Si hay ventas, abrir la ventana de estadísticas
                ProveedorStadisticasVista ventana = new ProveedorStadisticasVista();
                ventana.setVisible(true);
                vista.dispose();
                controladorStatsProveedor cont = new controladorStatsProveedor(modelo, ventana);
            } else {
                // Mostrar ventana de advertencia
                JOptionPane.showMessageDialog(null, "No hay ventas asociadas al usuario actual", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    
    
    }
    
    
    class ActualizarProductoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int filaSeleccionada = vista.getjTableProductos().getSelectedRow();
            if (filaSeleccionada != -1) {
                String idProducto = (String) vista.getModeloTabla().getValueAt(filaSeleccionada, 0);
                for(Producto producto : modelo.getTienda().getProductos()){
                    if (producto.getIdProducto() == idProducto) {
                        modelo.setProductoActual(producto);
                        ActualizarProductoVista ventana = new ActualizarProductoVista();
                        ventana.setVisible(true);
                        vista.dispose();
                        controladorActualizarProducto cont = new controladorActualizarProducto(modelo, ventana);
                        break;
                    }
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un producto a actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            
        }
    }
    
    class btnEliminarProducto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int filaSeleccionada = vista.getjTableProductos().getSelectedRow();
            if (filaSeleccionada != -1) {
                // Obtener el ID del producto seleccionado
                String idProducto = (String) vista.getModeloTabla().getValueAt(filaSeleccionada, 0);

                // Mostrar un cuadro de diálogo de confirmación
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar el producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    // El usuario ha confirmado la eliminación
                    modelo.eliminarProducto(idProducto);
                    agregarRegistrosLista();
                }
            } else {
                // No se ha seleccionado ningún producto
                JOptionPane.showMessageDialog(null, "Seleccione un producto a eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
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
    
    private boolean existeProductoConId(String idProducto) {
        for (Producto producto : modelo.getTienda().getProductos()) {
            if (producto.getIdProducto().equals(idProducto)) {
                return true; // El ID del producto ya existe
            }
        }
        return false; // El ID del producto no existe
    }
    
    
    private boolean tieneVentasAsociadas(String idProveedor) {
        for (VentaProv venta : modelo.getTienda().getVentas()) {
            if (venta.getIdVendedor().equals(idProveedor)) {
                return true; // Hay ventas asociadas al proveedor
            }
        }
        return false; // No hay ventas asociadas al proveedor
    }
}
