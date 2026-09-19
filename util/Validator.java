package util;

import java.util.regex.Pattern;

public class Validator {

    public static final Pattern MOBILE_PATTERN =
            Pattern.compile("^[6-9][0-9]{9}$");

    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static final Pattern PAN_PATTERN =
            Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");

    public static final Pattern IFSC_PATTERN =
            Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String mobile) {
        return MOBILE_PATTERN.matcher(mobile).matches();
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPAN(String pan) {
        return PAN_PATTERN.matcher(pan).matches();
    }

    public static boolean isValidIFSC(String ifsc) {
        return IFSC_PATTERN.matcher(ifsc).matches();
    }
}