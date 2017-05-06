package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.ConnectionFactory;

public class AbstractDao {

	public PreparedStatement retornaPreparedStatement(String sql){
		/**Este método cria uma conexão e retorna PreparedStatement a partir de uma entrada String contendo os comandos SQL.
		 * @author Matheus Barbosa
		 * 
		 */
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			return stmt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
