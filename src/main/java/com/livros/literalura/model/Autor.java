package com.livros.literalura.model;

import com.livros.literalura.dto.AutorDTO;
import com.livros.literalura.dto.LivroDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer ano_nasc;
    private Integer ano_fale;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor(){}

    public Autor(Autor autor) {
        this.nome=autor.getNome();
        this.ano_nasc=autor.getAno_fale();
        this.ano_fale=autor.getAno_nasc();
    }

    public Autor(AutorDTO autor, LivroDTO livroDTO) {
        this.nome= autor.name();
        this.ano_nasc= autor.birth_year();
        this.ano_fale= autor.death_year();
        List<Livro> livro = new ArrayList<>();
        livro.stream().map(Livro::new).collect(Collectors.toList());
        this.livros=livro;
    }

    public Autor(String nome, Integer anoNasc, Integer anoFale) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno_nasc() {
        return ano_nasc;
    }

    public void setAno_nasc(Integer ano_nasc) {
        this.ano_nasc = ano_nasc;
    }

    public Integer getAno_fale() {
        return ano_fale;
    }

    public void setAno_fale(Integer ano_fale) {
        this.ano_fale = ano_fale;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "\n---Autor---"+
                "\nNome: "+nome+
                "\nAno de nascimento: "+ano_nasc+
                "\nAno de falecimento: "+ano_fale;
    }
}
