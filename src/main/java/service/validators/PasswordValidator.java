package service.validators;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String>{

    // Check if a string contains uppercase, lowercase, special characters and numeric values
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

    @Override
    public CasesOfInvalidations validate(String s) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        if (s.length() < 8) {
            return CasesOfInvalidations.TOO_SHORT;
        }else if(!pattern.matcher(s).matches()){
            return CasesOfInvalidations.INVALID_FORM_PASSWORD;
        }
        return CasesOfInvalidations.GOOD;
    }
}
