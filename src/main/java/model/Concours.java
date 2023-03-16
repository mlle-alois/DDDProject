package model;

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

    private int note;
    public Concours(Candidat candidat, String nom, String sujetConcours, Date dateDebutConcours, Date dateRenduLimitConcours) {
        this.candidat = candidat;
        this.nom = nom;
        this.sujetConcours = sujetConcours;
        this.dateDebutConcours = dateDebutConcours;
        this.dateRenduLimitConcours = dateRenduLimitConcours;
        this.note = -1;
        this.notePourValider = 10;
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
}
