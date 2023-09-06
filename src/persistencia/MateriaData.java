package persistencia;


import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Hernan
 */
public class MateriaData {
    private Connection conexion=null;
    
    //constructor
    public MateriaData(Conexion conexion) {
        this.conexion = conexion.establecerConexion();
    }

    Scanner lectura = new Scanner (System.in);

    public void guardarMateria(Materia m) {
        try {
            //agregar un alumno
            String query = "INSERT INTO materia(nombre, anio, estado) VALUES (?,?,?)";
            //armar sentencia de sql
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setBoolean(3, m.isEstado());
            //ejecutar query executeUpdate devuelve un entero
            ps.executeUpdate();
            //recupero el registro y lo asigno a la clase alumno
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo guardar");
            }
            ps.close();
            System.out.println("Materia guardada");
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Materia buscarMAteria(int id){
        Materia m=new Materia();  
        try {                      
            //buscar materia
            String query = "SELECT * FROM alumno WHERE id_materia=?";
            //armar sentencia de sql
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            //ejecutar consulta executeQuery
            ResultSet rs = ps.executeQuery();
            //armo el aobjeto alumno
            while(rs.next()){
                m.setId(rs.getInt("id_materia"));               
                m.setNombre(rs.getString("nombre"));
                m.setAnio(rs.getInt("anio"));
                m.setEstado(rs.getBoolean("estado"));
            }
            //cierro el query
            ps.close();    
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m; 
    } 
    
//     public String actualizarPorDni(int dni){
//         Alumno a=new Alumno();
//         try {
//            //agregar un alumno
//            String query = "UPDATE alumno SET apellido=?,nombre=?,fecha_nacimiento=?,estado=? WHERE dni=?";
//            //armar sentencia de sql
//            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(6, dni);
//            System.out.println("Ingrese el apellido: ");             
//            ps.setString(1, lectura.next());
//             System.out.println("Ingrese el nombre: ");
//            ps.setString(2, lectura.next());
//             System.out.println("Ingrese la fecha de nacimiento: ");
//            ps.setString(3, lectura.next());
//             System.out.println("ingrese el estado: ");
//            ps.setBoolean(4, lectura.nextBoolean());
//            //ejecutar query executeUpdate devuelve un entero
//            ps.executeUpdate(); 
//            //recupero el registro y lo asigno a la clase alumno 
//            ResultSet rs = ps.getGeneratedKeys();
//            if(rs.next())
//                a.setId(rs.getInt(1));
//            else
//                System.out.println("No se pudo actualizar");
//            ps.close();
//            System.out.println("Actualizado");
//        } catch (SQLException ex) {
//            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         return a.toString();
//     }
    
     public void EliminarMateria(int id){ 
        try {
            //agregar un alumno
            String query = "DELETE FROM `materia` WHERE id=?";
            //armar sentencia de sql
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);   
            ps.setInt(1, id);
            //ejecutar query executeUpdate devuelve un entero
            ps.executeUpdate();          
            ResultSet rs = ps.getGeneratedKeys();
            //cierro el PreparedStatement
            ps.close();
            System.out.println("Eliminada");
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
          
     }
  

}
