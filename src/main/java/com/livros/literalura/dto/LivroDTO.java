package com.livros.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        String title, List<String> languages,
        List<AutorDTO> authors, Integer download_count) {
}