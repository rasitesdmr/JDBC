package ServiceImpl;

import Implement.CategoryRepositoryImpl;
import Model.Category;
import Repository.CategoryRepository;
import Service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();


    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findCategories();
    }
}
