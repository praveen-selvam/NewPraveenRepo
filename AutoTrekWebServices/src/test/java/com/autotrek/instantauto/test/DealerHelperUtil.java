package com.autotrek.instantauto.test;

import com.autotrek.instantauto.web.dto.DealerDto;

/**
 * Helper methods for testing Dealers.
 *
 * @author Joe C. McPherson
 */
public class DealerHelperUtil {

    /**
     * Create a mock dealer dto for creation through the endpoint.
     *
     * @return
     */
    public static DealerDto createMockDealerForCreate() {
        DealerDto ret = new DealerDto();
        ret.dealerName = StringHelperUtil.generateRandomString(10);
        ret.contactName = "contact name";
        ret.contactEmailId = "contact@somewhere.com";
        ret.contactMobile = "1234567890";
        ret.designation = "Some Designation";
        ret.iqamaNo = "no-1234567890";
        ret.dealerImagePath = "/some/server/location";
        ret.addressLine1 = "Address Line 1";
        ret.city = "Somewhere";
        ret.state = "Rainbow";
        ret.country = "Not Magical";
        ret.postalCode = "12345";

        return ret;
    }
}
