package devRev.user;
import devRev.user.exception.InValidMobileNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileNumberValidation {

    private final String regex = "^[6-9]\\d{9}$";
    private final Pattern pattern = Pattern.compile(regex);

    public boolean isMobileNumberValid(String mobileNumber) {
        Matcher matcher = pattern.matcher(mobileNumber);
        if (!matcher.find()) {
            throw new InValidMobileNumberException("Invalid mobile number");
        }
        return true;

    }
}
