package service.validators;


public class NumberValidator implements Validator<String>{
    @Override
    public CasesOfInvalidations validate(String s) {
        if(Integer.parseInt(s)>0){
            return CasesOfInvalidations.GOOD;
        }
        return CasesOfInvalidations.NOT_GOOD;
    }
}
