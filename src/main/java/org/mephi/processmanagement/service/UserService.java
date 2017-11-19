package org.mephi.processmanagement.service;

import org.mephi.processmanagement.model.Role;
import org.mephi.processmanagement.model.User;

import java.util.List;
import java.util.Set;

/**
 * Service class for {@link org.mephi.processmanagement.model.User}
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    List<Role> getAllUserRoles(String userId);

    void resetPassword(User user, String password);

}
