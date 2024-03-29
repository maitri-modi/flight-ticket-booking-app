package devRev.user;
import devRev.user.exception.InValidEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    private final String regex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    private final Pattern pattern = Pattern.compile(regex);


    public boolean isEmailValid(String email) {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new InValidEmailException("Invalid email");
        }
        return true;
    }
}
