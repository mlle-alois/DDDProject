package infrastructure;

import model.Candidat;
import model.CandidatRepository;
import model.EntityId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidatsInMemory implements CandidatRepository {
    private final AtomicInteger count = new AtomicInteger(0);
    HashMap<EntityId, Candidat> candidats = new HashMap<>();

    @Override
    public EntityId nextId() {
        return EntityId.of(count.incrementAndGet());
    }

    @Override
    public Candidat save(String cv, String lettreMotivation, String nom, String prenom) {

        Candidat candidat = new Candidat(nextId(), cv, lettreMotivation, nom, prenom);
        candidats.put(candidat.getId(), candidat);
        return candidat;
    }

    @Override
    public Candidat findById(EntityId id) {
        final Candidat candidat = candidats.get(id);
        if (candidat == null) {
            throw new RuntimeException("No Account for " + id.getValue());
        }
        return candidat;
    }

    @Override
    public void updateCandidat(Candidat candidat) {
        candidats.put(candidat.getId(), candidat);
    }

    @Override
    public void deleteCandidat(Candidat candidat) {
        candidats.remove(candidat.getId());
    }

}
