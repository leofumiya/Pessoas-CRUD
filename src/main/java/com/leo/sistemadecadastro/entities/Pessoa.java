package com.leo.sistemadecadastro.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "pessoas")
@Entity(name = "Pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento, String telefone, String email, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public void atualizarInformacoes(Pessoa dados) {
        if (dados.nome != null) {
            this.nome = dados.nome;
        }
        if (dados.telefone != null) {
            this.cpf = dados.cpf;
        }
        if (dados.dataNascimento != null) {
            this.dataNascimento = dados.dataNascimento;
        }
        if (dados.telefone != null) {
            this.telefone = dados.telefone;
        }
        if (dados.email != null) {
            this.email = dados.email;
        }
        if (dados.endereco != null) {
            this.endereco = dados.endereco;
        }
    }
}
