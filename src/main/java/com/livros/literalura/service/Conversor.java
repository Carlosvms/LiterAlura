package com.livros.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.livros.literalura.dto.LivroDTO;

import java.util.List;

public class Conversor {
    private ObjectMapper mapper = new ObjectMapper();

    public LivroDTO converteDados(String json){
        try {
            var modulo = mapper.readTree(json);
            var recptor = modulo.get("results").get(0);
            LivroDTO livroDTO = mapper.treeToValue(recptor, LivroDTO.class);
            return livroDTO;
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
