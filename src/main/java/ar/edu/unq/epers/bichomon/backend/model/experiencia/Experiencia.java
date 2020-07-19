package ar.edu.unq.epers.bichomon.backend.model.experiencia;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
public class Experiencia {

    @Id
    @Column(nullable = false, length=190)
    private String nombre;

    private int valor;

    public Experiencia(){

    }

    public Experiencia(String unNombre){
        this.nombre = unNombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
