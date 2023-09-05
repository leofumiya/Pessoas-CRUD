package com.leo.sistemadecadastro.service;

import com.leo.sistemadecadastro.builder.PessoaBuilder;
import com.leo.sistemadecadastro.controller.PessoaController;
import com.leo.sistemadecadastro.dto.PessoaDTO;
import com.leo.sistemadecadastro.entities.Pessoa;
import com.leo.sistemadecadastro.exception.PessoaNaoEncontradaException;
import com.leo.sistemadecadastro.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PessoaServiceTest {

    @InjectMocks
    private PessoaService service;

    @Mock
    private PessoaRepository repository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCadastrarPessoa() {
        Pessoa pessoa = PessoaBuilder.pessoa();
        when(repository.save(pessoa)).thenReturn(pessoa);

        Pessoa pessoaCadastrada = service.cadastrar(pessoa);

        assertNotNull(pessoaCadastrada);
        assertEquals(pessoa, pessoaCadastrada);
        verify(repository, times(1)).save(pessoa);
    }

    @Test
    void testListarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        when(repository.findAll()).thenReturn(pessoas);

        List<Pessoa> resultado = service.listar();

        assertNotNull(resultado);
        assertEquals(pessoas, resultado);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testAtualizarPessoa() {
        Long id = 1L;
        PessoaDTO pessoaDTO = PessoaBuilder.pessoaDTOAtualizada();
        Pessoa pessoaExistente = PessoaBuilder.pessoa();

        when(repository.findById(id)).thenReturn(Optional.of(pessoaExistente));
        when(repository.save(any(Pessoa.class))).thenReturn(pessoaExistente);

        Pessoa pessoaAtualizada = service.atualizar(id, pessoaDTO);

        assertNotNull(pessoaAtualizada);
        assertEquals(pessoaDTO.nome(), pessoaAtualizada.getNome());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void testAtualizarPessoaNaoEncontrada() {
        Long id = 1L;
        PessoaDTO pessoaDTO = PessoaBuilder.pessoaDTO();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PessoaNaoEncontradaException.class, () -> service.atualizar(id, pessoaDTO));
    }

    @Test
    void testExcluirPessoa() {
        Long id = 1L;
        Pessoa pessoaExistente = PessoaBuilder.pessoa();
        when(repository.findById(id)).thenReturn(Optional.of(pessoaExistente));

        service.excluir(id);

        verify(repository, times(1)).delete(pessoaExistente);
    }

    @Test
    void testExcluirPessoaNaoEncontrada() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PessoaNaoEncontradaException.class, () -> service.excluir(id));
    }

}
