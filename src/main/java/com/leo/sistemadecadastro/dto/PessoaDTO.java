package com.leo.sistemadecadastro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leo.sistemadecadastro.entities.Endereco;
import com.leo.sistemadecadastro.entities.Pessoa;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PessoaDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{10,11}", message = "O CPF deve conter 10 ou 11 dígitos")
        String cpf,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "O telefone deve conter 11 dígitos")
        String telefone,
        @NotBlank
        @Email
        String email,
        Endereco endereco ) {

    public Pessoa transformaParaPessoa() {
            return new Pessoa(nome, cpf, dataNascimento, telefone, email, endereco);
    }
}
