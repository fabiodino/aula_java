package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsereLivro {

	private static Scanner entrada;

	public static void main(String[] args) {

		try {
			// Abre conexao
			System.out.println("Abrindo conexao...");
			Connection conexao = FabricaDeConexao.criaConexao();

			// Recebe input do usuario
			entrada = new Scanner(System.in);

			System.out.println("Digite o nome do livro: ");
			String titulo = entrada.nextLine();

			System.out.println("Digite o valor do livro: ");
			double preco = entrada.nextDouble();

			System.out.println("Digite o id da editora: ");
			int editora_id = entrada.nextInt();

			// Cria string SQL: Insere registro no BD (sanitize "?")
			String textoDoComando = "INSERT INTO Livro (titulo, preco, editora_id)" + "VALUES (?, ?, ?)";

			// Prepara comando SQL: Conexao JDBC
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);

			// Recebe indice do parametro e o valor: (sanitize - "limpa" valores
			// enviados (sql injection))
			comando.setString(1, titulo);
			comando.setDouble(2, preco);
			comando.setInt(3, editora_id);

			// Executa comando SQL: Conexao JDBC
			System.out.println("Executando comando...");
			comando.execute();

			// Fecha conexao
			System.out.println("Fechando conexao...");
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
