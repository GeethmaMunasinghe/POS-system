package com.example.project01.model;

import com.example.project01.db.DatabaseConnection;
import com.example.project01.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductModel {

    private static final Logger logger = Logger.getLogger(ProductModel.class.getName());
    private static DatabaseConnection DatabaseConnection;

    private ProductModel() {
    }

    public static List<String> getSuppliersId(){

        String getAll = "SELECT sid FROM supplier";

        ArrayList<String> supplierArrayList = new ArrayList<>();
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnection.getDataBaseConnection().getConnection();

            preparedStatement = connection.prepareStatement(getAll);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                supplierArrayList.add(resultSet.getString(1));
            }
        }catch (SQLException e){
            logger.info(e.getMessage());
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
        return supplierArrayList;

    }

    public static int saveNewProduct(ProductDTO productDTO){

        String addNewProduct = "INSERT INTO products (name,description,unit_price,supplier_id) VALUES (?,?,?,?)";

        int result = 0;

        Connection connection;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getDataBaseConnection().getConnection();
            preparedStatement = connection.prepareStatement(addNewProduct);

            preparedStatement.setString(1,productDTO.getName());
            preparedStatement.setString(2,productDTO.getDescription());
            preparedStatement.setInt(3,productDTO.getUnit_price());
            preparedStatement.setString(4,productDTO.getSupplierID());

            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.info(e.getMessage());
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }

        return result;

    }

    public static int deleteProduct(Integer id){

        String deleteProduct = "DELETE FROM products WHERE product_id=?";

        Connection connection;
        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            connection = DatabaseConnection.getDataBaseConnection().getConnection();

            preparedStatement = connection.prepareStatement(deleteProduct);
            preparedStatement.setInt(1,id);

            result = preparedStatement.executeUpdate();

        }catch (SQLException e){
            logger.info(e.getMessage());
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
        return result;
    }

    public static List<ProductDTO> getAllProducts(){

        String getAll = "SELECT * FROM products";

        ArrayList<ProductDTO> productDTOArrayList = new ArrayList<>();

        Connection connection;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getDataBaseConnection().getConnection();
            preparedStatement = connection.prepareStatement(getAll);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                var product = ProductDTO.builder()
                        .id(resultSet.getString(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .unit_price(resultSet.getInt(4))
                        .supplierID(resultSet.getString(5)).build();

                productDTOArrayList.add(product);
            }

        }catch (SQLException e){
            logger.info(e.getMessage());
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }

        return productDTOArrayList;

    }

    public static int updateProductDetails(ProductDTO product) {

        String updateNewSupplier = "UPDATE products SET name=?, description=?, unit_price=?, supplier_id=? WHERE product_id=?";

        Connection connection;
        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            connection = DatabaseConnection.getDataBaseConnection().getConnection();
            preparedStatement = connection.prepareStatement(updateNewSupplier);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setInt(3,product.getUnit_price());
            preparedStatement.setString(4,product.getSupplierID());
            result = preparedStatement.executeUpdate();

        }catch (SQLException e){
            logger.info(e.getMessage());
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
        return result;
    }


    public static ProductDTO getProductByID(Integer id) {

        String getProductDetails = "SELECT * FROM products WHERE product_id = ? ";

        Connection connection;
        PreparedStatement preparedStatement = null;

        ProductDTO productDTO = null;

        try {
            connection = DatabaseConnection.getDataBaseConnection().getConnection();

            preparedStatement = connection.prepareStatement(getProductDetails);
            preparedStatement.setInt(1,id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {

                productDTO = ProductDTO.builder()
                        .id(result.getString(1))
                        .name(result.getString(2))
                        .description(result.getString(3))
                        .unit_price(result.getInt(4))
                        .supplierID(result.getString(5))
                        .build();
            }

        }catch (SQLException e){
            logger.info(e.getMessage());
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
        return productDTO;
    }
}