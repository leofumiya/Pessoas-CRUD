package com.leo.sistemadecadastro.repository;

import com.leo.sistemadecadastro.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
