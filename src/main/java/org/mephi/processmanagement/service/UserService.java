package org.mephi.processmanagement.service;

import org.mephi.processmanagement.model.User;

/**
 * Service class for {@link org.mephi.processmanagement.model.User}
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
