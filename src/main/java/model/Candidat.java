package model;

public class Candidat {

    private final CandidatId id;
    private final String cv;
    private final String lettreMotivation;
    private final String nom;
    private final String prenom;


    public Candidat(String cv, String lettreMotivation, String nom, String prenom) {
        this.cv = cv;
        this.lettreMotivation = lettreMotivation;
        this.nom = nom;
        this.prenom = prenom;
    }


    public CandidatId getId() {
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

}
