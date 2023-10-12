package service;

import entity.Order;
import entity.dto.TechnicianSuggestionDTO;

import java.util.List;

public interface TechnicianSuggestionService {
    List<TechnicianSuggestionDTO> getSuggestionsOf(Order order);
}
