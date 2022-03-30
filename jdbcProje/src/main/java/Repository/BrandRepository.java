package Repository;

import Model.Brand;

import java.util.List;

public interface BrandRepository {
    Brand findBrandById(int id);

    List<Brand> findBrands();
}
