package dao;

import exception.FerreteriaException;
import java.util.List;
import modelo.Producto;


public interface ProductoDao extends Crud {

    Producto obtenerProducto(String nombre, double precio, int stock, String estado) throws FerreteriaException;

    List<Producto> obtenerProductos(Integer id, String nombre) throws FerreteriaException;

}
