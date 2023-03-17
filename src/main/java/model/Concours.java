package model;

import model.enums.StatusCandidatEnum;

import java.util.Date;

public class Concours {

    private EntityId id;

    private Candidat candidat;

    private String nom;

    private String sujetConcours;

    private Date dateRenduConcours;
    private Date dateDebutConcours;

    private Date dateRenduLimitConcours;

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

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSujetConcours() {
        return sujetConcours;
    }

    public void setSujetConcours(String sujetConcours) {
        this.sujetConcours = sujetConcours;
    }

    public Date getDateDebutConcours() {
        return dateDebutConcours;
    }

    public void setDateDebutConcours(Date dateDebutConcours) {
        this.dateDebutConcours = dateDebutConcours;
    }

    public long getDateRenduLimitConcours() {
        return dateRenduLimitConcours.getTime();
    }

    public void setDateRenduLimitConcours(Date dateRenduLimitConcours) {
        this.dateRenduLimitConcours = dateRenduLimitConcours;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {

        this.note = Math.max(note, 0);
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

    public StatusCandidatEnum evaluer(int note){
        NoteConcours noteConcours = new NoteConcours(note);

        if(new Date().getTime() < getDateRenduLimitConcours()){
            return StatusCandidatEnum.EN_ATTENTE;
        }
        if(getDateRenduConcours() == null){
            return StatusCandidatEnum.EN_ATTENTE;
        }
        if (getDateRenduConcours() > getDateRenduLimitConcours()) {
            noteConcours = new NoteConcours( noteConcours.getNote() + this.penalite);
            return evalueCandidatStatus(noteConcours, true);
        }

        return evalueCandidatStatus(noteConcours, false);
    }
}
