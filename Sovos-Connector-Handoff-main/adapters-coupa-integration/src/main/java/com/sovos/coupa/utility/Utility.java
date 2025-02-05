package com.sovos.coupa.utility;

import com.sovos.coupa.controller.dto.AddressCoupaRequest;

public class Utility {

    private Utility() {

        throw new IllegalStateException("Do not instantiate this class.");
    }

    public static String preventNull(String string) {

        return substituteNull(string, "");
    }

    public static String substituteNull(String string, String replacement) {

        if (string == null) {
            return replacement;
        }
        return string;
    }

    public static Object defaultIfNull(Object desired, Object replacement) {

        Object returnedObject = desired;
        if (desired == null) {
            returnedObject = replacement;
        }
        return returnedObject;
    }


    public static String nullIfEmpty(String input) {
        String returnValue = input;
        if (input == null || input.isEmpty()) {
            returnValue = null;
        }
        return returnValue;
    }

    /**
     * Accepts a 5 or 9 digit zipcode as input. Returns an array of length 0, 1,
     * or 2 with no zip codes, Just the Zip 5 or with the Zip5 and +4 in
     * seperate elements. If there is no Zip5 or Zip4 Code, null is returned.
     *
     * @param postalCode
     * @return
     */
    public static String[] breakZip9CodeInHalf(String postalCode) {
        String[] rValue = new String[3];

        if (postalCode == null || (postalCode.length() != 5 && postalCode.length() != 10)) {
            rValue[0] = postalCode;
            rValue[1] = null;
            rValue[2] = null;
            return rValue;
        } else {
            rValue[0] = null;
            String[] zipBits = postalCode.split("-");
            if (zipBits.length == 1 || zipBits.length == 2) {
                rValue[1] = zipBits[0];
            }
            if (zipBits.length == 2) {
                rValue[2] = zipBits[1];
            }
            return rValue;
        }
    }

	/**
	 * Validate coupa address
	 *
	 * @param AddressCoupaRequest
	 * @return boolean
	 */

	public static boolean validateAddress(AddressCoupaRequest address) {
		return address.getCity() != null &&
				address.getState() != null &&
				address.getCountry() != null &&
				address.getStreet1() != null &&
				address.getPostalCode() != null;
	}

}
