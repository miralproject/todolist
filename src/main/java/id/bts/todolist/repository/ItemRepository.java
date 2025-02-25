package id.bts.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.bts.todolist.entity.ChecklistItem;


public interface ItemRepository extends JpaRepository<ChecklistItem, Long> {
    
}
