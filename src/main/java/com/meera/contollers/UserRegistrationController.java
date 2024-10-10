package com.meera.contollers;

import com.meera.entity.UserRegistration;
import com.meera.models.Response.ViewUserDetails;
import com.meera.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/{email}")
    public ResponseEntity<ViewUserDetails> getUserDetails(@PathVariable String email) {
        return userRegistrationService.getUserDetailsByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }


    @PostMapping("/register")
    public ResponseEntity<ViewUserDetails> createUser(@RequestBody UserRegistration userRegistration) {
        ViewUserDetails registrationResponse = userRegistrationService.createNewUser(userRegistration);
        return ResponseEntity.ok(registrationResponse);

    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UserRegistration updateUserRegistration) {
        updateUserRegistration.setEmail(email);
        ViewUserDetails updateUserResponse = userRegistrationService.updateUser(updateUserRegistration);
        return ResponseEntity.ok(updateUserRegistration);
    }

    @DeleteMapping("/{email}")
    public String deleteUser(@PathVariable String email) {

        if (userRegistrationService.deleteUser(email)) {
            return email + "is deleted";
        } else {


            return "Error deleting user: " + email;
        }
    }
}
