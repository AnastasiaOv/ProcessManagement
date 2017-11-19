package org.mephi.processmanagement.dao;

import org.mephi.processmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
