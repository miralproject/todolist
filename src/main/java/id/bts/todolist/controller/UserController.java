package id.bts.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.bts.todolist.dto.AuthRequest;
import id.bts.todolist.entity.User;
import id.bts.todolist.service.JwtService;
import id.bts.todolist.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    public String register(@RequestBody User user) {
        System.out.println("MASUK KONTROLLER");
        return service.save(user);
    }

    @PostMapping("/auth/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getName(), dto.getPassword())
            );
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(dto.getName());
                return ResponseEntity.ok(token);
            } else {
                throw new RuntimeException("Unauthorized");
            }
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Authentication failed: " + e.getMessage());
        }
    }

    @GetMapping("/auth/validate-token")
    public String validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return service.findAll();
    }
}
