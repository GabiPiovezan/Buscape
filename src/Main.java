import java.sql.*;
import java.util.Scanner;
public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/buscape";
    private static final String DB_USUARIO = "root";
    private static final String DB_SENHA = "";


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        System.out.println("---Buscapé produtos Naturais---");
        System.out.println("---Bem-vindo ao sistema!---");
        System.out.println("Como podemos ajudar?");

        int opcao = 0;

        while (opcao != 7) {
            exibirmenu();
            System.out.println("Escolha uma opção: ");

            if (scan.hasNextInt()) {
                opcao = scan.nextInt();
                scan.nextLine();

            } else {
                System.out.println("Por favor, escolha uma opção!");
                scan.nextLine();
                continue;
            }
            switch (opcao) {
                case 1:
                    cadastrarCliente(scan);
                    break;
                case 2:
                    cadastrarProduto(scan);
                    break;
                case 3:
                    exibirmenu2(scan);
                    break;
                case 4:
                    buscarClientePorNome(scan);
                    break;
                case 5:
                    listarTodosClientes(scan);
                    break;
                case 6:
                    listarTodosProdutos(scan);
                    break;
                case 7:
                    fazerUpdatePedido(scan);
                    break;
                case 8:
                    fazerDelete(scan);
                    break;
                case 9:
                    System.out.println("Saindo do sistema......até logo!");
                    break;
                default:
                    System.out.println("Esta opção é Invalida, por favor escolha outra!");

            }
        }

        scan.close();

    }

    public static void exibirmenu() {
        System.out.println("-----------------------");
        System.out.println("---Menu do Buscapé---");
        System.out.println("-1 Cadastro de Clientes: ");
        System.out.println("-2 Cadastro de Produtos: ");
        System.out.println("-3 Pedido");
        System.out.println("-4 Buscar Clientes por nome: ");
        System.out.println("-5 Listar todos os Clientes: ");
        System.out.println("-6 Listar todos os Produtos: ");
        System.out.println("-7 Atualizar Informações do Pedido: ");
        System.out.println("-8 Excuir Informações do Pedido: ");
        System.out.println("-9 Sair do sistema!");
        System.out.println("-------------------------------");
    }

    public static void cadastrarCliente(Scanner scan) {

        System.out.println("-----Cadastro de Cliente-----");
        System.out.println("Digite seu nome: ");
        String nome = scan.nextLine();
        System.out.println("Digite seu CPF: ");
        String cpf = scan.nextLine();
        System.out.println("Digite seu Endereço: ");
        String endereco = scan.nextLine();
        if (endereco.length() > 250) {
            System.out.println("Endereço Inválido!");
        }
        System.out.println("Digite seu Telefone: ");
        String telefone = scan.nextLine();
        System.out.println("Digite seu E-mail: ");
        String email = scan.nextLine();
        if (!email.contains("@")) {
            System.out.println("E-mail inválido!");
        }

        String sql = "INSERT INTO cliente(nome,cpf,endereco,telefone,email) VALUES(?,?,?,?,?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, endereco);
            stmt.setString(4, telefone);
            stmt.setString(5, email);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente salvo com SUCESSO!");
            }
        } catch (Exception e) {
            System.out.println("Falha ao Salvar!");
            throw new RuntimeException(e);
        }
    }

    public static void listarTodosClientes(Scanner scan) {
        System.out.println("Lista de Clientes");
        String sql = "SELECT * FROM cliente";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            boolean encontrouDados = false;

            while (rs.next()) {
                encontrouDados = true;
                System.out.println("Nome: "
                        + rs.getString("nome")
                        + " CPF: "
                        + rs.getString("cpf")
                        + " Email: "
                        + rs.getString("email")
                );

            }
            if (encontrouDados) {
                System.out.println("Dados encontrados!");
            } else {
                System.out.println("Nenhum dado encontrado!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void buscarClientePorNome(Scanner scan) {

        System.out.println("Digite o nome do Cliente: ");
        String nome = scan.nextLine();


        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();


            boolean encontrouDados = false;

            while (rs.next()) {
                encontrouDados = true;
                System.out.println("Nome: "
                        + rs.getString("nome")
                        + " CPF: "
                        + rs.getString("cpf")
                        + " Email: "
                        + rs.getString("email")
                );


            }
            if (encontrouDados) {
                System.out.println("Dados encontrados!");
            } else {
                System.out.println("Nenhum dado encontrado!");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void cadastrarProduto(Scanner scan) {

        System.out.println("Cadastro de Produto");
        System.out.println("Digite o nome do Produto: ");
        String nome = scan.nextLine();
        System.out.println("Digite a Marca do Produto: ");
        String marca = scan.nextLine();
        System.out.println("Digite o Preço do produto: ");
        String preco = scan.nextLine();
        System.out.println("Digite a Quantidade: ");
        String quantidade = scan.nextLine();

        String sql = "INSERT INTO produto(nome,marca,preco,quantidade) VALUES(?,?,?,?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, marca);
            stmt.setString(3, preco);
            stmt.setString(4, quantidade);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto Cadastrado com SUCESSO!");
            }
        } catch (Exception e) {
            System.out.println("Falha ao Cadastrar!");
            throw new RuntimeException(e);
        }
    }


    public static void listarTodosProdutos(Scanner scan) {
        System.out.println("Lista de Produtos");
        String sql = "SELECT * FROM produto";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean encontrouDados = false;

            while (rs.next()) {
                encontrouDados = true;
                System.out.println("Nome: "
                        + rs.getString("nome")
                        + " Marca: "
                        + rs.getString("marca")
                        + " Quantidade: "
                        + rs.getString("quantidade")
                        + "Preço: "
                        + rs.getString("preco")
                );


            }
            if (encontrouDados) {
                System.out.println("Dados encontrados!");
            } else {
                System.out.println("Nenhum dado encontrado!");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private static Connection conectar() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);

    }

    public static void fazerCadastroDoPedido(Scanner scan) {
        listarTodosClientes(scan);
        listarTodosProdutos(scan);

        System.out.println("----Informe seus dados----");
        System.out.println("Informe o id do seu Nome: ");
        String id_cliente = scan.nextLine();

        System.out.println("----Acesse o Portfólio de Produtos----");
        System.out.println("Informe o id do Produto: ");
        String id_produto = scan.nextLine();

        System.out.println("Informe a Quantidade desejada: ");
        String quantidade = scan.nextLine();

        String sql = "INSERT INTO pedido(id_cliente,id_produto,quantidade) VALUES (?,?,?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id_cliente);
            stmt.setString(2, id_produto);
            stmt.setString(3, quantidade);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pedido Recebido com SUCESSO!");
            }
        } catch (Exception e) {
            System.out.println("Falha ao Cadastrar!");
            throw new RuntimeException(e);
        }

    }
    public static void exibirmenu2(Scanner scan) {
        int opcao2 = 0;

        while (opcao2 != 2) {
        System.out.println("---Menu do Pedido---");
        System.out.println("-1 Montar Pedido");
        System.out.println("-2 Sair do Sistema!");

            if (scan.hasNextInt()) {
                opcao2 = scan.nextInt();
                scan.nextLine();
            } else {
                System.out.println("Por Favor, escolha uma opção válida!");
                scan.nextLine();
                continue;
            }
            switch (opcao2) {
                case 1:
                    fazerCadastroDoPedido(scan);
                    break;
                case 2:
                    System.out.println("Encerrando o Sistema.....Até logo!");
                    break;
                default:
                    System.out.println("Esta opção é Invalida, por favor escolha outra!");
            }
        }
    }
    public static void fazerUpdatePedido(Scanner scan) {
        System.out.println("por favor, digite o id do pedido que deseja alterar: ");
        int idPedido = scan.nextInt();
        scan.nextLine();

        System.out.println("Digite o NOVO nome do produto: ");
        String novoProduto = scan.nextLine();

        System.out.println("digite a NOVA quantidade: ");
        String novaQuantidade = scan.nextLine();

        String sql = "UPDATE pedido SET id_produto = ?, quantidade = ? Where id = ? ";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoProduto);
            stmt.setString(2, novaQuantidade);
            stmt.setInt(3, idPedido);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pedido Atualizado com SUCESSO!");
            } else {
                System.out.println("Nenhum pedido foi encontrado com o id informado!");
            }
        } catch (Exception e) {
            System.out.println("Falha ao atualizar o Pedido!");
            throw new RuntimeException(e);
        }

        }
        public static void fazerDelete(Scanner scan){
            System.out.println("Por favor, digite o id do pedido que deseja excluir: ");
            int idPedido = scan.nextInt();
            scan.nextLine();

            String sql = "DELETE FROM pedido WHERE id = ?";

            try(Connection conn = conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idPedido);

                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Pedido excluido com SUCESSO!");
                } else {
                    System.out.println("Nenhum pedido foi encontrado com o id informado!");
                }
            } catch (Exception e) {
                System.out.println("Falha ao excluir o pedido!");
                throw new RuntimeException(e);
            }

        }

    }

