package dao.twitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.pessoa.PessoaDao;
import model.Twitter;

public class TwitterDao extends dao.AbstractDao implements dao.interfaces.ITwitterDao {

	public TwitterDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public TwitterDao() {

	}

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
	public List listarTwitterPessoa(int idPessoa) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("select from twitter where idPessoa = ?");
		ResultSet rs;
		try {
			List<Twitter> lista = new ArrayList<Twitter>();
			stmt.setInt(1, idPessoa);
			rs = stmt.executeQuery();
			rs.close();
			while (rs.next()) {
				Twitter tw = new Twitter(rs.getInt("idPessoa"), rs.getString("mensagem"), rs.getDate("dataTwitter"),
						rs.getInt("idTwitter"));
				lista.add(tw);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List listarTwitterSeguindo(int idPessoa) {
		// TODO Auto-generated method stub
		ResultSet rs;
		PreparedStatement stmt = retornaPreparedStatement(
				"select twitter.idTwitter, twitter.mensagem, twitter.idPessoa, twitter.dataTwitter from twitter, follows where follows.idSeguidor = ? and twitter.idPessoa = follows.idPessoa order by twitter.dataTwitter asc");
		try {
			List<Twitter> lista = new ArrayList<Twitter>();
			stmt.setInt(1, idPessoa);
			rs = stmt.executeQuery();
			PessoaDao pdao = new PessoaDao(super.conn);
			while (rs.next()) {
				Twitter tw = new Twitter(rs.getInt("idPessoa"), rs.getString("mensagem"), rs.getDate("dataTwitter"),
						rs.getInt("idTwitter"));
				tw.setPessoa(pdao.retornaPessoaById(tw.getIdPessoa()));
				lista.add(tw);
			}
			rs.close();
			if (lista.isEmpty()) {
				return null;
			} else {
				return lista;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List listarTudo() {
		// TODO Auto-generated method stub
		ResultSet rs;
		PreparedStatement stmt = retornaPreparedStatement(
				"SELECT * FROM `twitter` ORDER BY `twitter`.`dataTwitter` DESC");
		PessoaDao pdao = new PessoaDao(super.conn);
		try {
			List<Twitter> lista = new ArrayList<Twitter>();
			rs = stmt.executeQuery();
			while (rs.next()) {
				Twitter tw = new Twitter(rs.getInt("idPessoa"), rs.getString("mensagem"), rs.getDate("dataTwitter"),
						rs.getInt("idTwitter"));
				tw.setPessoa(pdao.retornaPessoaById(tw.getIdPessoa()));
				lista.add(tw);
			}
			rs.close();

			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
