package repository;

import entity.Assistance;
import entity.SubAssistance;

import java.util.Optional;

public interface SubAssistanceRepository {
    Optional<SubAssistance> findSubAssistance(String title, Assistance assistance);
}
