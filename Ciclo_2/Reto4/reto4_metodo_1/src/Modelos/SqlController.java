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

    public void insertarDatos(double nombre, double materia, double genero, double nota) throws SQLException {
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
        double tempNom=0;
        double tempMat=0;

        try {
           
            connection=objConexion.connect();
            
            statement = connection.createStatement();

            if(nombre.equals("") && materia.equals("")){        //Sin datos
                JOptionPane.showMessageDialog(null,"Ingrese el nombre o la materia para eliminar");
                
                return;
            }else if(nombre.equals("") && !materia.equals("")){          //Eliminar por materia
                caso="M";
                tempMat=Double.parseDouble(materia);
                consultaEliminar(String.valueOf(tempNom), String.valueOf(tempMat), caso);
                statement.executeUpdate("DELETE FROM Alumnos WHERE Materia="+tempMat+";");
                
            }else if(!nombre.equals("") && materia.equals("")){         //Eliminar por nombre
                caso="N";
                tempNom=Double.parseDouble(nombre);
                consultaEliminar(String.valueOf(tempNom), String.valueOf(tempMat), caso);
                statement.executeUpdate("DELETE FROM Alumnos WHERE Nombre="+tempNom+";");
                
            }else{                                                //Eliminar por nombre y materia
                caso="MN";
                tempNom=Double.parseDouble(nombre);
                tempMat=Double.parseDouble(materia);
                consultaEliminar(String.valueOf(tempNom), String.valueOf(tempMat), caso);
                statement.executeUpdate("DELETE FROM Alumnos WHERE Nombre="+tempNom+" AND Materia="+tempMat+";");
                
            }

        objConexion.close();
        }catch (SQLException ex) 
        {
            System.out.println(ex);
        }
    }
    
    public String consultarDatos(String nombre, String materia){
        String datos="";
        double tempNom=0;
        double tempMat=0;
        try {
           
            connection=objConexion.connect();
            
            statement = connection.createStatement();

            if(nombre.equals("") && materia.equals("")){        //Sin datos
                JOptionPane.showMessageDialog(null,"Ingrese el nombre o la materia para realizar la consulta");
                datos="Ingrese el nombre o la materia para realizar la consulta";
                return datos;
            }else if(nombre.equals("") && !materia.equals("")){          //Consultar por materia
                tempMat=Double.parseDouble(materia);
                resultSet = statement.executeQuery("SELECT * FROM Alumnos WHERE Materia="+tempMat+";");
                
            }else if(!nombre.equals("") && (materia.equals(""))){     //Consultar por nombre
                tempNom=Double.parseDouble(nombre);
                resultSet = statement.executeQuery("SELECT * FROM Alumnos WHERE Nombre="+tempNom+";");
            }else{                                              //Consultar por nombre y materia
                tempNom=Double.parseDouble(nombre);
                tempMat=Double.parseDouble(materia);
                resultSet = statement.executeQuery("SELECT * FROM Alumnos WHERE Nombre="+
                                                    tempNom+" AND Materia="+tempMat+";");
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
            q="SELECT * FROM Alumnos WHERE Nombre="+nombre+";";
            break;
            case "M":
            q="SELECT * FROM Alumnos WHERE Materia="+materia+";";
            break;
            case "MN":
            q="SELECT * FROM Alumnos WHERE Nombre="+nombre+" AND Materia="+materia+";";
            break;
            default:
            break;

        }

        try {
            // System.out.println(q);
           
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

    public boolean controlDatos(double nombre, double genero, double materia){
        SchoolGradingSystem obj=new SchoolGradingSystem();
        String[] datos=obj.castControl(nombre,materia);
        boolean error=false;
        if(datos[0].equals("false")){
            JOptionPane.showMessageDialog(null,"El identificador de nombre ingresado es incorrecto");
            error=true;
        }
        
        if(datos[1].equals("false")){
            JOptionPane.showMessageDialog(null,"El identificador de materia ingresado es incorrecto");
            error=true;
        }
        return error;
    }
   
}
