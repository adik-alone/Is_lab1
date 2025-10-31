package ru.is_lab1.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@ApplicationScoped
public class ModelMapperProducer {
    @Produces
    @ApplicationScoped
    public ModelMapper produceModelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setAmbiguityIgnored(true);

        return modelMapper;
    }
}
