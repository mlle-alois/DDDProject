package model;

import java.util.List;

public interface CandidatRepository {

    EntityId nextId();
    Candidat save(String cv, String lettreMotivation, String nom, String prenom);

    List<Candidat> findAll();

    Candidat findById(EntityId id);

    void deleteById(EntityId id);

    void updateCandidat(Candidat candidat);
}
