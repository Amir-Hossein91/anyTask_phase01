package service;

import entity.Assistance;
import entity.SubAssistance;

public interface SubAsssistanceService {
    SubAssistance findSubAssistance(String title, Assistance assistance);
}
