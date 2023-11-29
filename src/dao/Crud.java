package dao;

import exception.FerreteriaException;
import java.util.List;

public interface Crud {

    public List listar() throws FerreteriaException;

    public int agregar(Object o) throws FerreteriaException;

    public int actualizar(Object o) throws FerreteriaException;

    public void eliminar(int id) throws FerreteriaException;
}
