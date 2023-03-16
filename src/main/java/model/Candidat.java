package model;

import model.enums.StatusCandidatEnum;

public class Candidat {

    private EntityId id;
    private final String cv;
    private final String lettreMotivation;
    private final String nom;
    private final String prenom;

    private Enum<StatusCandidatEnum> statusCandidat;

    public Candidat(String cv, String lettreMotivation, String nom, String prenom) {
        this.cv = cv;
        this.lettreMotivation = lettreMotivation;
        this.nom = nom;
        this.prenom = prenom;
        this.statusCandidat = StatusCandidatEnum.EN_ATTENTE;
    }


    public EntityId getId() {
        return id;
    }

    public String getCv() {
        return cv;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Enum<StatusCandidatEnum> getStatusCandidate() {
        return statusCandidat;
    }

    public void setStatusCandidate(Enum<StatusCandidatEnum> statusCandidat) {
        this.statusCandidat = statusCandidat;
    }
}
