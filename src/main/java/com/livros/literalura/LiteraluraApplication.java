package com.livros.literalura;

import com.livros.literalura.principal.Principal;
import com.livros.literalura.service.AutorService;
import com.livros.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LivroService livroService;
	@Autowired
	private AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorService, livroService);
		principal.exibeMenu();
	}
}
