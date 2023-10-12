package service;

import entity.Customer;
import entity.Order;
import entity.TechnicianSuggestion;
import entity.dto.TechnicianSuggestionDTO;

import java.util.List;

public interface TechnicianSuggestionService {
    List<TechnicianSuggestionDTO> getSuggestionsOf(Order order);
}
