package use_case.candidats;

import infrastructure.CandidatsInMemory;
import infrastructure.ConcoursInMemory;
import model.Candidat;
import model.CandidatRepository;
import model.Concours;
import model.ConcoursRepository;
import model.enums.StatusCandidatEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.candidats.dto.CandidatDto;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluerCandidatTest {

    private CandidatRepository candidatRepository;
    private ConcoursRepository concoursRepository;
    private final List<Candidat> candidatList = new ArrayList<>();
    private final List<Concours> concoursList = new ArrayList<>();
    @BeforeEach
    public void initialiser() {
        candidatRepository = new CandidatsInMemory();
        concoursRepository = new ConcoursInMemory();

        candidatList.add(candidatRepository.save("nom1", "prenom1", "cv1", "lm1"));
        candidatList.add(candidatRepository.save("nom2", "prenom2", "cv2", "lm2"));
        candidatList.add(candidatRepository.save("nom3", "prenom3", "cv3", "lm3"));
        candidatList.add(candidatRepository.save("nom4", "prenom4", "cv4", "lm4"));
        candidatList.add(candidatRepository.save("nom5", "prenom5", "cv5", "lm5"));
        candidatList.add(candidatRepository.save("nom6", "prenom6", "cv6", "lm6"));


        concoursList.add(concoursRepository.save(candidatList.get(0), "nom1", "pas de problemes",
                Date.from(LocalDateTime.of(2020, 1, 1, 0, 0).toInstant(java.time.ZoneOffset.UTC)),
                Date.from(LocalDateTime.of(2020, 1, 2, 0, 0).toInstant(java.time.ZoneOffset.UTC))));
        concoursList.get(0).setDateRenduConcours(Date.from(LocalDateTime.of(2020, 1, 1, 0, 10).toInstant(java.time.ZoneOffset.UTC)));

        concoursList.add(concoursRepository.save(candidatList.get(1), "nom2", "date de rendu trop tard",
                Date.from(LocalDateTime.of(2020, 1, 1, 0, 0).toInstant(java.time.ZoneOffset.UTC)),
                Date.from(LocalDateTime.of(2020, 1, 2, 0, 0).toInstant(java.time.ZoneOffset.UTC))));
        concoursList.get(1).setDateRenduConcours(Date.from(LocalDateTime.of(2020, 1, 3, 0, 10).toInstant(java.time.ZoneOffset.UTC)));

        concoursList.add(concoursRepository.save(candidatList.get(3), "nom3", "candidat n'existe plus",
                Date.from(LocalDateTime.of(2020, 1, 1, 0, 0).toInstant(java.time.ZoneOffset.UTC)),
                Date.from(LocalDateTime.of(2020, 1, 2, 0, 0).toInstant(java.time.ZoneOffset.UTC))));
        candidatRepository.deleteCandidat(candidatList.get(3));

        concoursList.add(concoursRepository.save(candidatList.get(4), "nom4", "date de debut pas commencer",
                Date.from(LocalDateTime.now().plusDays(2).toInstant(java.time.ZoneOffset.UTC)),
                Date.from(LocalDateTime.now().plusDays(3).toInstant(java.time.ZoneOffset.UTC))));

        concoursList.add(concoursRepository.save(candidatList.get(5), "nom5", "date de rendu pas encore commencer",
                Date.from(LocalDateTime.now().minusDays(2).toInstant(java.time.ZoneOffset.UTC)),
                Date.from(LocalDateTime.now().plusDays(3).toInstant(java.time.ZoneOffset.UTC))));
        concoursList.get(4).setDateRenduConcours(Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC)));

        concoursList.add(concoursRepository.save(candidatList.get(5), "nom5", "peut noter le candidat sans rendu si date de rendu finit",
                Date.from(LocalDateTime.now().minusDays(2).toInstant(java.time.ZoneOffset.UTC)),
                Date.from(LocalDateTime.now().plusDays(1).toInstant(java.time.ZoneOffset.UTC))));

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
