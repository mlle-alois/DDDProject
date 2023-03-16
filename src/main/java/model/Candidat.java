package model;

import model.enums.StatusCandidatEnum;
import use_case.candidats.EvaluerCandidat;

public class Candidat {

    private EntityId id;
    private final String cv;
    private final String lettreMotivation;
    private final String nom;
    private final String prenom;
    private final int penalite;
    private Enum<StatusCandidatEnum> statusCandidat;

    public Candidat(String cv, String lettreMotivation, String nom, String prenom) {
        this.cv = cv;
        this.lettreMotivation = lettreMotivation;
        this.nom = nom;
        this.prenom = prenom;
        this.statusCandidat = StatusCandidatEnum.EN_ATTENTE;
        this.penalite = -2;
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
/*
    public void evaluer(int note, Concours concours) {
        NoteConcours noteConcours = new NoteConcours(note);

        if (concours.getDateRenduConcours() > concours.getDateRenduLimitConcours()) {
            noteConcours = new NoteConcours( noteConcours.getNote() + this.penalite);
        }

        StatusCandidatEnum candidatStatut = concours.evalueCandidatStatus(noteConcours);
        setStatusCandidate(candidatStatut);

    }*/
}
