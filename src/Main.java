import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/cliente";
    private static final String DB_USUARIO = "root";
    private static final String DB_SENHA = "root";


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        List<String> clientes = new ArrayList<>();
        List<String> produto = new ArrayList<>();



        System.out.println("---Buscapé produtos Naturais---");
        System.out.println("---Bem-vindo ao sistema!---");
        System.out.println("Como podemos ajudar?");

        int opcao = 0;

        while (opcao != 5){
            exibirmenu();
            System.out.println("Escolha uma opção: ");

            if (scan.hasNextInt()){
                opcao = scan.nextInt();
                scan.nextLine();

            } else {
                System.out.println("Por favor, escolha uma opção!");
                scan.nextLine();
                continue;
            }
        switch (opcao) {
            case 1:
                cadastrarCliente(scan, clientes);
                break;
            case 2:
                cadastrarProduto(scan, produto);
                break;
            case 3:
                listarTodosClientes(clientes);
                break;
            case 4:
                listarTodosProdutos(produto);
                break;
            case 5:
                System.out.println("Saindo do sistema......até logo!");
                break;
            default:
                System.out.println("Esta opção é Invalida, por favor escolha outra!");

        }
        }
        scan.close();



    }
    public static void exibirmenu(){
        System.out.println("-----------------------");
        System.out.println("---Menu do Buscapé---");
        System.out.println("-1 Cadastro de Clientes: ");
        System.out.println("-2 Cadastro de Produtos: ");
        System.out.println("-3 Listar todos os Clientes: ");
        System.out.println("-4 Listar todos os Produtos: ");
        System.out.println("-------------------------------");
    }
    public static void cadastrarCliente(Scanner scan, List<String> cliente){

        System.out.println("-----Cadastro de Cliente-----");
        System.out.println("Digite seu nome: ");
        String nome = scan.nextLine();
        System.out.println("Digite seu CPF: ");
        String cpf = scan.nextLine();
        System.out.println("Digite seu Endereço: ");
        String endereco = + scan.nextLine();
        System.out.println("Digite seu Telefone: ");
        String telefone = scan.nextLine();
        System.out.println("Digite seu E-mail: ");
        if (!email.contains("@")){
            System.out.println("E-mail inválido!");;
        }

            String sql = "INSERT INTO cliente(nome,cpf,endereco,telefone,email) VALUES(?,?,?,?,?)";
            try (connection conn = conectar(); preparedStatement stmt = conn.preparedStatement(sql)){
                stmt.setString(1,nome);
                stmt.setString(2,CPF);
                stmt.setString(3,endereco);
                stmt.setString(4,telefone);
                stmt.setString(5,email);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas>0){
                    System.out.println("Cliente salvo com SUCESSO!");

                }

        }
        cliente.add(dados);
        System.out.println("Cliente cadastrado com SUCESSO!");
    }
    public static void listarTodosClientes(List<String> cliente){
        System.out.println("Lista de Clientes");

        if (cliente.isEmpty()){
            System.out.println("Nenhum cliente Encontrado!");
        } else {
            for (int i = 0; i< cliente.size(); i++){
                System.out.println("código: " + (i+1) + "." + cliente.get(i).toString());
            }
        }
    }
    public static void cadastrarProduto(Scanner scan, List<String> produto){
        String dados = "";
        System.out.println("Cadastro de Produto");
        System.out.println("Digite o nome do Produto: ");
        dados = scan.nextLine();
        System.out.println("Digite a Marca do Produto: ");
        dados += "|" + scan.nextLine();
        System.out.println("Digite a Quantidade: ");
        dados += "|" + scan.nextInt();
        produto.add(dados);
            System.out.println("Produto Cadastrado com SUCESSO!");
        }
    public static void listarTodosProdutos(List<String> produto){
        System.out.println("Lista de Produtos");

        if (produto.isEmpty()){
            System.out.println("Nenhum Produto Encontrado!");
        } else {
            for (int i = 0; i< produto.size(); i++){
                System.out.println("Código: " + (i+1) + "." + produto.get(i).toString());
            }
        }
    }

    private static conection conectar() throws SQLException{
        return DriverManager.getconection(DB_URL,DB_USUARIO,DB_SENHA)

    }



}