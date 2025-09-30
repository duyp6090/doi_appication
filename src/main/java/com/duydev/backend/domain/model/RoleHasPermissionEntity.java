package com.duydev.backend.domain.model;

import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name = "tbl_role_has_permission")
public class RoleHasPermissionEntity extends AbstractEntity<Integer>{
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonBackReference
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    @JsonBackReference
    private PermissionEntity permission;
}
