package com.example.project01.model;

import com.example.project01.db.DatabaseConnection;
import com.example.project01.dto.StockDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StockModel {
    public static int insertStock(StockDTO stock) {
        String sql = "INSERT INTO stock (product_id, quantity, update_time) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, stock.getProductId());
            ps.setInt(2, stock.getQuantity());
            ps.setTimestamp(3, Timestamp.valueOf(stock.getUpdateTime()));

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int updateStock(StockDTO stock) {
        String sql = "UPDATE stock SET product_id=?, quantity=?, update_time=? WHERE id=?";

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, stock.getProductId());
            ps.setInt(2, stock.getQuantity());
            ps.setTimestamp(3, Timestamp.valueOf(stock.getUpdateTime()));
            ps.setInt(4, stock.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int deleteStock(int id) {
        String sql = "DELETE FROM stock WHERE id=?";

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<StockDTO> getAllStock() {
        String sql = "SELECT * FROM stock";
        List<StockDTO> stockList = new ArrayList<>();

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                StockDTO stock = StockDTO.builder()
                        .id(rs.getInt("id"))
                        .productId(rs.getString("product_id"))
                        .quantity(rs.getInt("quantity"))
                        .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                        .build();
                stockList.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    public static StockDTO getStockById(int id) {
        String sql = "SELECT * FROM stock WHERE id=?";
        StockDTO stock = null;

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                stock = StockDTO.builder()
                        .id(rs.getInt("id"))
                        .productId(rs.getString("product_id"))
                        .quantity(rs.getInt("quantity"))
                        .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }
}
