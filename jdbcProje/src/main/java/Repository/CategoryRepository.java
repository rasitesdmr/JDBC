package Repository;

import Model.Category;

import java.util.List;

public interface CategoryRepository {

    Category findCategoryById(int id);

    List<Category> findCategories();

}
