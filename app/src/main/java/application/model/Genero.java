package application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Generatedvalue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="generos")
public class Genero {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nome;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id
    }
}