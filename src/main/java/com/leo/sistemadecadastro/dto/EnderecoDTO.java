package com.leo.sistemadecadastro.dto;

import com.leo.sistemadecadastro.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
    @NotBlank
    String logradouro,
    @NotBlank
    String bairro,
    @NotBlank
    String numero,
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String cep,
    @NotBlank
    String cidade,
    @NotBlank
    String uf ){

    public Endereco transformaParaEndereco() {
        return new Endereco(logradouro, bairro, numero, cep, cidade, uf);
    }

}
