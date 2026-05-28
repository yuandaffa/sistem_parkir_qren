/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.*;

/**
 *
 * @author asus
 */
public class JDBC {

    private Connection con;
    private Statement stmt;
    private String message;

    // Static connection
    private static Connection connection;

    // Konfigurasi database
    private static final String DB_NAME = "sqr_parkir";
    private static final String URL =
            "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message = msg;
    }

    // Connect biasa
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

            stmt = con.createStatement();

            message = "DB connected";

        } catch (Exception e) {
            message = e.getMessage();
        }
    }

    // Disconnect
    private void disconnect() {
        try {

            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }

        } catch (Exception e) {
            message = e.getMessage();
        }
    }

    // INSERT UPDATE DELETE
    public void runQuery(String query) {

        try {

            connect();

            int result = stmt.executeUpdate(query);

            message = "Info : " + result + " rows affected";

        } catch (Exception e) {

            message = e.getMessage();

        } finally {

            disconnect();

        }
    }

    // SELECT
    public ResultSet getData(String query) {

        ResultSet rs = null;

        try {

            connect();

            rs = stmt.executeQuery(query);

        } catch (Exception e) {

            message = e.getMessage();

        }

        return rs;
    }

    // Static connection
    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );

                System.out.println(
                        "[JDBC] Koneksi database berhasil"
                );

            } catch (ClassNotFoundException e) {

                throw new SQLException(
                        "MySQL JDBC Driver tidak ditemukan",
                        e
                );
            }
        }

        return connection;
    }
}