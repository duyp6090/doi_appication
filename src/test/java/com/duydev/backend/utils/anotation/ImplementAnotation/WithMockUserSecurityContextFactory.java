package com.duydev.backend.utils.anotation.ImplementAnotation;

import com.duydev.backend.domain.model.User;
import com.duydev.backend.utils.anotation.IFunctional.WithMockCustomUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.List;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        // Create role and permissions
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (String role : annotation.roles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        for (String permission : annotation.permissions()) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        // Custom User
        User user = User.builder()
                .username(annotation.username())
                .password("password")
                .build();
        user.setId(annotation.userId());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                authorities
        );

        context.setAuthentication(auth);
        return context;
    }
}
