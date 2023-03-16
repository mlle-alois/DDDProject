package use_case.candidats;

import infrastructure.CandidatsInMemory;
import infrastructure.ConcoursInMemory;
import model.Candidat;
import model.CandidatRepository;
import model.ConcoursRepository;
import model.enums.StatusCandidatEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.candidats.dto.CandidatDto;

import java.time.LocalDateTime;
import java.util.Date;

public class EvaluerCandidatTest {

    private CandidatRepository candidatRepository;
    private ConcoursRepository concoursRepository;

    @BeforeEach
    public void initialiser() {
        candidatRepository = new CandidatsInMemory();
        concoursRepository = new ConcoursInMemory();
        Candidat candidat = candidatRepository.save("cv", "lm", "nom", "prenom");
        concoursRepository.save(candidat, "nom", "sujet",
                Date.from(LocalDateTime.of(2020, 1, 1, 0, 0).toInstant(java.time.ZoneOffset.UTC)),
                Date.from(LocalDateTime.of(2020, 1, 2, 0, 0).toInstant(java.time.ZoneOffset.UTC)));
    }

    private CandidatDto evaluerCandidat(int candidatId, int note) {
        EvaluerCandidat evaluer = new EvaluerCandidat(candidatRepository, concoursRepository);
        return evaluer.evaluer(candidatId, note);
    }

    @Test
    void evaluer_candidat() {
        CandidatDto candidat = evaluerCandidat(1, 10);
        assert candidat.getStatusCandidat() == StatusCandidatEnum.ACCEPTER;
    }

    // TODO
    // id du candidat ne correspond à rien
    // le candidat n'a pas de concours
    // la note est inférieure à 0
    // la note est supérieure à 20
    //
    // date de rendu = on peut l'évaluer
    // date de rendu > date de fin = penalité
    // date de rendu < date de fin = normal
    // candidat existe
    // concours existe
    // quand il y a un rendu il faut une date de rendu

    @Test
    void candidat_id_nexiste_pas() {

    }
}
