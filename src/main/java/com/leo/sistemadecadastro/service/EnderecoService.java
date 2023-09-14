package com.leo.sistemadecadastro.service;

import com.leo.sistemadecadastro.dto.EnderecoDTO;
import com.leo.sistemadecadastro.entities.Endereco;
import com.leo.sistemadecadastro.exception.EnderecoNaoEncontradoException;
import com.leo.sistemadecadastro.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Transactional
    public Endereco cadastrar(Endereco endereco) {
        return repository.save(endereco);
    }

    @Transactional(readOnly = true)
    public List<Endereco> listar() {
        return repository.findAll();
    }

    @Transactional
    public Endereco atualizar(Long id, Endereco endereco) {
        Endereco enderecoAtualizado = buscarEnderecoPorId(id);
        enderecoAtualizado.atualizarInformacoes(endereco);
        return repository.save(enderecoAtualizado);
    }

    @Transactional
    public void excluir(Long id) {
        Endereco endereco = buscarEnderecoPorId(id);
        repository.delete(endereco);
    }

    private Endereco buscarEnderecoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + id));
    }

}
