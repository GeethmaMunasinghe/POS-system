package com.example.project01.model;

import com.example.project01.db.DatabaseConnection;
import com.example.project01.dto.SupplierDTO;
import com.example.project01.tm.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public static int insertSupplier(SupplierDTO supplier) {
        String sql = "INSERT INTO supplier (sid, name, address, tel_no) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, supplier.getId());
            ps.setString(2, supplier.getName());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getTelNo());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int updateSupplier(SupplierDTO supplier) {
        String sql = "UPDATE supplier SET name=?, address=?, tel_no=? WHERE sid=?";

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getAddress());
            ps.setString(3, supplier.getTelNo());
            ps.setString(4, supplier.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int deleteSupplier(String id) {
        String sql = "DELETE FROM supplier WHERE sid=?";

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<SupplierDTO> getAllSuppliers() {
        List<SupplierDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM supplier";

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SupplierDTO supplier = SupplierDTO.builder()
                        .id(rs.getString("sid"))
                        .name(rs.getString("name"))
                        .address(rs.getString("address"))
                        .telNo(rs.getString("tel_no"))
                        .build();
                list.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static SupplierDTO searchSupplier(String id) {
        String sql = "SELECT * FROM supplier WHERE sid=?";
        SupplierDTO supplier = null;

        try (Connection con = DatabaseConnection.getDataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                supplier = SupplierDTO.builder()
                        .id(rs.getString("sid"))
                        .name(rs.getString("name"))
                        .address(rs.getString("address"))
                        .telNo(rs.getString("tel_no"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

}
