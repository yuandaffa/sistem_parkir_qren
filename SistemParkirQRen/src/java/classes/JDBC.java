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
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String msg){
        message = msg;
    }
    
    public void connect() {
        String dbname = "smart_parking";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, username, password);
            stmt = con.createStatement();
            message = "DB connected";
        } catch (Exception e){
            message = e.getMessage();
        }
    }
        private void disconnect(){
            try{
                stmt.close();
                con.close();
            } catch (Exception e){
                message = e.getMessage();
            }
        }
        
        public void runQuery(String query){
            try {
                connect();
                int result = stmt.executeUpdate(query);
                message = "info: " + result + " rows affected";
            } catch (Exception e){
                message = e.getMessage();
            } finally {
                disconnect();
            }
        }
        
        public ResultSet getData(String query){
            ResultSet rs = null;
            try {
                connect();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                message = e.getMessage();
            }
            return rs;
        }
}
