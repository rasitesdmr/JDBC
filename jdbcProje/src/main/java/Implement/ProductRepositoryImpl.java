package Implement;

import Connection.*;
import Model.Brand;
import Model.Category;
import Model.Product;
import Queries.ProductQueries;
import Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final Logger LOGGER = LogManager.getLogger();

    private Connection connection = null;

    private PreparedStatement preparedStatement = null;

    private ResultSet resultSet = null;


    @Override
    public Product saveProduct(Product product) {
        connection = DBConnection.getConnection();
        //saveProductQueries = "INSERT INTO product(productId,productName,unitPrice,available,addDate,
        // updateDate,categoryId,brandId) VALUES(?,?,?,?,?,?,?,?)";

        try {
            LocalDateTime localDateTime = LocalDateTime.now();

            preparedStatement = connection.prepareStatement(ProductQueries.saveProductQueries);
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setDouble(3, product.getUnitPrice());
            preparedStatement.setInt(4, product.getAvailable());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(6, null);

            //Dikkat product nesnesine category vermezsek null pointer exception çıkar

            preparedStatement.setInt(7, product.getCategory().getCategoryId());
            preparedStatement.setInt(8, product.getBrand().getBrandId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("Ürün eklenirken hata oluştu . HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return product;
    }


    @Override
    public boolean saveBatchProduct(List<Product> products) {
        connection = DBConnection.getConnection();
        //saveProductQueries = "INSERT INTO product(productId,productName,unitPrice,available,addDate,
        // updateDate,categoryId,brandId) VALUES(?,?,?,?,?,?,?,?)";

        try {
            LocalDateTime localDateTime = LocalDateTime.now();

            preparedStatement = connection.prepareStatement(ProductQueries.saveProductQueries);

            for (Product product : products) {
                preparedStatement.setInt(1, product.getProductId());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setDouble(3, product.getUnitPrice());
                preparedStatement.setInt(4, product.getAvailable());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
                preparedStatement.setTimestamp(6, null);

                //Dikkat product nesnesine category vermezsek null pointer exception çıkar

                preparedStatement.setInt(7, product.getCategory().getCategoryId());
                preparedStatement.setInt(8, product.getBrand().getBrandId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            LOGGER.warn("Ürün listesi eklenirken hata oluştu . HATA : " + e);
            return false;
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return true;
    }


    @Override
    public Product updateProduct(Product product) {
        connection = DBConnection.getConnection();
        // updateProductQueries = "UPDATE product SET productName =?,unitPrice =?,
        // available =?,updateDate=?,categoryId =?,brandId =? WHERE productId =?";

        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            preparedStatement = connection.prepareStatement(ProductQueries.updateProductQueries);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getUnitPrice());
            preparedStatement.setInt(3, product.getAvailable());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
            preparedStatement.setInt(5, product.getCategory().getCategoryId());
            preparedStatement.setInt(6, product.getBrand().getBrandId());
            preparedStatement.setInt(7, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("Ürün güncellenirken hata meydana geldi");
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return product;
    }

    @Override
    public boolean removeProduct(int prductId) {
        connection = DBConnection.getConnection();
        //deleteUser_productQueries = "DELETE FROM user_product WHERE productId=?";
        //deleteProductQueries = "DELETE FROM product WHERE productId=?";

        try {
            preparedStatement = connection.prepareStatement(ProductQueries.deleteUser_productQueries);
            preparedStatement.setInt(1, prductId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ProductQueries.deleteProductQueries);
            preparedStatement.setInt(1, prductId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("ürün silinirken hata meydana geldi . HATA : " + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return true;
    }


    @Override
    public Product findProduct(int id) {
        connection = DBConnection.getConnection();
        Product product = null ;
        //findProductByIdQueries = "SELECT * FROM product p LEFT JOIN category c
        // on(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId) WHERE productId =?";
        try {
            preparedStatement = connection.prepareStatement(ProductQueries.findProductByIdQueries);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){

                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                double unitPrice = resultSet.getDouble("unitPrice");
                int avaible = resultSet.getInt("avaible");
                Date addDate = resultSet.getDate("addDate");
                Date updateDate = resultSet.getDate("updateDate");

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                int brandId = resultSet.getInt("brandId");
                String brandName = resultSet.getString("brandName");

                Category category = new Category(categoryId, categoryName);
                Brand brand = new Brand(brandId, brandName);

               product = new Product(productId, productName, unitPrice, avaible, addDate, updateDate, category, brand);
            }
        } catch (SQLException e) {
        LOGGER.warn(id + "id li ürün aranırken hata meydana geldi . HATA :" + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return product;
    }

    @Override
    public List<Product> findProducts() {
        connection = DBConnection.getConnection();
        List<Product> products = new ArrayList<>();
        Product product = null;
        //findProductsQueries = "SELECT * FROM product p LEFT JOIN category c
        // on(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId)";
        try {
            preparedStatement =connection.prepareStatement(ProductQueries.findProductsQueries);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                double unitPrice = resultSet.getDouble("unitPrice");
                int avaible = resultSet.getInt("avaible");
                Date addDate = resultSet.getDate("addDate");
                Date updateDate = resultSet.getDate("updateDate");

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                int brandId = resultSet.getInt("brandId");
                String brandName = resultSet.getString("brandName");

                Category category = new Category(categoryId, categoryName);
                Brand brand = new Brand(brandId, brandName);

                product = new Product(productId, productName, unitPrice, avaible, addDate, updateDate, category, brand);
                products.add(product);
            }
        } catch (SQLException e) {
        LOGGER.warn("Ürünler listelenirken hata oluştu. HATA : " + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return products;
    }
}
