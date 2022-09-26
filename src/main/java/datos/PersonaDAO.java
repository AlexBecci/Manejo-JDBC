package datos;
//PATRON DE DISENO: DATA ACCESS OBJECT "DAO"

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAO {
    //SENTENCIA BASICA
    //private static final String SQL_SELECT = "SELECT * FROM test.persona"

    private static final String SQL_SELECT = "SELECT id_persona,nombre,apellido,email,telefono FROM test.persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre,apellido,email,telefono)   VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE test.persona SET nombre = ?,apellido = ?, email = ?, telefono =? WHERE id_persona = ?;";
    private static final String SQL_DELETE = "DELETE from test.persona WHERE id_persona = ?";

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
                close(resulSet);
                close(preparedStatement);
                close(conexion);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return personas;
    }

    public int actualizar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());

            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int insertar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());

            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());

            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
}
