package infrastructure;

import model.Candidat;
import model.EntityId;
import model.CandidatRepository;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CandidatsInMemory implements CandidatRepository {
    private final AtomicInteger count = new AtomicInteger(0);
    HashMap<EntityId, Candidat> candidats = new HashMap<>();

    @Override
    public EntityId nextId() {
        return EntityId.of(count.incrementAndGet());
    }

    @Override
    public Candidat save(String cv, String lettreMotivation, String nom, String prenom) {
        Candidat candidat = new Candidat(cv, lettreMotivation, nom, prenom, penalité);
        candidats.put(candidat.getId(), candidat);
        return candidat;
    }

    @Override
    public List<Candidat> findAll() {
        return candidats.values().stream().collect(Collectors.toList());
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
    public void deleteById(EntityId id) {
        candidats.remove(id);
    }

    @Override
    public void updateCandidat(Candidat candidat) {
        candidats.put(candidat.getId(), candidat);
    }

}
