package dao.twitter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.pessoa.PessoaDao;
import model.Pessoa;
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
	public List<Twitter> listarTwitterPessoa(int idPessoa) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("select * from twitter where idPessoa = ?");
		ResultSet rs;
		try {
			List<Twitter> lista = new ArrayList<Twitter>();
			stmt.setInt(1, idPessoa);
			rs = stmt.executeQuery();
			PessoaDao pdao = new PessoaDao(super.conn);

			while (rs.next()) {
				Twitter tw = new Twitter(rs.getInt("idPessoa"), rs.getString("mensagem"), rs.getDate("dataTwitter"),
						rs.getInt("idTwitter"));
				tw.setPessoa(pdao.retornaPessoaById(idPessoa));
				lista.add(tw);
			}
			rs.close();
			if (!lista.isEmpty()) {

				return lista;

			} else {
				return null;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Twitter> listarTwitterSeguindo(int idPessoa) {
		// TODO Auto-generated method stub
		PessoaDao pdao = new PessoaDao(super.conn);
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		List<Twitter> lista = new ArrayList<Twitter>();
		pessoas = pdao.retornaSeguindo(idPessoa);
		System.out.println("Tamanho lista pessoas: " + pessoas.size());
		try {
			if (!pessoas.isEmpty()) {
				String sql = "select * from twitter where idPessoa = ";

				for (Pessoa pessoa : pessoas) {
					if (pessoas.indexOf(pessoa) == 0) {
						sql += Integer.toString(pessoa.getId());
					} else {
						sql += " or idPessoa = " + Integer.toString(pessoa.getId());
					}
				}
				sql += " order by dataTwitter desc";
				System.out.println(sql);

				PreparedStatement stmt = retornaPreparedStatement(sql);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Twitter tw = new Twitter(rs.getInt("idPessoa"), rs.getString("mensagem"), rs.getDate("dataTwitter"),
							rs.getInt("idTwitter"));
					tw.setPessoa(pdao.retornaPessoaById(tw.getIdPessoa()));
					lista.add(tw);
//						System.out.println("mensagem: " + tw.getMensagem());
				}
				rs.close();

				System.out.println("Tamanho lista Twitter: " + lista.size());
			}
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
	public List<Twitter> listarTudo() {
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

}
