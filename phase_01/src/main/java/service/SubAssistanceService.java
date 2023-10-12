package service;

import entity.Assistance;
import entity.SubAssistance;

public interface SubAssistanceService {
    SubAssistance findSubAssistance(String title, Assistance assistance);
}
