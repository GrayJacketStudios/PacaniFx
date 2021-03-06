
package pacani.controller;
import java.sql.*;

public class Conexion {
        
    static Connection conn=null;
    static Statement stmt=null;
    static ResultSet rs=null;
    private String nomDB="pelao_Pacani";
    private String ruta="jdbc:mysql://204.12.247.170/"+nomDB;
    private String usuario="pelao_Pacani";
    private String clave="hwANqdFoSA";
        
    
    public Conexion(){
      
    }
    public Connection Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(ruta,usuario,clave); 
            stmt= conn.createStatement();
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("No conectado: "+e);
        }return conn;
    }
    
  
    //INSERT, DELETE, UPDATE Y PARAMETRO SQL
    public void setExecuteUpdate(String sql){
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
        } catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());        
        }
    }
    //SELECT
    public void setExecuteQuery(String sql){
        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
        } catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
        }
    }
   
    //DEVOLVER RESULTADO DE CONSULTA
    public ResultSet getRs(){
        return rs;
    }
    
    public void closeConnection(){
        try {
            rs.close();
        } catch (SQLException ex) {
           
        }
        
        try {
            stmt.close();
        } catch (SQLException ex) {
            
        }
        try {
            conn.close();
        } catch (SQLException ex) {
        }
    }
    
   
}


