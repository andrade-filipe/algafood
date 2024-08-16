package com.esr.algafood.core.validation;

import com.esr.algafood.core.validation.annotations.FreeDeliveryRule;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class FreeDeliveryRuleValidator implements ConstraintValidator<FreeDeliveryRule, Object> {

    private String fieldValue;
    private String fieldDescription;
    private String mustHave;

    @Override
    public void initialize(FreeDeliveryRule constraintAnnotation) {
        this.fieldValue = constraintAnnotation.fieldValue();
        this.fieldDescription = constraintAnnotation.fieldDescription();
        this.mustHave = constraintAnnotation.mustHave();
    }

    @Override
    public boolean isValid(Object objectOfValidation, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        try {
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(objectOfValidation.getClass(), fieldValue)
                .getReadMethod().invoke(objectOfValidation);

            String descricao = (String) BeanUtils.getPropertyDescriptor(objectOfValidation.getClass(), fieldDescription)
                .getReadMethod().invoke(objectOfValidation);

            if(value != null && BigDecimal.ZERO.compareTo(value) == 0 && descricao != null && !descricao.isBlank()) {
                valid = descricao.toLowerCase().contains(this.mustHave.toLowerCase());
            }

            return valid;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
