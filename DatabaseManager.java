/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p2;

/**
 *
 * @author Sreey
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/myDatabase?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "Sreeya"; 
    private static final String PASSWORD = "Password"; 


    public static void init() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    public static void clearGameHistory() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM game_history")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to clear game history", e);
        }
    }

    public static void insertGameHistory(int winner, boolean isCrossWinner) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO game_history (winner, is_cross_winner) VALUES (?,?)");
            statement.setInt(1, winner);
            statement.setBoolean(2, isCrossWinner);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert game history", e);
        }
    }
}
