package service;

import entity.Assistance;

import java.util.Optional;

public interface AssistanceService {
    Assistance findAssistance(String assistanceName);
}
