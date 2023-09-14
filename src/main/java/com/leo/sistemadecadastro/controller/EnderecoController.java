package com.leo.sistemadecadastro.controller;

import com.leo.sistemadecadastro.dto.EnderecoDTO;
import com.leo.sistemadecadastro.entities.Endereco;
import com.leo.sistemadecadastro.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco enderecoCadastrado = service.cadastrar(enderecoDTO.transformaParaEndereco());
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listar() {
        List<Endereco> enderecos = service.listar();
        return ResponseEntity.ok(enderecos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco enderecoAtualizado = service.atualizar(id, enderecoDTO.transformaParaEndereco());
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
