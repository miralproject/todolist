package id.bts.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import id.bts.todolist.entity.Checklist;
import id.bts.todolist.repository.ChecklistRepository;

@Service
public class ChecklistService {
    private final ChecklistRepository repo;
    
    public ChecklistService(ChecklistRepository checklistRepository) {
        this.repo = checklistRepository;
    }

    public List<Checklist> getAllChecklists() {
        return repo.findAll();
    }

    public Optional<Checklist> getChecklistById(Long id) {
        return repo.findById(id);
    }

    public Checklist createChecklist(Checklist checklist) {
        return repo.save(checklist);
    }

    public void deleteChecklist(Long id) {
        repo.deleteById(id);
    }
}
