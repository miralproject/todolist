package id.bts.todolist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.bts.todolist.entity.Checklist;
import id.bts.todolist.service.ChecklistService;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistController {
    private final ChecklistService service;

    public ChecklistController(ChecklistService checklistService) {
        this.service = checklistService;
    }

    @GetMapping
    public List<Checklist> getAllChecklists() {
        return service.getAllChecklists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checklist> getChecklistById(@PathVariable Long id) {
        Optional<Checklist> checklist = service.getChecklistById(id);
        return checklist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Checklist createChecklist(@RequestBody Checklist checklist) {
        return service.createChecklist(checklist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChecklist(@PathVariable Long id) {
        service.deleteChecklist(id);
        return ResponseEntity.noContent().build();
    }
}
