
package pacani.controller;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
        
    static Connection conn=null;
    static Statement stmt=null;
    static ResultSet rs=null;
    
    
    public Conexion(){
      
    }
    public Connection Conexion(){
        String nomDB="";
        String ruta="jdbc:mysql://localhost/"+nomDB;
        String usuario="root";
        String clave="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(ruta,usuario,clave); 
            stmt= conn.createStatement();
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("No conectado");
        }return conn;
    }
    
  
    //INSERT, DELETE, UPDATE Y PARAMETRO SQL
    public void setExecuteUpdate(String sql){
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception ex){
            System.out.println("ERROR"+ex.getMessage());        
        }
    }
    //SELECT
    public void setExecuteQuery(String sql){
        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
        } catch (Exception ex){
            System.out.println("ERROR"+ex.getMessage());
        }
    }
   
    //DEVOLVER RESULTADO DE CONSULTA
    public ResultSet getRs(){
        return rs;
    }
    
   
}


