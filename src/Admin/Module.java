package Admin;


public class Module {
    private String intitule;
    private Filiere filiere;
    private Enseignant professeur;

    public Module(String intitule, Filiere filiere, Enseignant professeur) {
        if (filiere == null || professeur == null) {
            throw new IllegalArgumentException("Module must have a filiere and a professeur");
        }
        this.intitule = intitule;
        this.filiere = filiere;
        this.professeur = professeur;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        if (filiere == null) {
            throw new IllegalArgumentException("Module must have a filiere");
        }
        this.filiere = filiere;
    }

    public Enseignant getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Enseignant professeur) {
        if (professeur == null) {
            throw new IllegalArgumentException("Module must have a professeur");
        }
        this.professeur = professeur;
    }
}