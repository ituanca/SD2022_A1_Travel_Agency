package service.validators;

public class EmptyFieldValidator implements Validator<String>{

    @Override
    public CasesOfInvalidations validate(String s) {
        if(s.isEmpty()){
            return CasesOfInvalidations.EMPTY;
        }
        return CasesOfInvalidations.GOOD;
    }
}
