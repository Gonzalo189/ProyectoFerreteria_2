package dao;

import exception.FerreteriaException;
import modelo.DetalleVentas;

public interface DetalleVentasDao extends Crud {

    DetalleVentas obtenerDetalleVentas(int id, int idventas, int idproducto, int cantidad, double preventa) throws FerreteriaException;

}
