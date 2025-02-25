package id.bts.todolist.service;

import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
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

    public ChecklistItem updateItem(ChecklistItem item) {
        return repo.save(item);
    }

    public void deleteItem(Long id) {
        repo.deleteById(id);
    } 
}
