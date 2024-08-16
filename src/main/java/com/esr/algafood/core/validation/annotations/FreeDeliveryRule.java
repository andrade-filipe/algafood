package com.esr.algafood.core.validation.annotations;

import com.esr.algafood.core.validation.FreeDeliveryRuleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { FreeDeliveryRuleValidator.class })
public @interface FreeDeliveryRule {

    String message() default "o 'nome' deve conter 'FRETE GRÁTIS'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldValue();

    String fieldDescription();

    String mustHave();
}
