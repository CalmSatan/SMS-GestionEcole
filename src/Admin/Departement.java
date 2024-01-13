package Admin;
import java.util.*;

public class Departement {
    private String name;
    private Enseignant manager;
    private List<Filiere> filieres;

    public Departement(String name, Enseignant manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Manager cannot be null");
        }
        this.name = name;
        this.manager = manager;
        this.filieres = new ArrayList<>();
    }

    public void addFiliere (Filiere filiere) {
        if (filiere == null) {
            throw new IllegalArgumentException("Filiere cannot be null");
        }
        this.filieres.add(filiere);
    }

    // getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enseignant getManager() {
        return manager;
    }

    public void setManager(Enseignant manager) {
        this.manager = manager;
    }

    public List<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(List<Filiere> filieres) {
        this.filieres = filieres;
    }
}
