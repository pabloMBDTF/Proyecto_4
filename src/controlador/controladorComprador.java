/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.UsuarioDao;
import model.Producto;
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
