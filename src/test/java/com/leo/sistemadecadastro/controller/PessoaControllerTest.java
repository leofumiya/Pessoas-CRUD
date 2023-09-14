package com.leo.sistemadecadastro.controller;

import com.leo.sistemadecadastro.builder.PessoaBuilder;
import com.leo.sistemadecadastro.dto.PessoaDTO;
import com.leo.sistemadecadastro.entities.Pessoa;
import com.leo.sistemadecadastro.exception.PessoaNaoEncontradaException;
import com.leo.sistemadecadastro.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PessoaControllerTest {

    @InjectMocks
    private PessoaController controller;

    @Mock
    private PessoaService service;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCadastrarPessoa() {
        PessoaDTO pessoaDTO = PessoaBuilder.pessoaDTO();
        Pessoa pessoaCadastrada = PessoaBuilder.pessoa();

        when(service.cadastrar(any())).thenReturn(pessoaCadastrada);

        ResponseEntity<Pessoa> response = controller.cadastrar(pessoaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pessoaCadastrada, response.getBody());
    }

    @Test
    public void testListarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();

        when(service.listar()).thenReturn(pessoas);

        ResponseEntity<List<Pessoa>> response = controller.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoas, response.getBody());
    }

    @Test
    public void testAtualizarPessoa() {
        Long id = 1L;
        PessoaDTO pessoaDTO = PessoaBuilder.pessoaDTO();
        Pessoa pessoaAtualizada = PessoaBuilder.pessoaAtualizada();

        when(service.atualizar(id, pessoaDTO)).thenReturn(pessoaAtualizada);

        ResponseEntity<Pessoa> response = controller.atualizar(id, pessoaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoaAtualizada, response.getBody());
    }

    @Test
    public void testExcluirPessoa() {
        Long id = 1L;

        ResponseEntity<Void> response = controller.excluir(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).excluir(id);
    }

    @Test
    public void testAtualizarPessoaNaoEncontrada() {
        Long id = 1L;
        PessoaDTO pessoaDTO = PessoaBuilder.pessoaDTO();

        when(service.atualizar(id, pessoaDTO)).thenThrow(PessoaNaoEncontradaException.class);

        assertThrows(PessoaNaoEncontradaException.class, () -> controller.atualizar(id, pessoaDTO));
    }

    @Test
    public void testExcluirPessoaNaoEncontrada() {
        Long id = 1L;

        doThrow(PessoaNaoEncontradaException.class).when(service).excluir(id);

        assertThrows(PessoaNaoEncontradaException.class, () -> controller.excluir(id));
    }

}
