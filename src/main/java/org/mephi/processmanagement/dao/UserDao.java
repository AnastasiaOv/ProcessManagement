package org.mephi.processmanagement.dao;

import org.mephi.processmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
