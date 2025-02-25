package id.bts.todolist.service;

import id.bts.todolist.dto.ChecklistDTO;
import id.bts.todolist.dto.ChecklistItemDTO;
import id.bts.todolist.entity.Checklist;
import id.bts.todolist.repository.ChecklistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChecklistService {
    
    private final ChecklistRepository checklistRepository;
    private final ModelMapper modelMapper;

    public ChecklistService(ChecklistRepository checklistRepository, ModelMapper modelMapper) {
        this.checklistRepository = checklistRepository;
        this.modelMapper = modelMapper;
    }

    public List<ChecklistDTO> getAllChecklists() {
        return checklistRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ChecklistDTO> getChecklistById(Long id) {
        return checklistRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ChecklistDTO createChecklist(ChecklistDTO checklistDTO) {
        Checklist checklist = modelMapper.map(checklistDTO, Checklist.class);
        
        // Pastikan setiap ChecklistItem memiliki reference ke Checklist
        if (checklist.getItems() != null) {
            checklist.getItems().forEach(item -> item.setChecklist(checklist));
        }

        Checklist savedChecklist = checklistRepository.save(checklist);
        return convertToDTO(savedChecklist);
    }

    public void deleteChecklist(Long id) {
        checklistRepository.deleteById(id);
    }

    // Konversi Checklist ke DTO (menambahkan checklistId di setiap item)
    private ChecklistDTO convertToDTO(Checklist checklist) {
        ChecklistDTO checklistDTO = modelMapper.map(checklist, ChecklistDTO.class);
        if (checklist.getItems() != null) {
            List<ChecklistItemDTO> itemsDTO = checklist.getItems().stream()
                .map(item -> {
                    ChecklistItemDTO itemDTO = modelMapper.map(item, ChecklistItemDTO.class);
                    itemDTO.setChecklistId(checklist.getId()); // Tambahkan checklistId
                    return itemDTO;
                })
                .collect(Collectors.toList());
            checklistDTO.setItems(itemsDTO);
        }
        return checklistDTO;
    }
}
