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



    public static boolean isValidCpf(Long cpf) {
        String cpfString = cpf.toString();
        if (cpfString.length() != 11) {
            return false;
        }
        // Implementação simplificada da validação de CPF
        int[] multiplicadores1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadores2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpfString.charAt(i) - '0') * multiplicadores1[i];
        }

        int resto = soma % 11;
        char digito1 = (resto < 2) ? '0' : (char) ((11 - resto) + '0');

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpfString.charAt(i) - '0') * multiplicadores2[i];
        }

        resto = soma % 11;
        char digito2 = (resto < 2) ? '0' : (char) ((11 - resto) + '0');

        return cpfString.charAt(9) == digito1 && cpfString.charAt(10) == digito2;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }



    public static boolean isValidSenha(Long senha) {
        String senhaString = senha.toString();
        return !senhaString.isEmpty() && senhaString.length() <= 6;
    }
}

