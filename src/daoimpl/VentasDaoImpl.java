package daoimpl;

import dao.VentasDao;
import exception.FerreteriaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.DetalleVentas;
import modelo.Ventas;

public class VentasDaoImpl implements VentasDao {

    private final Connection conexionDB;

    public VentasDaoImpl(Connection conexionDB) {
        this.conexionDB = conexionDB;
    }
    PreparedStatement pstmt;
    ResultSet rs;
    int r = 0;

    public String NroSerieVentas() throws FerreteriaException {
        String serie = "";
        String sql = "select max(numeroventas) from ventas";
        try {
            pstmt = conexionDB.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                serie = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return serie;
    }

    public Integer idventas() throws FerreteriaException {
        Integer idv = null;
        String sql = "select max(idventas) from ventas";
        try {
            pstmt = conexionDB.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                idv = rs.getInt(1);

            }

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return idv;
    }

    public int guardarVentas(Ventas v) throws FerreteriaException {
        Ventas ventas = new Ventas();
        String sql = "insert into ventas(idcliente, idvendedor, numeroventas, fechaventas, monto, estado)values(?,?,?,?,?,?)";
        try {
            pstmt = conexionDB.prepareStatement(sql);
            pstmt.setInt(1, v.getIdcliente());
            pstmt.setInt(2, v.getIdvendedor());
            pstmt.setString(3, v.getSerie());
            pstmt.setString(4, v.getFecha());
            pstmt.setDouble(5, v.getMonto());
            pstmt.setString(6, v.getEstado());
            r = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }
        return r;

    }

    public int guardarDetalleVentas(DetalleVentas dv) throws FerreteriaException {
        String sql = "insert into detalle_ventas(idventas, idproducto, cantidad, precioventa)values(?,?,?,?)";
        try {
            pstmt = conexionDB.prepareStatement(sql);
            pstmt.setInt(1, dv.getIdventas());
            pstmt.setInt(2, dv.getIdproducto());
            pstmt.setInt(3, dv.getCantidad());
            pstmt.setDouble(4, dv.getPreventa());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new FerreteriaException(e.getMessage());
        }

        return r;

    }

    @Override
    public Ventas obtenerVentas(int idcliente, int idvendedor, String serie, String fecha, double monto, String estado) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
