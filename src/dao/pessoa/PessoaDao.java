package dao.pessoa;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;


import model.Pessoa;

public class PessoaDao extends dao.AbstractDao implements dao.interfaces.IPessoaDao {

	public PessoaDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public PessoaDao() {

	}

	@Override
	public List<Pessoa> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		List<Pessoa> lista = new ArrayList<Pessoa>();
		PreparedStatement stmt = retornaPreparedStatement("select * from Pessoas where nome like ?");
		ResultSet rs;
		Pessoa pessoa;
		try {
			stmt.setString(1, "%" + nome + "%");
			rs = stmt.executeQuery();
			rs.close();
			while (rs.next()) {
				pessoa = new Pessoa(rs.getString("usuario"), rs.getString("nome"));
				lista.add(pessoa);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean incluirPessoa(String nome, String usuario, String senha) throws SQLException {
		PreparedStatement stmt = retornaPreparedStatement("insert into Pessoa (usuario, senha, nome) values (?,?,?)");
		stmt.setString(1, usuario);
		stmt.setString(2, senha);
		stmt.setString(3, nome);
		try {
			stmt.execute();
			stmt.close();
			int novoId = retornaPessoaByUsuario(usuario).getId();
			seguirPessoa(novoId, novoId);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean alterarPessoa(Pessoa pessoa) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement(
				"update Pessoa set nome = ?, usuario = ?, senha = ? where id = ?");
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
	public List<Pessoa> retornaSeguindo(int idSeguidor) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		PreparedStatement stmt = retornaPreparedStatement("select * from follows where idSeguidor = ?");
		try {
			stmt.setInt(1, idSeguidor);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Pessoa p = retornaPessoaById(rs.getInt("idPessoa"));
				pessoas.add(p);
			}
			System.out.println("Tamanho da lista de pessoas: " + pessoas.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pessoas;

	}

	@Override
	public List<Pessoa> retornaSeguidores(int idPessoa) {
		PreparedStatement stmt = retornaPreparedStatement("select * from follows where idPessoa = ?");
		ResultSet rs;
		try {
			stmt.setInt(1, idPessoa);
			rs = stmt.executeQuery();
			rs.close();
			Pessoa pessoa;
			List<Pessoa> lista = new ArrayList<Pessoa>();
			while (rs.next()) {
				pessoa = new Pessoa(rs.getString("usuario"), rs.getString("nome"));
				lista.add(pessoa);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Pessoa retornaPessoaById(int idPessoa) {
		// TODO Auto-generated method stub

		PreparedStatement stmt = retornaPreparedStatement("Select * from pessoa where id = ?");
		ResultSet rs;
		try {
			stmt.setInt(1, idPessoa);
			rs = stmt.executeQuery();
			rs.next();
			Pessoa pessoa = new Pessoa(rs.getString("usuario"), rs.getString("nome"));
			pessoa.setId(rs.getInt("id"));
			rs.close();
			return pessoa;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pessoa loginUsuario(String usuario, String senha) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("select * from pessoa where usuario = ? and senha = ?");
		try {
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			Pessoa pessoa = null;
			if (rs.next()) {
				System.out.println("Usuário encontrado");
				pessoa = new Pessoa(rs.getString("usuario"), rs.getString("nome"));
				pessoa.setSenha(rs.getString("senha"));
				pessoa.setId(rs.getInt("id"));
			}
			return pessoa;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Pessoa> verificaExisteNomeUsuario(String usuario) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("select * from pessoa where usuario = ?");
		try {
			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();
			Pessoa pessoa;
			List<Pessoa> lista = new ArrayList<Pessoa>();
			while (rs.next()) {
				pessoa = new Pessoa(rs.getString("usuario"), rs.getString("nome"));
				lista.add(pessoa);
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Pessoa> listarTodasQuemSeguir(int idPessoa) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("select * from pessoa p where p.id in (select idSeguidor from follows where idSeguidor <> ? and idPessoa <> ?)");
		try {
			stmt.setInt(1, idPessoa);
			stmt.setInt(2, idPessoa);
			ResultSet rs = stmt.executeQuery();
			Pessoa pessoa;
			List<Pessoa> lista = new ArrayList<Pessoa>();
			while (rs.next()) {
				pessoa = new Pessoa(rs.getString("usuario"), rs.getString("nome"));
				pessoa.setId(rs.getInt("id"));
				if (!verificaSeguindo(rs.getInt("id"), idPessoa)) {
					lista.add(pessoa);
				}

			}
			rs.close();
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pessoa retornaPessoaByUsuario(String usuario) {
		PreparedStatement stmt = retornaPreparedStatement("Select * from Pessoa where usuario = ?");
		ResultSet rs;
		try {
			stmt.setString(1, usuario);
			rs = stmt.executeQuery();
			Pessoa pessoa = null;
			if (rs.next()) {
				pessoa = new Pessoa(rs.getString("usuario"), rs.getString("nome"));
				pessoa.setId(rs.getInt("id"));
			}
			rs.close();
			return pessoa;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean verificaSeguindo(int idPessoa, int idSeguidor) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement(
				"select * from follows where idPessoa = ? and idSeguidor = ?");
		try {
			stmt.setInt(1, idPessoa);
			stmt.setInt(2, idSeguidor);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				rs.close();
				return true;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deixarDeSeguir(int idPessoa, int idSeguidor) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = retornaPreparedStatement("delete from follows where idPessoa = ? and idSeguidor = ?");
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

	public void inserirImagem(FileItem item, int idPessoa) {

		PreparedStatement declaracao = retornaPreparedStatement(
				/*
				"update fotos_perfil set imagem = ? where id_pessoa = ? IF @@ROWCOUNT=0 INSERT INTO fotos_perfil (id_pessoa, imagem) VALUES(?, ?) "
				*/ 
				 "INSERT INTO fotos_perfil (imagem, id_pessoa) VALUES (?, ?)  ON DUPLICATE KEY UPDATE imagem = ?"
				 );

		try {

			declaracao.setBinaryStream(1, item.getInputStream(), (int) item.getSize());
			declaracao.setInt(2, idPessoa); // codigo 1
			declaracao.setBinaryStream(3, item.getInputStream(), (int) item.getSize());

			declaracao.executeUpdate();
			declaracao.close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public File resgataImagem(int idPessoa) {
		File image = null;
		Blob blob = null;
		InputStream bin = null;
		FileOutputStream bout = null;
		byte[] bbuf = new byte[1024];
		int bytesRead = 0;
		PreparedStatement stm = retornaPreparedStatement("select imagem from fotos_perfil where id_pessoa = ?");
		try {
			System.out.println("Recuperando foto");
			stm.setInt(1, idPessoa);
			ResultSet rset = stm.executeQuery();
			if(rset.next()) {
				blob = rset.getBlob("imagem");
				System.out.println("imagem "+blob.toString());
				bin = blob.getBinaryStream();
				bout = new FileOutputStream("imagem.jpg");
				while ((bytesRead = bin.read(bbuf)) != -1) {
					bout.write(bbuf, 0, bytesRead);
				}
			}else {
				return null;
			}
			
			rset.close();
		} catch (Exception e) {
			System.out.println("Operação falhou");
			e.printStackTrace();
		}
		
		image = new File("imagem.jpg");
		return image;
	}

}
