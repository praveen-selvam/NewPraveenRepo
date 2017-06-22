package com.autotrek.instantauto.web.controllers;

import com.autotrek.instantauto.ApiExceptionBuilder;
import com.autotrek.instantauto.domain.Dealer;
import com.autotrek.instantauto.domain.User;
import com.autotrek.instantauto.services.DealerService;
import com.autotrek.instantauto.web.dto.DealerDto;
import com.autotrek.instantauto.web.dto.converters.DealerDtoToDealerConverter;
import com.autotrek.instantauto.web.dto.converters.DealerToDealerDtoConverter;
import com.autotrek.instantauto.web.util.SecurityContextHelper;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author praveens
 */
@RestController
@RequestMapping("/dealer")
public class DealerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealerController.class);

    @Autowired
    private DealerService dealerService;

    @Autowired
    private DealerToDealerDtoConverter dealerToDealerDtoConverter;

    @Autowired
    private DealerDtoToDealerConverter dealerDtoToDealerConverter;

    @Autowired
    private SecurityContextHelper securityContextHelper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<DealerDto> getAllDealers() {
        List<Dealer> dealers = dealerService.getAllDealers();
       
        ArrayList<DealerDto> ret = new ArrayList<>();

        dealers.stream().map((dealer) -> {
            DealerDto dto = dealerToDealerDtoConverter.convert(dealer);
            return dto;
        }).forEachOrdered((dto) -> {
            ret.add(dto);
        });

        return ret;
    }

    @RequestMapping(value = "/{dealerId}", method = RequestMethod.GET)
    public DealerDto getDealerInfo(@PathVariable Long dealerId) {
        Dealer dealer = dealerService.getDealerById(dealerId);
        if (dealer != null) {
            DealerDto dto = dealerToDealerDtoConverter.convert(dealer);
            return dto;
        } else {
            throw ApiExceptionBuilder.NOT_FOUND.build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public DealerDto addDealer(@RequestBody DealerDto dealer) {
        User caller = securityContextHelper.getCallingUser();
        Dealer nDealer = dealerDtoToDealerConverter.convert(dealer);
        nDealer.setCreatedBy(caller);

        Dealer savedDealer = dealerService.addDealer(nDealer);
        
        return dealerToDealerDtoConverter.convert(savedDealer);
    }
}
