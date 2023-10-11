package repository;

import entity.Technician;
import exceptions.NotSavedException;

import java.util.List;
import java.util.Optional;

public interface TechnicianRepository {
    Optional<List<Technician>> saveOrUpdate(List<Technician> technicians) throws NotSavedException;
    Optional<List<Technician>> findUnapproved();
}
