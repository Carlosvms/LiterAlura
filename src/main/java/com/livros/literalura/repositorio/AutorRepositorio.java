package com.livros.literalura.repositorio;

import com.livros.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.ano_nasc <= :ano AND a.ano_fale >= :ano")
    List<Autor> buscaAno(Integer ano);
}
