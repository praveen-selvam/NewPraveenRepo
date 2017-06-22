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
public class DealerToDealerDtoConverter implements Converter<Dealer, DealerDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DealerDto convert(Dealer source) {
        DealerDto dto = modelMapper.map(source, DealerDto.class);
        return dto;
    }

}
