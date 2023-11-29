package daoimpl;

import dao.ClienteDao;
import exception.FerreteriaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDaoImpl implements ClienteDao {

    private final Connection conexionDB;

    public ClienteDaoImpl(Connection conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public Cliente obetenerCliente(String dni, String nombres, String dirreccion, String estado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Cliente> obtenerClientes(Integer id, String dni, String nombres) throws FerreteriaException {
        List<Cliente> lista = new ArrayList<>();
        Cliente cl;
        String sql = "SELECT idcliente, dni, nombres, dirreccion, estado FROM cliente WHERE 1=1 ";
        if (id != null) {
            sql = sql + "AND idcliente=? ";
        }
        if (dni != null && !dni.trim().equals("")) {
            sql = sql + "AND dni=? ";
        }
        if (nombres != null && !nombres.trim().equals("")) {
            sql = sql + "AND nombres=? ";
        }

        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            int index = 1;
            if (id != null) {
                pstmt.setInt(index++, id);
            }
            if (dni != null && !dni.trim().equals("")) {
                pstmt.setString(index++, dni);
            }
            if (nombres != null && !nombres.trim().equals("")) {
                pstmt.setString(index++, nombres);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cl = new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNombres(rs.getString(3));
                cl.setDirreccion(rs.getString(4));
                cl.setEstado(rs.getInt(5));


                lista.add(cl);
            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return lista;
    }

    public Cliente listarID(String dni) throws FerreteriaException {
        Cliente c = new Cliente();
        String sql = "select * from cliente where Dni=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombres(rs.getString(3));
                c.setDirreccion(rs.getNString(4));
                c.setEstado(rs.getInt(5));

            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return c;
    }

    @Override
    public List listar() throws FerreteriaException {
        List<Cliente> lista = new ArrayList<>();
        Cliente ct;
        String sql = "SELECT idcliente, dni, nombres, dirreccion, estado FROM cliente";

        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ct = new Cliente();
                ct.setId(rs.getInt(1));
                ct.setDni(rs.getString(2));
                ct.setNombres(rs.getString(3));
                ct.setDirreccion(rs.getString(4));
                ct.setEstado(rs.getInt(5));
                lista.add(ct);
            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return lista;

    }

    @Override
    public int agregar(Object o) throws FerreteriaException {
        Cliente c = (Cliente) o;
        int r = 0;
        String sql = "INSERT INTO cliente (dni, nombres, dirreccion, estado) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setObject(1, c.getDni());
            pstmt.setObject(2, c.getNombres());
            pstmt.setObject(3, c.getDirreccion());
            pstmt.setObject(4, c.getEstado());
            r = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return r;
    }

    @Override
    public int actualizar(Object o) throws FerreteriaException {
        Cliente c = (Cliente) o;
        int r = 0;
        String sql = "update cliente set dni=?, nombres=?, dirreccion=?, estado=? where idcliente=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setObject(1, c.getDni());
            pstmt.setObject(2, c.getNombres());
            pstmt.setObject(3, c.getDirreccion());
            pstmt.setObject(4, c.getEstado());
            pstmt.setObject(5, c.getId());
            r = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return r;
    }

    @Override
    public void eliminar(int id) throws FerreteriaException {
        String sql = "delete from cliente where idcliente=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
    }

}
