/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Hernan
 */
public class AlumnoData {
    private Connection conexion=null;

    public AlumnoData(Conexion conexion) {
        this.conexion = conexion.establecerConexion();
    }

    public void guardarAlumno(Alumno a) {
        try {
            //agregar un alumno
            String query = "INSERT INTO alumno(dni, apellido, nombre ,fecha_nacimiento, estado)"
                    + " VALUES (?,?,?,?,?)";
            //armar sentencia de sql
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setString(4, a.getFechaNacimiento());
            ps.setBoolean(5, a.isEstado());
            //ejecutar query executeUpdate devuelve un entero
            ps.executeUpdate(); 
            //recupero el registro y lo asigno a la clase alumno 
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                a.setId(rs.getInt(1));
            else
                System.out.println("No se pudo guardar");
            ps.close();
            System.out.println("Guardado");
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Alumno buscarAlumno(int id){
        Alumno a= new Alumno();        
        try {
            //agregar un alumno
            String query = "SELECT * FROM alumno WHERE id_alumno=?";
            //armar sentencia de sql
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            //ejecutar consulta executeQuery
            ResultSet rs = ps.executeQuery();
            //armo el aobjeto alumno
            while(rs.next()){                
                a.setId(rs.getInt("id_alumno"));
                a.setDni(rs.getInt("dni"));
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                a.setEstado(rs.getBoolean("estado"));
            }
            //cierro el query
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;    
    } 
    
     public void actualizar(Alumno a){
         try {
            //agregar un alumno
            String query = "UPDATE alumno SET dni=?,apellido=?,nombre=?,fecha_nacimiento=?,estado=? WHERE 1";
            //armar sentencia de sql
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setString(4, a.getFechaNacimiento());
            ps.setBoolean(5, a.isEstado());
            //ejecutar query executeUpdate devuelve un entero
            ps.executeUpdate(); 
            //recupero el registro y lo asigno a la clase alumno 
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                a.setId(rs.getInt(1));
            else
                System.out.println("No se pudo guardar");
            ps.close();
            System.out.println("Guardado");
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    
}
