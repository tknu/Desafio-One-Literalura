package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
    @JsonAlias("name") String nome,
    @JsonAlias("birth_day") Integer anoDeNascimento,
    @JsonAlias("death_year") Integer anoDeFalecimento
){
}
