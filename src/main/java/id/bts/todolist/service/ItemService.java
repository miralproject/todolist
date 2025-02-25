package id.bts.todolist.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import id.bts.todolist.entity.ChecklistItem;
import id.bts.todolist.repository.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository repo;

    public ItemService(ItemRepository itemRepository) {
        this.repo = itemRepository;
    }

    public Optional<ChecklistItem> getItemById(Long id) {
        return repo.findById(id);
    }

    public ChecklistItem createItem(ChecklistItem item) {
        return repo.save(item);
    }

    public ChecklistItem updateItem(Long id, ChecklistItem newItem) {
        return repo.findById(id)
                .map(existingItem -> {
                    existingItem.setName(newItem.getName());
                    existingItem.setCompleted(newItem.isCompleted());
                    return repo.save(existingItem);
                })
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public void deleteItem(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Item not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
