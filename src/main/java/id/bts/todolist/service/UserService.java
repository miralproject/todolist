package id.bts.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.bts.todolist.entity.User;
import id.bts.todolist.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public String save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return "User save successfully";
    }

    public Iterable<User> findAll() {
        return repo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repo.findByName(username);
        return user.map(UserInfoDetail::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
