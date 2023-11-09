package Controlador;

import ConexionSQL.ConexionMySQL;
import Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private String nombre;
    private String correo;
    private String contrasena;

    public void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, contrasena) VALUES (?, ?, ?)";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getContrasena());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = null;
        String sql = "SELECT nombre, contrasena FROM usuarios WHERE correo = ?";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, correo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String contrasena = resultSet.getString("contrasena");
                    usuario = new Usuario(nombre, correo, contrasena);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
