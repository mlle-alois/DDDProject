package use_case.candidats;

import model.*;
import model.enums.StatusCandidatEnum;
import use_case.candidats.dto.CandidatDto;

public class EvaluerCandidat {

    private final int notePourValider;
    private final CandidatRepository candidatRepository;
    private final ConcoursRepository concoursRepository;

    public EvaluerCandidat(CandidatRepository candidatRepository, ConcoursRepository concoursRepository) {
        this.candidatRepository = candidatRepository;
        this.concoursRepository = concoursRepository;
        this.notePourValider = 10;
    }


    public CandidatDto evaluer(int candidatId, int note) {
        try {
            Candidat candidat = this.candidatRepository.findById(EntityId.of(candidatId));
            Concours concours = this.concoursRepository.getByCandidatId(candidat.getId());

            StatusCandidatEnum resultat = concours.evaluer(note);
            candidat.setStatusCandidate(resultat);

            this.candidatRepository.updateCandidat(candidat);
            this.concoursRepository.updateConcours(concours);
            return new CandidatDto(
                    candidat.getId(),
                    candidat.getCv(),
                    candidat.getLettreMotivation(),
                    candidat.getNom(),
                    candidat.getPrenom(),
                    candidat.getStatusCandidate()
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
