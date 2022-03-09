package service.validators;

import javafx.scene.control.Alert;

import java.util.regex.Pattern;

public class UsernameValidator implements Validator<String>{

    // A valid username should start with an alphabet so, [A-Za-z].
    // All other characters can be alphabets, numbers or an underscore so, [A-Za-z0-9_].
    // Since length constraint was given as 8-30 and we had already fixed the first character, so we give {7,29}.
    // We use ^ and $ to specify the beginning and end of matching.
    private static final String USERNAME_PATTERN = "^[A-Za-z][A-Za-z0-9_]{7,29}$";
    @Override
    public CasesOfInvalidations validate(String s) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        if (!pattern.matcher(s).matches()) {
            return CasesOfInvalidations.INVALID_FORM_USERNAME;
        }
        return CasesOfInvalidations.GOOD;
    }
}
