package use_case.candidats.dto;

import model.EntityId;
import model.enums.StatusCandidatEnum;

public class CandidatDto {
    private EntityId id;
    private final String cv;
    private final String lettreMotivation;
    private final String nom;
    private final String prenom;

    private final StatusCandidatEnum statusCandidat;

    public CandidatDto(EntityId id, String cv, String lettreMotivation, String nom, String prenom, StatusCandidatEnum statusCandidat) {
        this.id = id;
        this.cv = cv;
        this.lettreMotivation = lettreMotivation;
        this.nom = nom;
        this.prenom = prenom;
        this.statusCandidat = statusCandidat;
    }

    public StatusCandidatEnum getStatusCandidat() {
        return statusCandidat;
    }
}
