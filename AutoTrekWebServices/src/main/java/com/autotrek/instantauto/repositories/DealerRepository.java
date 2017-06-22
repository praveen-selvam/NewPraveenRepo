package com.autotrek.instantauto.repositories;

import com.autotrek.instantauto.domain.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author praveens
 */
public interface DealerRepository extends JpaRepository<Dealer, Long> {

    /**
     * Fetch the dealer by the dealer name.
     *
     * @param dealerName
     * @return
     */
    public Dealer findDealerByDealerName(String dealerName);
}
