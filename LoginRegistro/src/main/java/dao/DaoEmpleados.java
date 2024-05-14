package dao;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.MySQLConexion; // Importa la clase que gestiona la conexión a la base de datos
import modelo.Empleado;

public class DaoEmpleados {
    // Método para obtener un empleado por su correo electrónico
    public Empleado obtenerEmpleadoPorCorreo(String correo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleado empleado = null;

        try {
            conn = MySQLConexion.getConexion(); // Obtiene la conexión del paquete util
            String sql = "SELECT * FROM empleados WHERE emp_correo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                empleado = new Empleado();
                empleado.setEmp_codigo(rs.getString("emp_codigo"));
                empleado.setEmp_nombre(rs.getString("emp_nombre"));
                empleado.setEmp_correo(rs.getString("emp_correo"));
                empleado.setEmp_pswd(rs.getString("emp_pswd"));
                // Agregar otros campos según sea necesario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, stmt, rs); // Método para cerrar la conexión y liberar recursos
        }

        return empleado;
    }

    // Método para registrar un nuevo empleado en la base de datos
    public boolean registrarEmpleado(Empleado empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "INSERT INTO empleados (emp_codigo, emp_nombre, emp_correo, emp_pswd, emp_cargo) VALUES (?, ?, ?, ?, 'Empleado')";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, empleado.getEmp_codigo());
            stmt.setString(2, empleado.getEmp_nombre());
            stmt.setString(3, empleado.getEmp_correo());
            stmt.setString(4, empleado.getEmp_pswd());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarRecursos(conn, stmt, null);
        }
    }

    // Otros métodos CRUD y métodos de utilidad irían aquí

    // Método para cerrar la conexión y liberar recursos
    private void cerrarRecursos(Connection conn, PreparedStatement stmt, ResultSet rs) {
        // Implementación para cerrar la conexión y liberar recursos
    }
    
}
