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
import vista.LoginVista;
import vista.PrincipalUsuVista;

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
    }
    
    class btnComprarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int filaSeleccionada = vista.getjTableProductos().getSelectedRow();

            if (filaSeleccionada != -1) {
                String idProducto = (String) vista.getModelo().getValueAt(filaSeleccionada, 0);
                int cantidad = Integer.parseInt(vista.getModelo().getValueAt(filaSeleccionada, 3).toString());
                String idComprador = modelo.getUsuario().getIdentificador();
                System.out.println(idProducto );
                System.out.println(cantidad );
                System.out.println(idComprador );

                modelo.realizarCompra(idProducto, cantidad, idComprador);
                agregarRegistrosLista();
            } else {
                // No hay fila seleccionada
                JOptionPane.showMessageDialog(null, "No hay fila seleccionada", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
