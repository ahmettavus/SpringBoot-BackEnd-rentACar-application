package kodlama.io.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAllModelsResponse {

    private int id;
    private String name;
   // - > BrandId yerine brandName getirdi ve islemlere devam etti/// private int brandId;
    private int brandName;
}
