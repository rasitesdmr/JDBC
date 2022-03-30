package Implement;

import Connection.DBConnection;
import Model.Brand;
import Model.Category;
import Model.Product;
import Model.User;
import Queries.UserQueries;
import Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Logger LOGGER = LogManager.getLogger();

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;


    @Override
    public User saveUser(User user) {
        connection = DBConnection.getConnection();
        try {
            //"INSERT INTO user(userId,firstName,lastName,birthOfDate,userName) VALUES(?,?,?,?,?)"
            preparedStatement = connection.prepareStatement(UserQueries.saveUserQueries);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setDate(4, user.getBirthOfDate());
            preparedStatement.setString(5, user.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(user.getUserId() + "User kaydedilirken hata meydana geldi . HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }


    @Override
    public boolean saveUserProduct(int userId, int productId) {
        connection = DBConnection.getConnection();
        try {
            //"INSERT INTO user_product(userId,productId) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(UserQueries.saveUser_ProductQueries);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("User_Product kaydedilirken hata meydana geldi . HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return true;
    }


    @Override
    public User updateUser(User user) {
        connection = DBConnection.getConnection();
        try {
            // "UPDATE user SET firstName =?,lastName =?,birthOfDate =?,userName =? WHERE userId =?"
            preparedStatement = connection.prepareStatement(UserQueries.updateUserQueries);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, user.getBirthOfDate());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("User güncellenirken hata meydana geldi . HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }


    @Override
    public boolean removeUser(int id) {
        connection = DBConnection.getConnection();
        // deleteUser_ProductQueries ="DELETE FROM user_product WHERE userId =?"
        //deleteUserByIdQueries = "DELETE FROM user WHERE userId =?";
        try {
            preparedStatement = connection.prepareStatement(UserQueries.deleteUser_ProductQueries);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(UserQueries.deleteUserByIdQueries);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("User silinirken hata meydana geldi . HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return true;
    }


    @Override
    public User findUserById(int id) {
        connection = DBConnection.getConnection();
        //findUserByIdQueries = "SELECT * FROM user WHERE userId";

        User user = null;
        try {
            preparedStatement = connection.prepareStatement(UserQueries.findUserByIdQueries);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Date birthOfDate = resultSet.getDate("birthOfDate");
                String username = resultSet.getString("username");
                user = new User(userId, firstName, lastName, birthOfDate, username);
            }

        } catch (SQLException e) {
            LOGGER.warn("User bulunurken hata meydana geldi . HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }


    @Override
    public User findUserProductById(int id) {
        connection = DBConnection.getConnection();
        /* "SELECT * FROM user u LEFT OUTER JOIN user_product up ON(u.userId = up.userId) " +
                    "LEFT JOIN product p ON(up.productId = p.productId) " +
                            "LEFT JOIN category c ON(p.categoryId = c.categoryId) " +
                            "LEFT JOIN brand b ON(p.breadId = b.breadId) " +
                           "WHERE u.userId =?";
                           */

        User user = null;
        try {
            preparedStatement = connection.prepareStatement(UserQueries.findUserProductQueries);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            boolean durum = true;

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                if (durum) {
                    int userId = resultSet.getInt("userId");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    Date birthOfDate = resultSet.getDate("birthOfDate");
                    String username = resultSet.getString("username");
                    user = new User(userId, firstName, lastName, birthOfDate, username);
                    durum = false;
                }
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

                Product product = new Product(productId, productName, unitPrice, avaible, addDate, updateDate, category, brand);
                products.add(product);
            }
            user.setProducts(products);
        } catch (SQLException e) {
            LOGGER.warn("User ve ürünleri bulunurken hata meydana geldi . HATA : " + e);
        } finally {
            DBConnection.closeConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public List<User> findUsers() {
        connection = DBConnection.getConnection();
        // findUsersQueries = "SELECT * FROM user";
        List<User>users = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(UserQueries.findUsersQueries);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Date birthOfDate = resultSet.getDate("birthOfDate");
                String username = resultSet.getString("username");
                User user = new User(userId,firstName,lastName, birthOfDate, username);
                users.add(user);

            }
        } catch (SQLException e) {
            LOGGER.warn("User listesi alınırken hata meydana geldi . HATA : " + e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return users;
    }
}
