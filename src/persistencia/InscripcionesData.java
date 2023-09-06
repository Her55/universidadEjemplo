/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;

/**
 *
 * @author Hernan
 */
public class InscripcionesData {
    private Connection conexion=null;

    public InscripcionesData(Conexion conexion) {
        this.conexion = conexion.establecerConexion();
    }
    
}
