package br.com.agenda.aplicação;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main (String[] args) {
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		
		//Contato contato = new Contato();
		//contato.setNome("Tâmara Victória ");
		//contato.setIdade(21);
		//contato.setDataCadastro(new Date());
		
		//-Salva os contatos no banco.
		//contatoDAO.save(contato);
		
		//-Atualiza os contatos no banco.
		//Contato c1 = new Contato();
		//c1.setNome("Arthur Sales Pereora Neto");
		//c1.setIdade(25);
		//c1.setDataCadastro(new Date());
		//c1.setId(1);//Posição no banco de dados.
		//contatoDAO.update(c1);
		
		//Deleta o contato pelo ID
		contatoDAO.deleteByID(2);
		
		
		//-Vizualiza os contatos do banco.
		for(Contato c : contatoDAO.getContatos()) {
			System.out.println("contato:  " + c.getNome());
		}
	}
		
}
