package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hernan
 */
public class Conexion {
    private String url;
    private String usuario;
    private String password;
    
    private Connection conexion=null;

    public Conexion(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }    
    public Connection establecerConexion(){
        if(conexion==null){
        try {           
            //cargar driver
            Class.forName("org.mariadb.jdbc.Driver");            
            //crear conexion a la bd
            conexion = DriverManager.getConnection(url,usuario,password);            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"ERROR carga driver "+ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERROR de conexion "+ex.getMessage());        }
    }
        return  conexion;
    }
}
