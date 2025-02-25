package id.bts.todolist.controller;

import id.bts.todolist.dto.ChecklistItemDTO;
import id.bts.todolist.entity.ChecklistItem;
import id.bts.todolist.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    public ItemController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChecklistItemDTO> getItemById(@PathVariable Long id) {
        Optional<ChecklistItem> item = itemService.getItemById(id);
        return item.map(value -> ResponseEntity.ok(modelMapper.map(value, ChecklistItemDTO.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ChecklistItemDTO> createItem(@RequestBody ChecklistItemDTO itemDTO) {
        ChecklistItem item = modelMapper.map(itemDTO, ChecklistItem.class);
        ChecklistItem savedItem = itemService.createItem(item);
        return ResponseEntity.ok(modelMapper.map(savedItem, ChecklistItemDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChecklistItemDTO> updateItem(@PathVariable Long id, @RequestBody ChecklistItemDTO itemDTO) {
        ChecklistItem item = modelMapper.map(itemDTO, ChecklistItem.class);
        ChecklistItem updatedItem = itemService.updateItem(id, item);
        return ResponseEntity.ok(modelMapper.map(updatedItem, ChecklistItemDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
