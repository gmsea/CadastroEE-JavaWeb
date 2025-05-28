package cadastrobd;

import cadastrobd.model.Pessoa;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.dao.PessoaFisicaDAO;
import cadastrobd.model.dao.PessoaJuridicaDAO;

import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\nMENU PRINCIPAL:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Obter por ID");
            System.out.println("5 - Listar todos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            if (opcao != 0) {
                int tipoPessoa;
                System.out.println("\nTipo de Pessoa:");
                System.out.println("1 - Pessoa Física");
                System.out.println("2 - Pessoa Jurídica");
                System.out.print("Escolha: ");
                tipoPessoa = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> incluir(tipoPessoa);
                    case 2 -> alterar(tipoPessoa);
                    case 3 -> excluir(tipoPessoa);
                    case 4 -> obterPorId(tipoPessoa);
                    case 5 -> listarTodos(tipoPessoa);
                    default -> System.out.println("Opção inválida.");
                }
            }

        } while (opcao != 0);

        System.out.println("Encerrando...");
    }

    private static void incluir(int tipo) {
        if (tipo == 1) {
            PessoaFisica pf = lerPessoaFisica();
            new PessoaFisicaDAO().incluir(pf);
            System.out.println("Pessoa Física incluída com sucesso!");
        } else if (tipo == 2) {
            PessoaJuridica pj = lerPessoaJuridica();
            new PessoaJuridicaDAO().incluir(pj);
            System.out.println("Pessoa Jurídica incluída com sucesso!");
        }
    }

    private static void alterar(int tipo) {
        System.out.print("ID da pessoa a alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            PessoaFisica pf = lerPessoaFisica();
            pf.setId(id);
            new PessoaFisicaDAO().alterar(pf);
            System.out.println("Pessoa Física alterada com sucesso!");
        } else if (tipo == 2) {
            PessoaJuridica pj = lerPessoaJuridica();
            pj.setId(id);
            new PessoaJuridicaDAO().alterar(pj);
            System.out.println("Pessoa Jurídica alterada com sucesso!");
        }
    }

    private static void excluir(int tipo) {
        System.out.print("ID da pessoa a excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            new PessoaFisicaDAO().excluir(id);
            System.out.println("Pessoa Física excluída com sucesso!");
        } else if (tipo == 2) {
            new PessoaJuridicaDAO().excluir(id);
            System.out.println("Pessoa Jurídica excluída com sucesso!");
        }
    }

    private static void obterPorId(int tipo) {
        System.out.print("ID da pessoa: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            PessoaFisica pf = new PessoaFisicaDAO().getPessoa(id);
            System.out.println(pf != null ? pf : "Pessoa Física não encontrada.");
        } else if (tipo == 2) {
            PessoaJuridica pj = new PessoaJuridicaDAO().getPessoa(id);
            System.out.println(pj != null ? pj : "Pessoa Jurídica não encontrada.");
        }
    }

    private static void listarTodos(int tipo) {
        if (tipo == 1) {
            List<PessoaFisica> lista = new PessoaFisicaDAO().getPessoas();
            lista.forEach(System.out::println);
        } else if (tipo == 2) {
            List<PessoaJuridica> lista = new PessoaJuridicaDAO().getPessoas();
            lista.forEach(System.out::println);
        }
    }

    private static PessoaFisica lerPessoaFisica() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        return new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
    }

    private static PessoaJuridica lerPessoaJuridica() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        return new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
    }
}
