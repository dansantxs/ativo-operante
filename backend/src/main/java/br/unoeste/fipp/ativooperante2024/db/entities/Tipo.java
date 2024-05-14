package br.unoeste.fipp.ativooperante2024.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tipo")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tip_id")
    private Long Id;
    @Column(name="tip_nome")
    private String nome;

    public Tipo() {
        this(0L,"");
    }

    public Tipo(Long id, String nome) {
        Id = id;
        this.nome = nome;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
