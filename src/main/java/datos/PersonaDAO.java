package datos;
//PATRON DE DISENO: DATA ACCESS OBJECT "DAO"

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonaDAO {
    //SENTENCIA BASICA
    //private static final String SQL_SELECT = "SELECT * FROM test.persona"

    private static final String SQL_SELECT = "SELECT id_persona,nombre,apellido,email,telefono FROM test.persona";

    public List<Persona> seleccionar() {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resulSet = null;
        Persona persona = null;

        List<Persona> personas = new ArrayList<>();

        try {
            conexion = getConnection();
            preparedStatement = conexion.prepareStatement(SQL_SELECT);
            resulSet = preparedStatement.executeQuery();

            while (resulSet.next()) {
                int idPersona = resulSet.getInt("id_persona");
                String nombre = resulSet.getString("nombre");
                String apellido = resulSet.getString("apellido");
                String email = resulSet.getString("email");
                String telefono = resulSet.getString("telefono");

                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                personas.add(persona);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(resulSet);
                Conexion.close(preparedStatement);
                Conexion.close(conexion);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return personas;
    }

}
