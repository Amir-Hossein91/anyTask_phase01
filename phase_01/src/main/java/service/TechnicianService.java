package service;

import entity.Technician;

import java.util.List;

public interface TechnicianService {
    List<Technician> saveOrUpdate(List<Technician> technicians);
}
