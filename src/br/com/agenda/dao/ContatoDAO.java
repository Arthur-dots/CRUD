package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	/*
	 * CRUD
	 * c: Create (save)
	 * r: Read   (List<Contato>)
	 * u: Update (update)
	 * d: Delete (deleById)
	 */
	
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) Values(?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			//Criamos uma pstm para executar a query
			pstm = (PreparedStatement) conn.prepareCall(sql);
			//Adiciona os Valores esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//Executar a query
			pstm.execute();
			System.out.println("Deu BOM!!");
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		
		//Fecha as conexões
		try {
			if(pstm!=null) {pstm.close();}
			if(conn!=null) {conn.close();}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

    }

	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco.
		ResultSet rst = null;
		
		try {
			conn= ConnectionFactory.createConnectionToMySQL();
			
			pstm= (PreparedStatement) conn.prepareStatement(sql);
			
			rst= pstm.executeQuery();
			
			while (rst.next()) {
				
				Contato contato = new Contato();
				
				//Recuperação de ID
				contato.setId(rst.getInt("id"));
				//Recuperação de Nome
				contato.setNome(rst.getString("nome"));
				//Recuperação de idade
				contato.setIdade(rst.getInt("idade"));
				//Recuperação Data de Cadastro
				contato.setDataCadastro(rst.getDate("dataCadastro"));
				
				contatos.add(contato);
				
			     }
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
				if (rst!=null) {rst.close();}
				if (pstm!=null){pstm.close();}
				if (conn!=null){conn.close();}
				}
				catch (Exception e) {e.printStackTrace();}
}
			return contatos;		

}

	public void update(Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ?" +
				"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Cria a conexão com o banco.
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar a pstm para executar a query.
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date (contato.getDataCadastro().getTime()));
			
			//Qual o id a ser atualizado?
			pstm.setInt(4, contato.getId());
			
			//Execução de query
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {pstm.close();}
				if(conn!=null) {conn.close();}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByID(int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm!=null) {pstm.close();}
				if (conn!=null) {conn.close();}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
	