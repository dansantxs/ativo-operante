package br.unoeste.fipp.ativooperante2024.db.entities;

import jakarta.persistence.*;

import java.util.regex.Pattern;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usu_id")
    private Long id;

    @Column(name="usu_cpf", nullable = false)
    private Long cpf;

    @Column(name="usu_email", nullable = false, length = 40)
    private String email;

    @Column(name="usu_senha", nullable = false)
    private Long senha;

    @Column(name="usu_nivel", nullable = false)
    private int nivel;

    public Usuario() {
        this(0L, 0L, "", 0L, 0);
    }

    public Usuario(Long id, Long cpf, String email, Long senha, int nivel) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSenha() {
        return senha;
    }

    public void setSenha(Long senha) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}