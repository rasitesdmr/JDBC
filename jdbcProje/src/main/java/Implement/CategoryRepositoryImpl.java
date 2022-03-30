package Implement;
import Connection.*;
import Model.Category;
import Queries.CategoryQueries;
import Repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final Logger LOGGER = LogManager.getLogger();

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;


    @Override
    public Category findCategoryById(int id) {
        connection = DBConnection.getConnection();
        Category category = null;
        try {
            preparedStatement = connection.prepareStatement(CategoryQueries.findCategoryByIdQueries);
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                category = new Category(categoryId,categoryName);
            }
        } catch (SQLException e) {
            LOGGER.warn("Category aranırken hata meydana geldi . HATA : " +e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return category;
    }

    @Override
    public List<Category> findCategories() {
        connection = DBConnection.getConnection();
        List<Category> categories =new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(CategoryQueries.findCategoriesQueries);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

               Category category = new Category(categoryId,categoryName);
               categories.add(category);
            }
        } catch (SQLException e) {
           LOGGER.warn("Category listesi aranırken hata meydana geldi . HATA : "+e);
        }finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return categories;
    }
}
