package dao.pessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pessoa;

public class PessoaDao extends dao.AbstractDao implements dao.interfaces.IPessoaDao {

	@Override
	public ResultSet buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("select from Pessoas where nome like ?");
		ResultSet rs;
		try {
			stmt.setString(1, "%"+nome+"%");
			rs = stmt.executeQuery();
			rs.close();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean incluirPessoa(String nome, String usuario, String senha) throws SQLException {
		PreparedStatement stmt = retornaPreparedStatement("insert into Pessoa (usuario, senha, nome) values (?,?,?)");
		stmt.setString(1, usuario);
		stmt.setString(2, senha);
		stmt.setString(3, nome);
		try{
			stmt.execute();
			stmt.close();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	

	@Override
	public boolean alterarPessoa(Pessoa pessoa) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("update Pessoa set nome = ?, usuario = ?, senha = ? where id = ?");
		try {
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getUsuario());
			stmt.setString(3, pessoa.getSenha());
			stmt.setInt(4, pessoa.getId());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean seguirPessoa(int idPessoa, int idSeguidor) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("insert into follows (idPessoa, idSeguidor) values (?,?)");
		try {
			stmt.setInt(1, idPessoa);
			stmt.setInt(2, idSeguidor);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ResultSet retornaSeguindo(int idSeguidor) {
		PreparedStatement stmt = retornaPreparedStatement("select from follows where idSeguidor = ?");
		ResultSet rs;
		try {
			stmt.setInt(1, idSeguidor);
			rs = stmt.executeQuery();
			rs.close();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ResultSet retornaSeguidores(int idPessoa) {
		PreparedStatement stmt = retornaPreparedStatement("select from follows where idPessoa = ?");
		ResultSet rs;
		try {
			stmt.setInt(1, idPessoa);
			rs = stmt.executeQuery();
			rs.close();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ResultSet retornaPessoaById(int idPessoa) {
		// TODO Auto-generated method stub
		
		PreparedStatement stmt = retornaPreparedStatement("Select from Pessoa where idPessoa = ?");
		ResultSet rs;
		try {
			stmt.setInt(1, idPessoa);
			rs = stmt.executeQuery();
			rs.close();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
