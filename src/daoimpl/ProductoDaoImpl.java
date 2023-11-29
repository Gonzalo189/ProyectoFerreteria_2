package daoimpl;

import dao.ProductoDao;
import java.sql.Connection;
import modelo.Producto;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import exception.FerreteriaException;
import java.sql.SQLException;


public class ProductoDaoImpl implements ProductoDao {

    int r;

    private final Connection conexionDB;

    public ProductoDaoImpl(Connection conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public Producto obtenerProducto(String Nombre, double Precio, int Stock, String estado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Producto> obtenerProductos(Integer id, String nombre) throws FerreteriaException {
        List<Producto> lista = new ArrayList<>();
        Producto pd;
        String sql = "SELECT idproducto, nombre, precio, stock, estado FROM producto WHERE 1=1 ";
        if (id != null) {
            sql = sql + "AND idproducto=? ";
        }
        if (nombre != null && !nombre.trim().equals("")) {
            sql = sql + "AND nombres=? ";
        }

        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            int index = 1;
            if (id != null) {
                pstmt.setInt(index++, id);
            }
            if (nombre != null && !nombre.trim().equals("")) {
                pstmt.setString(index++, nombre);
            }


            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pd = new Producto();
                pd.setId(rs.getInt(1));
                pd.setNombre(rs.getString(2));
                pd.setPrecio(rs.getDouble(3));
                pd.setStock(rs.getInt(4));
                pd.setEstado(rs.getInt(5));

                lista.add(pd);
            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return lista;
    }

    public int actualizarStock(int cant, int idp) throws FerreteriaException {
        String sql = "update producto set stock=? where idproducto=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setInt(1, cant);
            pstmt.setInt(2, idp);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return r;

    }

    public Producto listarID(int id) throws FerreteriaException {
        Producto P = new Producto();
        String sql = "select * from producto where idproducto=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                P.setId(rs.getInt(1));
                P.setNombre(rs.getString(2));
                P.setPrecio(rs.getDouble(3));
                P.setStock(rs.getInt(4));
                P.setEstado(rs.getInt(5));

            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return P;
    }

    //***********************MANTENIMIENTO CRUD******************************
    @Override
    public List listar() throws FerreteriaException {
        List<Producto> lista = new ArrayList<>();
        Producto pd;
        String sql = "SELECT idproducto, nombre, precio, stock, estado FROM producto";

        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pd = new Producto();
                pd.setId(rs.getInt(1));
                pd.setNombre(rs.getString(2));
                pd.setPrecio(rs.getDouble(3));
                pd.setStock(rs.getInt(4));
                pd.setEstado(rs.getInt(5));
                lista.add(pd);
            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return lista;
    }

    @Override
    public int agregar(Object o) throws FerreteriaException {
        Producto p = (Producto) o;
        String sql = "INSERT INTO producto (nombre, precio, stock, estado) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setObject(1, p.getNombre());
            pstmt.setObject(2, p.getPrecio());
            pstmt.setObject(3, p.getStock());
            pstmt.setObject(4, p.getEstado());
            r = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return r;
    }

    @Override
    public int actualizar(Object o) throws FerreteriaException {
        Producto p = (Producto) o;
        String sql = "update producto set nombre=?, precio=?, stock=?, estado=? where idproducto=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setObject(1, p.getNombre());
            pstmt.setObject(2, p.getPrecio());
            pstmt.setObject(3, p.getStock());
            pstmt.setObject(4, p.getEstado());
            pstmt.setObject(5, p.getId());
            r = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return r;
    }

    @Override
    public void eliminar(int id) throws FerreteriaException {
        String sql = "delete from producto where idproducto=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
    }



}
