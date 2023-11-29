package dao;

import exception.FerreteriaException;
import java.util.List;
import modelo.Vendedor;

public interface VendedorDao extends Crud {

    Vendedor obtenerVendedorPorUsername(String username) throws FerreteriaException;

    List<Vendedor> obtenerVendedores(Integer id, String dni, String nombres, String username) throws FerreteriaException;
    
}
