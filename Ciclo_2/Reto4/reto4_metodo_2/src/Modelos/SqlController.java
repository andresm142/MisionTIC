package Modelos;

import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.Connect;

public class SqlController {
    protected Connection connection=null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    protected Connect objConexion= new Connect();

    public void insertarDatos(String nombre, String materia, String genero, String nota) throws SQLException {
        connection=objConexion.connect();
        statement = connection.createStatement();
  
        try {
            statement.execute(
                "INSERT INTO Alumnos (Nombre, Genero, Materia, Notas ) VALUES ( '"+nombre+"', '"+genero+"', '"+materia+"', '"+nota+"' ) ");
                
                JOptionPane.showMessageDialog(null,"Datos guardados");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String leerDatos() throws SQLException{
                                
        String datos="";
        try {
            
            connection=objConexion.connect();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Alumnos ORDER BY Id ASC; ");
            
            while (resultSet.next())
            {
                
                datos=datos + resultSet.getString("Nombre") + " " 
                                        + resultSet.getString("Genero") + " "
                                        +  resultSet.getString("Materia") + " " 
                                        +  resultSet.getString("Notas") +"\n";
            }
            objConexion.close();
            
        }catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        return datos;
    }

    public void eliminarDatos(String nombre, String materia){
        String caso="";
       
        try {
           
            connection=objConexion.connect();
            
            statement = connection.createStatement();

            if(nombre.equals("") && materia.equals("")){        //Sin datos
                JOptionPane.showMessageDialog(null,"Ingrese el nombre o la materia para eliminar");
                // txtResultadoConsulta.setText("Ingrese el nombre o la materia para eliminar");
                return;
            }else if(nombre.equals("") && !materia.equals("")){          //Eliminar por materia
                caso="M";
                consultaEliminar(nombre, materia, caso);
                statement.executeUpdate("DELETE FROM Alumnos WHERE Materia='"+materia+"';");
                
            }else if(!nombre.equals("") && materia.equals("")){         //Eliminar por nombre
                caso="N";
                consultaEliminar(nombre, materia, caso);
                statement.executeUpdate("DELETE FROM Alumnos WHERE Nombre='"+nombre+"';");
                
            }else{                                                //Eliminar por nombre y materia
                caso="MN";
                consultaEliminar(nombre, materia, caso);
                statement.executeUpdate("DELETE FROM Alumnos WHERE Nombre='"+nombre+"' AND Materia='"+materia+"';");
                
            }

        objConexion.close();
        }catch (SQLException ex) 
        {
            System.out.println(ex);
        }
    }
    
    public String consultarDatos(String nombre, String materia){
        String datos="";
        try {
           
            connection=objConexion.connect();
            
            statement = connection.createStatement();

            if(nombre.equals("") && materia.equals("")){        //Sin datos
                JOptionPane.showMessageDialog(null,"Ingrese el nombre o la materia para realizar la consulta");
                datos="Ingrese el nombre o la materia para realizar la consulta";
                return datos;
            }else if(nombre.equals("") && !materia.equals("")){          //Consultar por materia
               resultSet = statement.executeQuery("SELECT * FROM Alumnos WHERE Materia='"+materia+"';");
                
            }else if(!nombre.equals("") && (materia.equals(""))){     //Consultar por nombre
                resultSet = statement.executeQuery("SELECT * FROM Alumnos WHERE Nombre='"+nombre+"';");
            }else{                                              //Consultar por nombre y materia
                resultSet = statement.executeQuery("SELECT * FROM Alumnos WHERE Nombre='"+
                                                    nombre+"' AND Materia='"+materia+"';");
            }

            while (resultSet.next())
            {
                datos=datos + resultSet.getString("Nombre") + " " 
                                                        + resultSet.getString("Genero") + " "
                                                        +  resultSet.getString("Materia") + " " 
                                                        +  resultSet.getString("Notas") +"\n";
            }
            objConexion.close();

        }catch (SQLException ex) 
        {
            System.out.println(ex);
        }

        return datos;
    }

    public void consultaEliminar(String nombre,String materia,String caso){
        String q="";
        switch(caso){
            case "N":
            q="SELECT * FROM Alumnos WHERE Nombre='"+nombre+"';";
            break;
            case "M":
            q="SELECT * FROM Alumnos WHERE Materia='"+materia+"';";
            break;
            case "MN":
            q="SELECT * FROM Alumnos WHERE  Nombre='"+nombre+"'"+" AND Materia='"+materia+"';";
            break;
            default:
            break;

        }

        try {
           
            resultSet = statement.executeQuery(q);
            
            if(resultSet.next()){
                 JOptionPane.showMessageDialog(null,"Registros borrados");
            }else{
                JOptionPane.showMessageDialog(null,"No hay registros para eliminar");
            }
            

        }catch (SQLException ex) 
        {
            System.out.println(ex);
        }

    }

    
}
