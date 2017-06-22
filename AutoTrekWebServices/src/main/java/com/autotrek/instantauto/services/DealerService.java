package com.autotrek.instantauto.services;

import com.autotrek.instantauto.domain.Dealer;
import java.util.List;

/**
 *
 * @author praveens
 */
public interface DealerService {

    public List<Dealer> getAllDealers();

    public Dealer getDealerById(Long id);

    public Dealer addDealer(Dealer dealer);
}
