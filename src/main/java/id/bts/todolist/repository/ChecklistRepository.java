package id.bts.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.bts.todolist.entity.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
    
}
