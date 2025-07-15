package br.com.alura.literalura.view;

import br.com.alura.literalura.dto.DadosAutor;
import br.com.alura.literalura.dto.DadosLivro;
import br.com.alura.literalura.dto.ResultadosBusca;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component // Indica ao Spring para gerenciar esta classe
public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO_API = "https://gutendex.com/books/?search=";
    public Menu(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibir() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                     ***********************************
                     Escolha o número de sua opção:
                     1- Buscar livro pelo título
                     2- Listar livros registrados
                     3- Listar autores registrados
                     4- Listar autores vivos em um determinado ano
                     5- Listar livros em um determinado idioma
                     
                     0 - Sair
                     ***********************************
                    """;
            System.out.println(menu);

            // Tratamento de exceção para entrada não numérica
            if(scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                switch (opcao) {
                    case 1:
                        buscarLivroPeloTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosEmAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo do LiterAlura...");
                        break;
                    default:
                        System.out.println("Opção Inválida!");
                }
            } else {
                System.out.println("Por favor, digite um número válido");
                scanner.next(); // Descarta a entrada inválida
            }
        }
    }

    private void buscarLivroPeloTitulo() {
        System.out.println("\nDigite o nome do livro que você deseja buscar:");
        var nomeLivro = scanner.nextLine();
        var url = ENDERECO_API + nomeLivro.replace(" ", "+");

        System.out.println("Buscando livro com o nome " + nomeLivro + "...");
        var json = consumoApi.obterDados(url);
        ResultadosBusca resultadosBusca = conversor.obterDados(json, ResultadosBusca.class);

        Optional<DadosLivro> livroBuscado = resultadosBusca.livros().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nomeLivro.toLowerCase()))
                .findFirst();

        if(livroBuscado.isPresent()) {
            DadosLivro dadosLivro = livroBuscado.get();

            Optional<Autor> autorExistente = autorRepository.findByNomeContainingIgnoreCase(dadosLivro.autores().get(0).nome());

            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
                System.out.println("Autor já existe no banco de dados");
            } else {
                System.out.println("Novo autor. Salvando no banco de dados");
                autor = new Autor(dadosLivro.autores().get(0));
                autorRepository.save(autor);
            }

            Livro livro = new Livro(dadosLivro);
            livro.setAutor(autor);

            try {
                livroRepository.save(livro);
                System.out.println("\nLivro salvo com sucesso!");
                System.out.println(livro);
            } catch (Exception e) {
                System.out.println("Erro ao salvar o livro: " + e.getMessage());
                System.out.println("Possivelmente este livro já foi salvo anteriormente.");
            }
        } else {
            System.out.println("Nenhum livro encontrado com esse título.");
        }
    }

    private void listarLivrosRegistrados() {
        System.out.println("\n----- Livros Registrados -----");
        var livros = livroRepository.findAll();
        if(livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        System.out.println("\n----- Autores Registrados -----");
        var autores = autorRepository.findAll();
        if(autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEmAno() {
        System.out.println("Digite um ano para listar os autores vivos:");
        if (scanner.hasNextInt()) {
            var ano = scanner.nextInt();
            scanner.nextLine();

            var autoresvivos = autorRepository.findAutoresVivosEmAno(ano);
            if(autoresvivos.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado no ano de " + ano + ".");
            } else {
                System.out.println("\n----- Autores Vivos em " + ano + " -----");
                autoresvivos.forEach(System.out::println);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite um ano válido.");
            scanner.next();
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
            Digite o idioma para busca:
            es - espanhol
            en - inglês
            fr - francês
            pt - português
            """);
        var idiomaEscolhido = scanner.nextLine();
        String idioma;

        switch (idiomaEscolhido.toLowerCase()) {
            case "es":
                idioma = "es";
                break;
            case "en":
                idioma = "en";
                break;
            case "fr":
                idioma = "fr";
                break;
            case "pt":
                idioma = "pt";
                break;
            default:
                System.out.println("Opção de idioma inválida.");
                return;
        }
        var livrosPorIdioma = livroRepository.findByIdioma(idioma);
        if(livrosPorIdioma.isEmpty()) {
            System.out.println("Não existem livros nesse idioma no banco de dados.");
        } else {
            System.out.println("\n----- Livros em " + idioma.toUpperCase() + " -----");
            livrosPorIdioma.forEach(System.out::println);
        }
    }
}
