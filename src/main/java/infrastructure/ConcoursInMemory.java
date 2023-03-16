package infrastructure;

import model.Candidat;
import model.Concours;
import model.ConcoursRepository;
import model.EntityId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ConcoursInMemory implements ConcoursRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    HashMap<EntityId, Concours> concours = new HashMap<>();

    @Override
    public EntityId nextId() {
        return EntityId.of(count.incrementAndGet());
    }

    @Override
    public Concours save(Candidat candidat, String nom, String sujetConcours, Date dateDebutConcours, Date dateRenduLimitConcours) {
        Concours concours = new Concours(candidat, nom, sujetConcours, dateDebutConcours, dateRenduLimitConcours);
        this.concours.put(concours.getId(), concours);
        return concours;
    }

    @Override
    public List<Concours> findAll() {
        return new ArrayList<>(concours.values());
    }

    @Override
    public Concours findById(EntityId id) {
        final Concours concours = this.concours.get(id);
        if (concours == null) {
            throw new RuntimeException("No Account for " + id.getValue());
        }
        return concours;
    }

    @Override
    public void deleteById(EntityId id) {
        concours.remove(id);
    }

    @Override
    public Concours getByCandidatId(EntityId entityId) {
        return concours.values().stream().filter(c -> c.getCandidat().getId().equals(entityId)).findFirst().orElse(null);
    }

    @Override
    public void updateConcours(Concours concours) {
        this.concours.put(concours.getId(), concours);
    }
}
