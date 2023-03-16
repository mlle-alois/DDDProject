package use_case.candidats;

import model.Candidat;
import model.CandidatRepository;

public class CreerCandidat {

    private CandidatRepository candidatRepository;
    public void creerCandidature(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    public Candidat creer(String cv, String lettreMotivation,String nom, String prenom) {
        return candidatRepository.save(cv,lettreMotivation,nom,prenom);
    }



}
