package dao.interfaces;
import java.sql.SQLException;
import java.util.List;

import model.Pessoa;
public interface IPessoaDao {
	public List<Pessoa> buscarPorNome(String nome);
	public boolean incluirPessoa(String nome, String usuario, String senha) throws SQLException;
	public boolean alterarPessoa(Pessoa pessoa);
	public boolean seguirPessoa(int idSeguidor, int idPessoa);
	public Pessoa retornaPessoaById(int idPessoa);
	public List<Pessoa> retornaSeguindo(int idPessoa);
	public List<Pessoa> retornaSeguidores(int idPessoa);
	public Pessoa loginUsuario(String usuario, String senha);
	public List<Pessoa> verificaExisteNomeUsuario(String usuario);
}
