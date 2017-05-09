package dao.twitter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Twitter;

public class TwitterDao extends dao.AbstractDao implements dao.interfaces.ITwitterDao {

	@Override
	public boolean twittar(String mensagem, int idPessoa) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("insert into twitter (mensagem, idPessoa) values (?, ?)");
		try {
			stmt.setString(1, mensagem);
			stmt.setInt(2, idPessoa);
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
	public boolean alterarTwitter(Twitter twitter) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("update from twitter set mensagem = ? where idPessoa = ?");
		try {
			stmt.setString(1, twitter.getMensagem());
			stmt.setInt(2, twitter.getIdPessoa());
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
	public boolean excluirTwitter(int idTwitter) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("delete from twitter where idTwitter = ?");
		try {
			stmt.setInt(1, idTwitter);
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
	public ResultSet listarTwitterPessoa(int idPessoa) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("select from twitter where idPessoa = ?");
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

	@Override
	public ResultSet listarTwitterSeguindo(int idPessoa) {
		// TODO Auto-generated method stub
		ResultSet rs;
		PreparedStatement stmt = retornaPreparedStatement("select twitter.idTwitter, twitter.mensagem, twitter.idPessoa, twitter.dataTwitter from twitter, follows where follows.idSeguidor = ? and twitter.idPessoa = follows.idPessoa order by twitter.dataTwitter asc");
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
