package id.bts.todolist.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistDTO {
    private Long id;
    private String title;
    private List<ChecklistItemDTO> items;
}
