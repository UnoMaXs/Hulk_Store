package ConexionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoSQL {

    public static void agregarProducto(String nombre, String descripcion, double precio, int cantidad) {
        String sql = "INSERT INTO productos (nombre,descripcion,precio,cantidad) VALUES (?, ?, ?,?)";

        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3,precio);
            statement.setInt(4,cantidad);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
