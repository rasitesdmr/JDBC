package Service;

import Model.Brand;

import java.util.List;

public interface BrandService {
    Brand findBrandById(int id);

    List<Brand> findBrands();

}
