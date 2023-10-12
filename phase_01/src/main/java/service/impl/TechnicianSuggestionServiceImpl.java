package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Order;
import entity.TechnicianSuggestion;
import entity.dto.TechnicianSuggestionDTO;
import exceptions.NotFoundException;
import repository.impl.TechnicianSuggestionRepositoryImpl;
import service.TechnicianSuggestionService;
import utility.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TechnicianSuggestionServiceImpl extends
        BaseServiceImpl<TechnicianSuggestionRepositoryImpl, TechnicianSuggestion> implements
        TechnicianSuggestionService {

    public TechnicianSuggestionServiceImpl(TechnicianSuggestionRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public List<TechnicianSuggestionDTO> getSuggestionsOf(Order order) {
        try{
            List<TechnicianSuggestion> suggestions = repository.findTechnitionSugestions(order).orElseThrow(
                    () -> new NotFoundException(Constants.NO_TECHNICIAN_SUGGESTION_FOUND)
            );
            List<TechnicianSuggestionDTO> result = new ArrayList<>();
            for(TechnicianSuggestion t : suggestions){
                TechnicianSuggestionDTO suggestionDTO = TechnicianSuggestionDTO.builder()
                        .suggestionId(t.getId())
                        .suggestionRegistrationDate(LocalDateTime.now())
                        .technicianFirstname(t.getTechnician().getFirstName())
                        .technicianLastname(t.getTechnician().getLastName())
                        .technicianId(t.getTechnician().getId())
                        .technicianScore(t.getTechnician().getScore())
                        .numberOfFinishedTasks(t.getTechnician().getNumberOfFinishedTasks())
                        .suggestedPrice(t.getTechSuggestedPrice())
                        .suggestedDate(t.getTechSuggestedDate())
                        .taskEstimatedDuration(t.getTaskEstimatedDuration()).build();
                result.add(suggestionDTO);
            }
            return result;
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
            return null;
        }


    }
}
