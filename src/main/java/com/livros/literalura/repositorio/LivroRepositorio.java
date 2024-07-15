package com.livros.literalura.repositorio;

import com.livros.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String nome);

    List<Livro> findByIdioma(String idioma);
}