package repository;

import entity.Assistance;

import java.util.Optional;

public interface AssistanceRepository {
    Optional<Assistance> findAssistance(String assistanceName);
}
