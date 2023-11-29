/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gonza
 */
public class Connector {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String server = "localhost";
    private static final String port = "3306";
    private static final String db = "ferreteria";
    private static final String params = "";//"?serverTimezone=UTC";      
    private static final String user = "root";
    private static final String pass = "123456";
    private static final String url = "jdbc:mysql://" + server + ":" + port + "/" + db + params;

    private static Connection conexionDB = null;

    private Connector() {
    }

    public synchronized static Connection getConnection() {
        try {
            if (conexionDB == null || conexionDB.isClosed()) {

                Class.forName(driver);
                conexionDB = DriverManager.getConnection(url, user, pass);
                System.out.println("conectando a la bd ...");

            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return conexionDB;
    }
}
