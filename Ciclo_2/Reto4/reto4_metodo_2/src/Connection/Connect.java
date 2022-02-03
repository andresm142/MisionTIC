package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connect {
    Connection conn=null;
    public Connection connect(){
        // Ruta de la base de datos
        String url="jdbc:sqlite:db\\Calificaciones.db";
        // Connection conn=null;

        try{
            // Creamos la conexion
            conn=DriverManager.getConnection(url);
            // System.out.println("Conectado a la base de datos SQLite");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void close(){
        // Cerrar la conexion con la base de datos
        try {
            conn.close();
            // System.out.println("desconectado de la base de datos SQLite");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
