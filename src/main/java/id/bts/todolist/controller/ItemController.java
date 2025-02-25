package id.bts.todolist.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.bts.todolist.entity.ChecklistItem;
import id.bts.todolist.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService itemService) {
        this.service = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChecklistItem> getItemById(@PathVariable Long id) {
        Optional<ChecklistItem> item = service.getItemById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ChecklistItem createItem(@RequestBody ChecklistItem item) {
        return service.createItem(item);
    }

    @PutMapping("/{id}")
    public ChecklistItem updateItem(@RequestBody ChecklistItem item) {
        return service.updateItem(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
