package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBussinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBussinessRules brandBussinessRules;


    @Override
    public List<GetAllBrandsResponse> getAll() {

        List<Brand> brands = brandRepository.findAll();//Veritabanindan gelen 3 tane marka var.

        List<GetAllBrandsResponse> brandsResponse = brands.stream()
                .map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
                .collect(Collectors.toList());

        //business rules
        return brandsResponse;
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        //OrElseThrow ile Optional a gerek kalmadan geebiliriz
        Brand brand = this.brandRepository.findById(id).orElseThrow();
        GetByIdBrandResponse getByIdBrandResponse = this.modelMapperService
                .forResponse().map(brand, GetByIdBrandResponse.class);
        return getByIdBrandResponse;
    }

//    @Override
//    public CreateBrandRequest add(CreateBrandRequest createBrandRequest) {
//        //Brand nesnesini request icerisinde gezerek
//        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
//
//        this.brandRepository.save(brand);
//        return createBrandRequest;
//    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        //Brand nesnesini request icerisinde gezerek
        this.brandBussinessRules.checkIfBrandNAmeExists(createBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);

        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        this.brandRepository.deleteById(id);
    }
}
