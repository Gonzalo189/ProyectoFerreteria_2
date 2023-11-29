package dao;

import exception.FerreteriaException;
import java.util.List;
import modelo.Cliente;

public interface ClienteDao extends Crud {

    Cliente obetenerCliente(String dni, String nombres, String dirreccion, String estado) throws FerreteriaException;
    
     List<Cliente> obtenerClientes(Integer id, String dni, String nombres) throws FerreteriaException;

}
