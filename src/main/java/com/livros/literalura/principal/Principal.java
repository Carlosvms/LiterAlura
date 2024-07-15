package com.livros.literalura.principal;

import com.livros.literalura.dto.LivroDTO;
import com.livros.literalura.model.Autor;
import com.livros.literalura.model.Livro;
import com.livros.literalura.service.AutorService;
import com.livros.literalura.service.ConsumoApi;
import com.livros.literalura.service.Conversor;
import com.livros.literalura.service.LivroService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner digitar = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private Conversor conversor = new Conversor();
    private LivroService livroService;
    private AutorService autorService;

    public Principal(AutorService autorService, LivroService livroService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0){
            String menu = """
                \n--------------------------
                Digite um número referente ao tópico:
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registradores
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                0 - Sair
                """;
            System.out.println(menu);
            opcao = digitar.nextInt();
            digitar.nextLine();
            switch (opcao){
                case 1:
                    buscarLivroNome();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAtoresAno();
                    break;
                case 5:
                    listarLivroIdioma();
                    break;
                case 0:
                    break;
            }
        }
    }

    public void buscarLivroNome() {
        System.out.println("Digite o nome do livro: ");
        var nomeLivro = digitar.nextLine();
        var json = consumoApi.busca(ENDERECO+nomeLivro.replaceAll(" ", "%20"));
        LivroDTO livroDTO = conversor.converteDados(json);
        try {
            List<Autor> autores = livroDTO.authors().stream().map(a -> new Autor(a, livroDTO)).toList();
            var autor = autores.getFirst();
            Livro livro = new Livro(livroDTO, autor);
            System.out.println(livro);
            Optional<Autor> autorPresent = autorService.findByNome(autor.getNome());
            if (!(autorPresent.isPresent())){
                autorService.salvar(autor);
            }
            Optional<Livro> livroPresent = livroService.findByTitulo(livro.getTitulo());
            if (!(livroPresent.isPresent())){
                livroService.salvar(livro);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void listarLivros() {
        System.out.println("Aqui está a lista de livros registrados no banco");
        try {
            List<Livro> livro = livroService.listarLivro();
            livro.stream().map(Livro::new).forEach(System.out::println);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void listarAutores() {
        System.out.println("Aqui está a lista de autores registrados no banco");
        try {
            List<Autor> autores = autorService.listarAutores();
            autores.stream().map(Autor::new).forEach(System.out::println);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void listarAtoresAno() {
        System.out.println("Digite um ano");
        var ano = digitar.nextInt();
        digitar.nextLine();
        List<Autor> autores = autorService.findByAno(ano);
        autores.forEach(a -> System.out.println("\nAutor: "+a.getNome()
                +"\nAno de nascimento: "+a.getAno_nasc()
                +"\nAno de falecimento: "+a.getAno_fale()
                +"\nLivros"+a.getLivros().stream().map(Livro::getTitulo).toList()
        ));
    }

    private void listarLivroIdioma() {
        var list_idioma = """
                Digite uma sigla de um idioma para a busca
                pt - Português
                fr - Francês
                en - Inglês
                es - Espanhol
                """;
        System.out.println(list_idioma);
        var sigla = digitar.nextLine();
        List<Livro> livros = livroService.listarLivroIdioma(sigla);
        livros.forEach(System.out::println);
        if (livros.isEmpty()){
            System.out.println("\nNão existe livros cadastros com esse idioma");
        }
    }

}