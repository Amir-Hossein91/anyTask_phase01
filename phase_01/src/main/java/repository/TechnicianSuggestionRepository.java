package repository;

import entity.Order;
import entity.TechnicianSuggestion;

import java.util.List;
import java.util.Optional;

public interface TechnicianSuggestionRepository {
    Optional<List<TechnicianSuggestion>> findTechnitionSugestions(Order order);
}
