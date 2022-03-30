import Model.Brand;
import Model.Category;
import Model.Product;
import Service.BrandService;
import Service.CategoryService;
import Service.ProductService;
import Service.UserService;
import ServiceImpl.BrandServiceImpl;
import ServiceImpl.CategoryServiceImpl;
import ServiceImpl.ProductServiceImpl;
import ServiceImpl.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BrandService brandService = new BrandServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();
        UserService userService = new UserServiceImpl();
        ProductService productService = new ProductServiceImpl();

       /* List<Brand>brands =brandService.findBrands();
        for (Brand brand : brands){
            System.out.println("BrandId : " +brand.getBrandId() + " - " + brand.getBrandName());
         }
       */
        /*
        Brand brand = brandService.findBrandById(100);
        System.out.println(brand.getBrandName());
         */

       /* List<Category>categories = categoryService.findCategories();
        for(Category category : categories){
            System.out.println(category.getCategoryId() + " - " + category.getCategoryName());
              }
        */

        Category category = categoryService.findCategoryById(11);
        System.out.println(category.getCategoryName());


    }
}
