package com.leo.sistemadecadastro.builder;

import com.leo.sistemadecadastro.dto.PessoaDTO;
import com.leo.sistemadecadastro.entities.Pessoa;

import java.time.LocalDate;

public class PessoaBuilder {

    public static Pessoa pessoa() {
        Pessoa p1 = new Pessoa("Leonardo", "12345678910", LocalDate.of(1999, 03, 30), "18996964751", "leo@gmail.com");
        return p1;
    }

    public static Pessoa pessoa1() {
        Pessoa p1 = new Pessoa("Bianca", "12345678911", LocalDate.of(2001, 10, 10), "18996419927", "bianca@gmail.com");
        return p1;
    }

    public static Pessoa pessoaAtualizada() {
        Pessoa p1 = new Pessoa("Leonardo Fumiya", "12345678910", LocalDate.of(1999, 03, 30), "18996964751", "leo@gmail.com");
        return p1;
    }

    public static PessoaDTO pessoaDTO() {
        PessoaDTO p1 = new PessoaDTO("Leonardo", "12345678910", LocalDate.of(1999, 03, 30), "18996964751", "leo@gmail.com");
        return p1;
    }

    public static PessoaDTO pessoaDTOAtualizada() {
        PessoaDTO p1 = new PessoaDTO("Leonardo Fumiya", "12345678910", LocalDate.of(1999, 03, 30), "18996964751", "leo@gmail.com");
        return p1;
    }

    public static PessoaDTO pessoaDTONomeEmBranco() {
        PessoaDTO p1 = new PessoaDTO("", "12345678910", LocalDate.of(1999, 03, 30), "18996964751", "leo@gmail.com");
        return p1;
    }

}
