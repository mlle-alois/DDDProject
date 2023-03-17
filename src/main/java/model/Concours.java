package model;

import model.enums.StatusCandidatEnum;

import java.util.Date;

public class Concours {

    private EntityId id;

    private Candidat candidat;

    private final String nom;

    private final String sujetConcours;

    private Date dateRenduConcours;
    private final Date dateDebutConcours;

    private final Date dateRenduLimitConcours;

    private final int notePourValider;


    private final int penalite;

    private int note;


    public Concours(EntityId id, Candidat candidat, String nom, String sujetConcours, Date dateDebutConcours, Date dateRenduLimitConcours) {
        this.id = id;
        this.candidat = candidat;
        this.nom = nom;
        this.sujetConcours = sujetConcours;
        this.dateDebutConcours = dateDebutConcours;
        this.dateRenduLimitConcours = dateRenduLimitConcours;
        this.note = -1;
        this.notePourValider = 10;
        this.penalite = -2;
    }

    public EntityId getId() {
        return this.id;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public long getDateRenduLimitConcours() {
        return dateRenduLimitConcours.getTime();
    }

    public long getDateRenduConcours() {
        return dateRenduConcours.getTime();
    }

    public void setDateRenduConcours(Date dateRenduConcours) {
        this.dateRenduConcours = dateRenduConcours;
    }

    public int getNotePourValider() {
        return notePourValider;
    }

    public StatusCandidatEnum evalueCandidatStatus(NoteConcours noteConcours, boolean isPenalite) {
        if (noteConcours.getNote() >= getNotePourValider()) {
            if (isPenalite) {
                return StatusCandidatEnum.ACCEPTER_AVEC_PENALITE;
            }
            return StatusCandidatEnum.ACCEPTER;
        } else {
            return StatusCandidatEnum.REFUSER;
        }
    }

    private boolean evaluationNaPasCommence() {
        return new Date().getTime() < getDateRenduLimitConcours();
    }

    private boolean evaluationEstTermineeSansRendu() {
        return dateRenduConcours == null;
    }
    public StatusCandidatEnum evaluer(int note) {
        NoteConcours noteConcours = new NoteConcours(note);

        if (evaluationNaPasCommence()) {
            return StatusCandidatEnum.EN_ATTENTE;
        }

        if (evaluationEstTermineeSansRendu()) {
            return StatusCandidatEnum.REFUSER;
        }

        if (getDateRenduConcours() > getDateRenduLimitConcours()) {
            noteConcours = new NoteConcours(noteConcours.getNote() + this.penalite);
            return evalueCandidatStatus(noteConcours, true);
        }

        return evalueCandidatStatus(noteConcours, false);
    }
}
