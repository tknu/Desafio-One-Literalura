package br.com.alura.literalura.model;

import br.com.alura.literalura.dto.DadosAutor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    private String nome;

    private int anoDeNascimento;
    private int anoDeFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoDeNascimento = dadosAutor.anoDeNascimento();
        this.anoDeFalecimento = dadosAutor.anoDeFalecimento();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(int anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public int getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(int anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor: " + nome +
                " (Nascimento: " + anoDeNascimento +
                ", Falecimento: " + anoDeFalecimento + ")";

    }
}
