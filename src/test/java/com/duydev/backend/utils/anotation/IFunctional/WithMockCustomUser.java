package com.duydev.backend.utils.anotation.IFunctional;

import com.duydev.backend.utils.anotation.ImplementAnotation.WithMockUserSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(
        factory = WithMockUserSecurityContextFactory.class
)
public @interface WithMockCustomUser {
    String username() default "user";

    long userId() default 1L;

    String[] roles() default {"USER"};

    String[] permissions() default {};
}
