package model;

public interface CandidatRepository {

    EntityId nextId();
    Candidat save(String cv, String lettreMotivation, String nom, String prenom);

    Candidat findById(EntityId id);

    void updateCandidat(Candidat candidat);

    void deleteCandidat(Candidat candidat);
}
