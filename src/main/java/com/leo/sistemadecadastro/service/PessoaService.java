package com.leo.sistemadecadastro.service;

import com.leo.sistemadecadastro.dto.PessoaDTO;
import com.leo.sistemadecadastro.entities.Pessoa;
import com.leo.sistemadecadastro.exception.PessoaNaoEncontradaException;
import com.leo.sistemadecadastro.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    @Autowired
    public PessoaService(PessoaRepository repository){
        this.repository = repository;
    }

    public Pessoa cadastrar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public List<Pessoa> listar() {
        return repository.findAll();
    }

    public Pessoa atualizar(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoaAtualizada = buscarPessoaPorId(id);
        pessoaAtualizada.atualizarInformacoes(pessoaDTO);
        return repository.save(pessoaAtualizada);
    }

    public void excluir(Long id) {
        Pessoa pessoa = buscarPessoaPorId(id);
        repository.delete(pessoa);
    }

    private Pessoa buscarPessoaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada com o ID: " + id));
    }

}
