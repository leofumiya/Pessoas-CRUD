package com.leo.sistemadecadastro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leo.sistemadecadastro.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "endereco")
@Entity(name = "Endereco")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String bairro;
    private String numero;
    private String cep;
    private String cidade;
    private String uf;

    @JsonIgnore
    @OneToMany(mappedBy = "endereco")
    private List<Pessoa> pessoas = new ArrayList<>();

    public Endereco(Long id, String logradouro, String bairro, String numero, String cep, String cidade, String uf) {
        this.id = id;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(String logradouro, String bairro, String numero, String cep, String cidade, String uf) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void atualizarInformacoes(Endereco dados) {
        if (dados.logradouro != null) {
            this.logradouro = dados.logradouro;
        }
        if (dados.bairro != null) {
            this.bairro = dados.bairro;
        }
        if (dados.numero != null) {
            this.numero = dados.numero;
        }
        if (dados.cep != null) {
            this.cep = dados.cep;
        }
        if (dados.cidade != null) {
            this.cidade = dados.cidade;
        }
        if (dados.uf != null) {
            this.uf = dados.uf;
        }
    }
}
