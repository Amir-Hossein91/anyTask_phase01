package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.TechnicianSuggestion;
import repository.impl.TechnicianSuggestionRepositoryImpl;

public class TechnicianSuggestionServiceImpl extends
        BaseServiceImpl<TechnicianSuggestionRepositoryImpl, TechnicianSuggestion> {

    public TechnicianSuggestionServiceImpl(TechnicianSuggestionRepositoryImpl repository) {
        super(repository);
    }

}
