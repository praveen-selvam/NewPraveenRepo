package com.autotrek.instantauto.services.impl;

import com.autotrek.instantauto.ApiExceptionBuilder;
import com.autotrek.instantauto.domain.Dealer;
import com.autotrek.instantauto.repositories.DealerRepository;
import com.autotrek.instantauto.services.DealerService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author praveens
 */
@Service
public class DealerServiceImpl implements DealerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealerServiceImpl.class);

    @Autowired
    private DealerRepository dealerRepository;

    @Override
    @Transactional
    public List<Dealer> getAllDealers() {
        List<Dealer> list = dealerRepository.findAll();
        return list;
    }

    @Override
    @Transactional
    public Dealer getDealerById(Long id) {
        LOGGER.debug("Finding dealer by id: {}", id);
        return dealerRepository.findOne(id);
    }

    @Override
    @Transactional
    public Dealer addDealer(Dealer dealer) {
        // Be nicer and check to see if it does not already
        // exist and provide a bit more information.
        if (!StringUtils.isEmpty(StringUtils.trimWhitespace(dealer.getDealerName()))) {
            Dealer exist = dealerRepository.findDealerByDealerName(dealer.getDealerName());
            if (exist != null) {
                throw ApiExceptionBuilder.CONFLICT.build(String.format("A Dealer with the name '%s' already exists.", dealer.getDealerName()));
            }
        } else {
            throw ApiExceptionBuilder.BAD_REQUEST.build();
        }

        return dealerRepository.save(dealer);
    }
}
