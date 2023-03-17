package model;

import java.util.Date;

public interface ConcoursRepository {

    EntityId nextId();

    Concours getByCandidatId(EntityId id);

    Concours save(Candidat candidat, String nom, String sujetConcours, Date dateDebutConcours, Date dateRenduLimiteConcours);

    void updateConcours(Concours concours);

}
