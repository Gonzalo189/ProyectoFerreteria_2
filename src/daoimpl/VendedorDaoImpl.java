package daoimpl;

import dao.VendedorDao;
import exception.FerreteriaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Vendedor;

public class VendedorDaoImpl implements VendedorDao {

    private final Connection conexionDB;

    public VendedorDaoImpl(Connection conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public Vendedor obtenerVendedorPorUsername(String username) throws FerreteriaException {
        Vendedor vendedor = null;
        String sql = "SELECT idvendedor, dni, nombres, telefono, estado, username, password "
                + "FROM vendedor "
                + "WHERE username = ? "
                + "AND estado = '1' ";

        try {
            PreparedStatement statement = conexionDB.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vendedor = new Vendedor();
                vendedor.setId(resultSet.getInt(1));
                vendedor.setDni(resultSet.getString(2));
                vendedor.setNombres(resultSet.getString(3));
                vendedor.setTelefono(resultSet.getString(4));
                vendedor.setEstado(resultSet.getInt(5));
                vendedor.setUsername(resultSet.getString(6));
                vendedor.setPassword(resultSet.getString(7));
            }
        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return vendedor;
    }

    @Override
    public List<Vendedor> obtenerVendedores(Integer id, String dni, String nombres, String username) throws FerreteriaException {
        List<Vendedor> lista = new ArrayList<>();
        Vendedor vd;
        String sql = "SELECT idvendedor, dni, nombres, telefono, estado, username, password FROM vendedor WHERE 1=1 ";
        if (id != null) {
            sql = sql + "AND idvendedor=? ";
        }
        if (dni != null && !dni.trim().equals("")) {
            sql = sql + "AND dni=? ";
        }
        if (nombres != null && !nombres.trim().equals("")) {
            sql = sql + "AND nombres=? ";
        }
        if (username != null && !username.trim().equals("")) {
            sql = sql + "AND username=? ";
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
            if (username != null && !username.trim().equals("")) {
                pstmt.setString(index++, username);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                vd = new Vendedor();
                vd.setId(rs.getInt(1));
                vd.setDni(rs.getString(2));
                vd.setNombres(rs.getString(3));
                vd.setTelefono(rs.getString(4));
                vd.setEstado(rs.getInt(5));
                vd.setUsername(rs.getString(6));
                vd.setPassword(rs.getString(7));

                lista.add(vd);
            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return lista;
    }

    @Override
    public List listar() throws FerreteriaException {
        List<Vendedor> lista = new ArrayList<>();
        Vendedor vd;
        String sql = "SELECT idvendedor, dni, nombres, telefono, estado, username, password FROM vendedor";

        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                vd = new Vendedor();
                vd.setId(rs.getInt(1));
                vd.setDni(rs.getString(2));
                vd.setNombres(rs.getString(3));
                vd.setTelefono(rs.getString(4));
                vd.setEstado(rs.getInt(5));
                vd.setUsername(rs.getString(6));
                vd.setPassword(rs.getString(7));

                lista.add(vd);
            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return lista;

    }

    @Override
    public int agregar(Object o) throws FerreteriaException {
        Vendedor v = (Vendedor) o;
        int count = 0;
        String sql = "INSERT INTO vendedor (dni, nombres, telefono, estado, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setObject(1, v.getDni());
            pstmt.setObject(2, v.getNombres());
            pstmt.setObject(3, v.getTelefono());
            pstmt.setObject(4, v.getEstado());
            pstmt.setObject(5, v.getUsername());
            pstmt.setObject(6, v.getPassword());

            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return count;

    }

    @Override
    public int actualizar(Object o) throws FerreteriaException {
        Vendedor v = (Vendedor) o;
        int count = 0;
        String sql = "update vendedor set dni=?, nombres=?, telefono=?, estado=?, username=?, password=? where idvendedor=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setObject(1, v.getDni());
            pstmt.setObject(2, v.getNombres());
            pstmt.setObject(3, v.getTelefono());
            pstmt.setObject(4, v.getEstado());
            pstmt.setObject(5, v.getUsername());
            pstmt.setObject(6, v.getPassword());
            pstmt.setObject(7, v.getId());
            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return count;

    }

    @Override
    public void eliminar(int id) throws FerreteriaException {
        String sql = "delete from vendedor where idvendedor=?";
        try {
            PreparedStatement pstmt = conexionDB.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
    }


}
