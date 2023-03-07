package kodlama.io.rentACar.core.utilities.mappers;

import org.modelmapper.ModelMapper;

//ModelMapper interfacesini yapti
public interface ModelMapperService {

    ModelMapper forResponse();
    ModelMapper forRequest();
}
