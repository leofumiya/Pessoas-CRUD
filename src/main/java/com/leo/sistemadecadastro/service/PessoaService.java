package com.leo.sistemadecadastro.service;

import com.leo.sistemadecadastro.entities.Pessoa;
import com.leo.sistemadecadastro.exception.EnderecoNaoEncontradoException;
import com.leo.sistemadecadastro.exception.PessoaNaoEncontradaException;
import com.leo.sistemadecadastro.repository.EnderecoRepository;
import com.leo.sistemadecadastro.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.reflect.Array.get;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa) {
        if (enderecoRepository.findById(pessoa.getEndereco().getId()).isPresent()) {
            return repository.save(pessoa);
        } else {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + pessoa.getEndereco().getId());
        }
    }

    @Transactional(readOnly = true)
    public List<Pessoa> listar() {
        return repository.findAll();
    }

    @Transactional
    public Pessoa atualizar(Long id, Pessoa pessoa) {
        if (enderecoRepository.findById(pessoa.getEndereco().getId()).isPresent()) {
            Pessoa pessoaAtualizada = buscarPessoaPorId(id);
            pessoaAtualizada.atualizarInformacoes(pessoa);
            return repository.save(pessoaAtualizada);
        } else {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + pessoa.getEndereco().getId());
        }
    }

    @Transactional
    public void excluir(Long id) {
        Pessoa pessoa = buscarPessoaPorId(id);
        repository.delete(pessoa);
    }

    private Pessoa buscarPessoaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada com o ID: " + id));
    }

}
