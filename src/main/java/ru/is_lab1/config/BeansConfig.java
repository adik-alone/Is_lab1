package ru.is_lab1.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@ApplicationScoped
public class BeansConfig {

    @Produces
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration()
//                .setSkipNullEnabled(true)
//                .setFieldMatchingEnabled(true)
//                .setAmbiguityIgnored(true);
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        return modelMapper;
    }
}
