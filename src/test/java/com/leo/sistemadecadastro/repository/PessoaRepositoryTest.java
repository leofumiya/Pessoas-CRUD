package com.leo.sistemadecadastro.repository;

import com.leo.sistemadecadastro.builder.PessoaBuilder;
import com.leo.sistemadecadastro.entities.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository repository;

    @Test
    void testCadastrarPessoa() {
        Pessoa pessoa = PessoaBuilder.pessoa();

        Pessoa pessoaSalva = repository.save(pessoa);

        assertNotNull(pessoaSalva.getId());
    }

    @Test
    void testBuscarPessoaPorId() {
        Pessoa pessoa = PessoaBuilder.pessoa();
        Pessoa pessoaSalva = repository.save(pessoa);

        Optional<Pessoa> pessoaEncontrada = repository.findById(pessoaSalva.getId());

        assertTrue(pessoaEncontrada.isPresent());
        assertEquals(pessoaSalva, pessoaEncontrada.get());
    }

    @Test
    void testListarPessoas() {
        Pessoa pessoa1 = PessoaBuilder.pessoa();
        Pessoa pessoa2 = PessoaBuilder.pessoa1();
        repository.saveAll(List.of(pessoa1, pessoa2));

        List<Pessoa> pessoas = repository.findAll();

        assertEquals(2, pessoas.size());
    }

    @Test
    void testAtualizarPessoa() {
        Pessoa pessoa = PessoaBuilder.pessoa();
        Pessoa pessoaSalva = repository.save(pessoa);

        pessoaSalva.setNome("Novo Nome");
        repository.save(pessoaSalva);

        Optional<Pessoa> pessoaAtualizada = repository.findById(pessoaSalva.getId());

        assertTrue(pessoaAtualizada.isPresent());
        assertEquals("Novo Nome", pessoaAtualizada.get().getNome());
    }

    @Test
    void testExcluirPessoa() {
        Pessoa pessoa = PessoaBuilder.pessoa();
        Pessoa pessoaSalva = repository.save(pessoa);

        repository.delete(pessoaSalva);

        Optional<Pessoa> pessoaExcluida = repository.findById(pessoaSalva.getId());

        assertFalse(pessoaExcluida.isPresent());
    }

}
