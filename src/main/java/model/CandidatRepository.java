package model;

import java.util.List;

public interface CandidatRepository {

    CandidatId nextId();
    Candidat save(String cv, String lettreMotivation, String nom, String prenom);

    List<Candidat> findAll();

    Candidat findById(CandidatId id);

    void deleteById(CandidatId id);


}
