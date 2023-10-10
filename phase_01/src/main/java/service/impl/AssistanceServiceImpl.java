package service.impl;

import basics.baseService.impl.BaseServiceImpl;
import entity.Assistance;
import repository.impl.AssistanceRepositoryImpl;
import service.AssistanceService;

import javax.validation.constraints.Null;
import java.util.Optional;

public class AssistanceServiceImpl extends BaseServiceImpl<AssistanceRepositoryImpl, Assistance> implements AssistanceService {

    public AssistanceServiceImpl(AssistanceRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public Assistance findAssistance(String assistanceName) {
        return repository.findAssistance(assistanceName).orElse(null);
    }
}
