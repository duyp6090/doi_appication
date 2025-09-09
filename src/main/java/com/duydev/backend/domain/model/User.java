package com.duydev.backend.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.duydev.backend.domain.enums.StatusUser;
import com.duydev.backend.domain.enums.TypeUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User extends AbstractEntity<Long> implements UserDetails  {
    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "type_user")
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    @Column(name = "status_user")
    @Enumerated(EnumType.STRING)
    private StatusUser statusUser;

    @OneToMany(mappedBy = "user")
    private Set<UserHasGroupEntity> userHasGroups;
    
    @OneToMany(mappedBy = "user")
    private Set<UserHasRoleEntity> userHasRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Get list of roles
        List<RoleEntity> roles = userHasRoles.stream().map(UserHasRoleEntity::getRole).toList();

        // Create ArrayList to save role name and permissions
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // For each role, get list of permissions
        for(RoleEntity role: roles){
            String roleName = "ROLE_" + role.getName();
            authorities.add(new SimpleGrantedAuthority(roleName));
            List<PermissionEntity> permissions = role.getRoleHasPermissions().stream().map(RoleHasPermissionEntity::getPermission).toList();
            permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));
        }

        // Map permissions to GrantedAuthority
        return authorities;
    }

}
