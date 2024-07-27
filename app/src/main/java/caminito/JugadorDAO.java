package caminito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JugadorDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/caminito";
    private static final String USER = "root";  // Cambia por tu usuario de MySQL
    private static final String PASSWORD = "";  // Cambia por tu contraseña de MySQL

    public void agregarJugador(String nombre) {
        String sql = "INSERT INTO usuarios (nombre) VALUES (?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
