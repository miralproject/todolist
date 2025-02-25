package id.bts.todolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.bts.todolist.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
