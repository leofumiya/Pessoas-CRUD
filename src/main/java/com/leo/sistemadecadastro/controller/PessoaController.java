package com.leo.sistemadecadastro.controller;


import com.leo.sistemadecadastro.dto.PessoaDTO;
import com.leo.sistemadecadastro.entities.Pessoa;
import com.leo.sistemadecadastro.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody @Valid PessoaDTO pessoaDTO) {
        Pessoa pessoaCadastrada = service.cadastrar(pessoaDTO.transformaParaPessoa());
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        List<Pessoa> pessoas = service.listar();
        return ResponseEntity.ok(pessoas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaDTO pessoaDTO) {
        Pessoa pessoaAtualizada = service.atualizar(id, pessoaDTO.transformaParaPessoa());
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
