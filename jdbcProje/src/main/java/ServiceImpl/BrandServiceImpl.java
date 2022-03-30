package ServiceImpl;

import Implement.BrandRepositoryImpl;
import Model.Brand;
import Repository.BrandRepository;
import Service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository = new BrandRepositoryImpl();

    @Override
    public Brand findBrandById(int id) {
        return brandRepository.findBrandById(id);
    }

    @Override
    public List<Brand> findBrands() {
        return brandRepository.findBrands();
    }
}
