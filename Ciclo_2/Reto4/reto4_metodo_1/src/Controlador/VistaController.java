package Controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javafx.scene.input.KeyEvent;
import Modelos.SchoolGradingSystem;
import Modelos.SqlController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class VistaController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombreIngresar;

    @FXML
    private TextField txtNotas;

    @FXML
    private TextField txtMateriaIngresar;

    @FXML
    private TextField txtGenero;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCargar;

    @FXML
    private TextArea txtNotasCargar;

    @FXML
    private Button btnProcesar;

    @FXML
    private TextArea txtResultado;

    @FXML
    private TextField txtNombreConsulta;

    @FXML
    private TextField txtMateriaConsulta;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TextArea txtResultadoConsulta;

    @FXML
    void cargarClick(ActionEvent event) throws SQLException {
        
        SqlController sqlController=new SqlController();
        txtNotasCargar.clear();
        String resultado=sqlController.leerDatos();
        if(resultado.equals("")){
            JOptionPane.showMessageDialog(null,"No hay registros");
        }else{
            txtNotasCargar.setText(sqlController.leerDatos());
        }
        

    }
                    
    @FXML
    void consultarClick(ActionEvent event) {
        txtResultadoConsulta.clear();
        boolean error=false;
        SqlController sqlController=new SqlController();
        String strNombre=txtNombreConsulta.getText();
        String strMateria=txtMateriaConsulta.getText();
        error=comprobarDatos(strNombre, strMateria);
        if(!error){
            String resultado=sqlController.consultarDatos(strNombre, strMateria);
            if(resultado.equals("")){
                JOptionPane.showMessageDialog(null,"No hay registros");
                txtResultadoConsulta.setText("No hay registros");
            }else{
                txtResultadoConsulta.setText(resultado);
            }
        }
    }

    @FXML
    void eliminarClick(ActionEvent event) {
        txtResultadoConsulta.clear();
        SqlController sqlController=new SqlController();
        String strNombre=txtNombreConsulta.getText();
        String strMateria=txtMateriaConsulta.getText();
        boolean error=false;
        error=comprobarDatos(strNombre, strMateria);
        if(!error){
            sqlController.eliminarDatos(strNombre, strMateria);
        }

    }

    @FXML
    void guardarClick(ActionEvent event) throws SQLException {
        SqlController sqlController=new SqlController();
        boolean error=false;
        String Strnombre=txtNombreIngresar.getText().replaceAll("\\s", "");
        String Strgenero=txtGenero.getText().replaceAll("\\s", "");
        if (Strgenero.equals("m")){
            Strgenero="0.0";
        }else{
            Strgenero="1.0";
        }
        String Strmateria=txtMateriaIngresar.getText().replaceAll("\\s", "");
        String Strnota=txtNotas.getText().replaceAll("\\s", "");
        
        if(Strnombre.equals("") || Strgenero.equals("") || Strmateria.equals("") || Strnota.equals("")){
            JOptionPane.showMessageDialog(null,"Llene todos los campos");
            return;
        }
        double nombre=Double.parseDouble(Strnombre);
        double genero=Double.parseDouble(Strgenero);
        double materia=Double.parseDouble(Strmateria);
        double notas=Double.parseDouble(Strnota);
        
        if(notas <0 || notas>10){
            JOptionPane.showMessageDialog(null,"La nota no puede ser menor a 0 y mayor a 10");
            return;
        }
        
        error=sqlController.controlDatos(nombre, genero, materia);
        if(!error){
            sqlController.insertarDatos(nombre, materia, genero, notas);
            txtNombreIngresar.clear();
            txtGenero.clear();
            txtMateriaIngresar.clear();
            txtNotas.clear();
        }
        
    }

    @FXML
    void procesarClick(ActionEvent event) {
        SchoolGradingSystem estuiante=new  SchoolGradingSystem();
        String datos= txtNotasCargar.getText();
        if(datos.equals("")){
            JOptionPane.showMessageDialog(null,"No hay datos para procesar");
            txtNotasCargar.clear();
        }else{
            estuiante.loadData(datos);
            
            txtResultado.setText(String.valueOf(estuiante.stat1()+"\n"+
                                                estuiante.stat2()+"\n"+
                                                estuiante.stat3()+"\n"+
                                                estuiante.stat4()+"\n"));
        }
    }

    @FXML
    void keyTypedGenero(KeyEvent event ) {

        if (txtGenero.getText().length() >= 1 ){ // limite a 1 caracter
                txtGenero.setText(txtGenero.getText().substring(0,1));
                txtGenero.end();                //Cursor al final del texto
                
        }

        if (!txtGenero.getText().equals("")){
            if (!txtGenero.getText().matches("[f m]") ) {
                txtGenero.clear();
            }

            
        }
    }

    @FXML
    void keyTypedNota(KeyEvent event) {
        if(!txtNotas.getText().equals("")){
            try{
                Double.parseDouble(txtNotas.getText());       //Comprueba que el textFiel solo tenga numeros, sino genera error
            
            }catch(Exception e)
            {
                txtNotas.clear();
                JOptionPane.showMessageDialog(null,"Error, ingrese solo numeros en el campo de notas y uso de punto (.)");
            }
        }

        if (txtNotas.getText().length()>=5){            // limite a 5 caracteres
            txtNotas.setText(txtNotas.getText().substring(0,4));
            txtNotas.end();         
        }
       
    }

    @FXML
    void keyTyped(KeyEvent event) {
        if(!txtNombreIngresar.getText().equals("") || !txtMateriaIngresar.getText().equals("") ){
            try{
                Double.parseDouble(txtNombreIngresar.getText());       //Comprueba que el textFiel solo tenga numeros, sino genera error
                Double.parseDouble(txtMateriaIngresar.getText());
            }catch(Exception e)
            {
                txtMateriaIngresar.clear();
                txtNombreIngresar.clear();
                JOptionPane.showMessageDialog(null,"Error, ingrese solo numeros en el campo de nombre y materia");
            }
        }
    }

    public boolean comprobarDatos(String strNombre,String strMateria){
        SqlController sqlController=new SqlController();
        boolean error=false;
        double nombre=0;
        double materia=0;
        if(!strNombre.equals("") && !strMateria.equals("")){
            nombre=Double.parseDouble(strNombre);
            materia=Double.parseDouble(strMateria);
            error=sqlController.controlDatos(nombre, 0.0, materia);
        }else if(!strNombre.equals("")){
            nombre=Double.parseDouble(strNombre);
            System.out.println(nombre);
            error=sqlController.controlDatos(nombre, 0, 1.0);
        }else if(!strMateria.equals("")){
            materia=Double.parseDouble(strMateria);
            error=sqlController.controlDatos(1.0, 0.0, materia);
        }
        return error;
    }
    @FXML
    void initialize() {
        txtNotasCargar.setEditable(false);
    }
    
}
