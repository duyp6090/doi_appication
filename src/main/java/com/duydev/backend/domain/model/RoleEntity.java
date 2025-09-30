package com.duydev.backend.domain.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "tbl_role")
public class RoleEntity extends AbstractEntity<Integer> {
    @Column(name = "role_name")
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private Set<RoleHasPermissionEntity> roleHasPermissions;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private Set<UserHasRoleEntity> userHasRoles;
}
 