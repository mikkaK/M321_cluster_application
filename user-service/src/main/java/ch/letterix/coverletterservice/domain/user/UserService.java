package ch.letterix.coverletterservice.domain.user;

import ch.letterix.coverletterservice.core.generic.AbstractService;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

public interface UserService extends AbstractService<User> {

    User getUserByEmail(String email) throws InstanceNotFoundException;
}
