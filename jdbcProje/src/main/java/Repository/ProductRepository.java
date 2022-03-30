package Repository;

import Model.Product;

import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);

    boolean saveBatchProduct(List<Product> products);

    Product updateProduct(Product product);

    boolean removeProduct(int id);

    Product findProduct(int id);

    List<Product> findProducts();
}
