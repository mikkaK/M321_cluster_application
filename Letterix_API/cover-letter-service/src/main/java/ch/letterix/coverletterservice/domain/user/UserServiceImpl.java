package ch.letterix.coverletterservice.domain.user;

import ch.letterix.coverletterservice.core.generic.AbstractRepository;
import ch.letterix.coverletterservice.core.generic.AbstractServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Log4j2
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

    @Value("${letterix.OPENAI_API_KEY}")
    private String OPENAI_API_KEY;
    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    private final UserRepository userRepository;
    @Autowired
    protected UserServiceImpl(AbstractRepository<User> repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) throws InstanceNotFoundException {
        List<User> userList = repository.findAll();
        return userList.stream().filter(user -> email.equals(user.getEmail())).findAny().orElseThrow(InstanceNotFoundException::new);
    }
}
