package dao;

import exception.FerreteriaException;
import modelo.Ventas;

public interface VentasDao {

    Ventas obtenerVentas(int idcliente, int idvendedor, String serie, String fecha, double monto, String estado) throws FerreteriaException;
}
