package com.livros.literalura.model;

import com.livros.literalura.dto.AutorDTO;
import com.livros.literalura.dto.LivroDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_idioma", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "idioma")
    private List<String> idioma;
    private Integer download;

    public Livro(){}

    public Livro(LivroDTO livroDTO, Autor autor) {
        this.titulo=livroDTO.title();
        this.autor=autor;
        this.idioma=livroDTO.languages();
        this.download=livroDTO.download_count();
    }

    public Livro(Livro livro) {
        this.titulo=livro.getTitulo();
        this.autor=livro.getAutor();
        this.idioma=livro.getIdioma();
        this.download=livro.getDownload();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    @Override
    public String toString() {
        return "\nTítulo: "+titulo
                +"\nAutor: "+autor.getNome()
                +"\nIdioma: "+idioma
                +"\nNúmero de downloads: "+download+"\n";
    }
}