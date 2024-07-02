package org.nobleson.demonstration.resources;


import lombok.RequiredArgsConstructor;
import org.nobleson.demonstration.data.AuthRequest;
import org.nobleson.demonstration.data.AuthResponse;
import org.nobleson.demonstration.data.RegistrationRequest;
import org.nobleson.demonstration.logics.AuthService;
import org.nobleson.demonstration.logics.UserLogic;
import org.nobleson.demonstration.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/demonstration/user/auth")
@RequiredArgsConstructor
public class UserResource {

    private final UserLogic userLogic;
    private final AuthService authService;

    @GetMapping("all")
    public ResponseEntity<List<AppUser>> getAllUsers(){

        return new ResponseEntity<>(userLogic.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("find/{userID}")
    public ResponseEntity<AppUser> getUserByID(@PathVariable("userID") String userID){

        return new ResponseEntity<>(userLogic.getUserByID(userID), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser user){
        return new ResponseEntity<>(userLogic.addUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable("userID") String userID){
        userLogic.deleteUser(userID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest request){

        return new ResponseEntity<>(authService.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
        return new ResponseEntity<>(authService.authenticateUser(request),HttpStatus.OK);
    }

}
