package model;

import java.util.Date;
import java.util.List;

public interface ConcoursRepository {

    EntityId nextId();
    Concours save(Candidat candidat, String nom, String sujetConcours, Date dateDebutConcours, Date dateRenduLimitConcours);

    List<Concours> findAll();

    Concours findById(EntityId id);

    void deleteById(EntityId id);

    Concours getByCandidatId(EntityId id);

    void updateConcours(Concours concours);

}
