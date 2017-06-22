package com.autotrek.instantauto.web.dto.converters;

import com.autotrek.instantauto.domain.Dealer;
import com.autotrek.instantauto.web.dto.DealerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author praveens
 */
@Component
public class DealerDtoToDealerConverter implements Converter<DealerDto, Dealer> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Dealer convert(DealerDto source) {
        Dealer dealer = modelMapper.map(source, Dealer.class);
        return dealer;
    }
}
