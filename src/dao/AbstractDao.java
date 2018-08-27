package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AbstractDao {

	protected Connection conn;
	public AbstractDao(Connection conn){
		this.conn = conn;
	}
	public AbstractDao(){
		
	}

	public PreparedStatement retornaPreparedStatement(String sql){
		/**Este método cria uma conexão e retorna PreparedStatement a partir de uma entrada String contendo os comandos SQL.
		 * @author Matheus Barbosa
		 * 
		 */
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			return stmt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Prepared Statement falhou: "+sql);
			e.printStackTrace();
		}
		return null;
	}
}
