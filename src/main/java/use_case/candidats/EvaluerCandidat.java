package use_case.candidats;

import CustomException.NoteNegatifException;
import model.*;
import model.enums.StatusCandidatEnum;
import use_case.candidats.dto.CandidatDto;

public class EvaluerCandidat {

    private final int penalite;
    private final int notePourValider;
    private final CandidatRepository candidatRepository;
    private final ConcoursRepository concoursRepository;

    public EvaluerCandidat(CandidatRepository candidatRepository, ConcoursRepository concoursRepository) {
        this.candidatRepository = candidatRepository;
        this.concoursRepository = concoursRepository;
        this.penalite= -2;
        this.notePourValider = 10;
    }


    public CandidatDto eveluer(int candidatId, int note) {
        try {
            Candidat candidat = this.candidatRepository.findById(EntityId.of(candidatId));
            Concours concours = this.concoursRepository.getByCandidatId(candidat.getId());

            if (note < 0) {
                throw new NoteNegatifException("La note ne peut être négatif");
            }

            if (concours.getDateRenduConcours().getTime() > concours.getDateRenduLimitConcours().getTime()) {
                note += penalite;
            }

            if (concours.getNote() >= this.notePourValider) {
                candidat.setStatusCandidate(StatusCandidatEnum.ACCEPTER);
            } else {
                candidat.setStatusCandidate(StatusCandidatEnum.REFUSER);
            }
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
