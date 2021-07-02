package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
	
	//Nome do Usuario
	private static final String USERNAME = "root";

	//Senha do banco
	private static final String PASSWORD = "";
	
	//Caminho do Banco de dados e porta
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	//Conexao com o banco de dados
	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
	
		return connection;
	}

	public static void main(String[] args) throws Exception {
		
		//Recuperar Conexão existente.
		Connection con = createConnectionToMySQL();
		
		//Testar se a conexão é nula
		if (con!=null) {
			System.out.println("Deu Certo");
			con.close();
		}
		
	}




}
