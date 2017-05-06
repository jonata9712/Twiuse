package dao.interfaces;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pessoa;
public interface IPessoaDao {
	public ResultSet buscarPorNome(String nome);
	public boolean incluirPessoa(String nome, String usuario, String senha) throws SQLException;
	public boolean alterarPessoa(Pessoa pessoa);
	public boolean seguirPessoa(int idSeguidor, int idPessoa);
	public ResultSet retornaPessoaById(int idPessoa);
	public ResultSet retornaSeguindo(int idPessoa);
	public ResultSet retornaSeguidores(int idPessoa);
}
