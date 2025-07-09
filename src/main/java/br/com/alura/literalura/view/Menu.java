package br.com.alura.literalura.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component // Indica ao Spring para gerenciar esta classe
public class Menu {
    private final Scanner scanner = new Scanner(System.in);

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
                        System.out.println("Opção 1 escolhida: Buscar livro pelo título");
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
}
