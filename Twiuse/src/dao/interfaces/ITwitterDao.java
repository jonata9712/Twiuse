package dao.interfaces;

import java.sql.ResultSet;

import model.Twitter;

public interface ITwitterDao {
	public boolean twittar(String mensagem, int idPessoa);
	public boolean alterarTwitter(Twitter twitter);
	public boolean excluirTwitter(int idTwitter);
	public ResultSet listarTwitterPessoa(int idPessoa);
	public ResultSet listarTwitterSeguindo(int idPessoa);
	
}
