package dao.interfaces;

import java.sql.ResultSet;
import java.util.List;

import model.Twitter;

public interface ITwitterDao{
	public boolean twittar(String mensagem, int idPessoa);
	public boolean alterarTwitter(Twitter twitter);
	public boolean excluirTwitter(int idTwitter);
	public List<Twitter> listarTwitterPessoa(int idPessoa);
	public List<Twitter> listarTwitterSeguindo(int idPessoa);
	public List<Twitter> listarTudo();
}
