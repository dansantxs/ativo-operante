package br.unoeste.fipp.ativooperante2024.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name="feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fee_id")
    private Long id;

    @Column(name="fee_texto")
    private String texto;

    @ManyToOne
    @JoinColumn(name="den_id", nullable = false)
    private Denuncia denuncia;

    public Feedback() {
        this(0L, "", null);
    }

    public Feedback(Long id, String texto, Denuncia denuncia) {
        this.id = id;
        this.texto = texto;
        this.denuncia = denuncia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Denuncia getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Denuncia denuncia) {
        this.denuncia = denuncia;
    }
}
