package kodlama.io.rentACar.business.rules;

import kodlama.io.rentACar.core.utilities.exceptions.BussinessException;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBussinessRules {
    private BrandRepository brandRepository;

    public void checkIfBrandNAmeExists(String name) {
        if (this.brandRepository.existsByName(name)) {
            throw new BussinessException("Brand name already exists"); //Java exception types
        }
    }
}
