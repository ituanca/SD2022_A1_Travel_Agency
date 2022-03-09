package service.validators;

import javafx.scene.control.Alert;

import java.util.regex.Pattern;

public class NameValidator implements Validator<String>{
    private static final String NAME_PATTERN = "^(?![\\s.]+$)[a-zA-Z\\s.]*$";

    @Override
    public CasesOfInvalidations validate(String s) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if(!pattern.matcher(s).matches()){
            return CasesOfInvalidations.INVALID_FORM_NAME;
        }
        return CasesOfInvalidations.GOOD;
    }
}
