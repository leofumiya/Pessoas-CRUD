package com.leo.sistemadecadastro.controller;


import com.leo.sistemadecadastro.dto.PessoaDTO;
import com.leo.sistemadecadastro.entities.Pessoa;
import com.leo.sistemadecadastro.service.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
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
    @Transactional
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaDTO dados) {
        Pessoa pessoaAtualizada = service.atualizar(id, dados);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
