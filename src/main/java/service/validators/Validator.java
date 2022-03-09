package service.validators;

public interface Validator<T>{
    public CasesOfInvalidations validate(T t);
}
