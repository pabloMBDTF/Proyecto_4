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

/*
Pablo Becerrra G. - 2243506 - pablo.becerra@correounivalle.edu.co
Tiffany Torres F. - 2241747 - tiffany.torre@correounivalle.edu.do
David Rengifo J. - 2241016 - julian.david.rengifo@correounivalle.edu.co

Fundamentos de programacion orientada a eventos

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

                
                String cantidadIngresadaTexto = vista.getCantidadJField().getText();

                
                if (esNumero(cantidadIngresadaTexto)) {
                    int cantidadIngresada = Integer.parseInt(cantidadIngresadaTexto);

                    
                    if (cantidadIngresada <= cantidadDisponible) {
                        // Realizar la compra
                        System.out.println(idProducto);
                        System.out.println(cantidadIngresada);
                        System.out.println(idComprador);

                        modelo.realizarCompra(idProducto, cantidadIngresada, idComprador);
                        modelo.guardarProductoEnArchivo();
                        agregarRegistrosLista();
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad ingresada es mayor que la cantidad disponible", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad ingresada no es un número válido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                
                JOptionPane.showMessageDialog(null, "No hay fila seleccionada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    
    }
    
    class btnHistorialListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        String idUsuarioActual = modelo.getUsuario().getIdentificador();
        boolean tieneCompras = tieneComprasAsociadas(idUsuarioActual);

            if (tieneCompras) {
                
                CompradorEstadisticasVista ventana = new CompradorEstadisticasVista();
                ventana.setVisible(true);
                vista.dispose();
                controladorStatsComprador cont = new controladorStatsComprador(modelo, ventana);
            } else {
                
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
                return true; 
            }
        }
        return false; 
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
