package ch.letterix.coverletterservice.domain.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<User> newCoverLetter(@Valid @RequestBody User user) throws JsonProcessingException, InstanceAlreadyExistsException {
        User responseUser = userService.save(user);
        log.info("Created User");
        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmailadress(@PathVariable("email") String email) throws InstanceNotFoundException {
        String traceMessage = MessageFormat.format("Getting user with email:{0}", email);
        String infoMessage = MessageFormat.format("Found user with email:{0}", email);
        log.trace(traceMessage);
        User responseUser = userService.getUserByEmail(email);
        log.info(infoMessage);
        return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByEmailadress(@PathVariable("id") UUID id) throws InstanceNotFoundException {
        String traceMessage = MessageFormat.format("Getting user with id:{0}", id);
        String infoMessage = MessageFormat.format("Found user with id:{0}", id);
        log.trace(traceMessage);
        User responseUser = userService.findById(id);
        log.info(infoMessage);
        return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        String traceMessage = "Getting all users";
        String infoMessage = "Found all Users";
        log.trace(traceMessage);
        List<User> responseUsers = userService.findAll();
        log.info(infoMessage);
        return new ResponseEntity<>(responseUsers, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> editUsers(@PathVariable("id") UUID id, @Valid @RequestBody User user) {
        String traceMessage = MessageFormat.format("Getting user with id:{0}", id);
        String infoMessage = MessageFormat.format("Updated user with id:{0}", id);
        log.trace(traceMessage);
        User responseUser = userService.updateById(id, user);
        log.info(infoMessage);
        return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable("id") UUID id) {
        String traceMessage = MessageFormat.format("Getting user with id:{0}", id);
        String infoMessage = MessageFormat.format("Deleted user with id:{0}", id);
        log.trace(traceMessage);
        userService.deleteById(id);
        log.info(infoMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
