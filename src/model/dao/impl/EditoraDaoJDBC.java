package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import db.DB;
import db.DbException;
import model.dao.EditoraDao;
import model.entities.Editora;

public class EditoraDaoJDBC implements EditoraDao {
	
	private Connection conn;
	
	public EditoraDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Editora obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Editora (cod_editora, nome_editora) VALUES (?, ?)"
					, Statement.RETURN_GENERATED_KEYS );
			String code = generateId();
			st.setString(1, code);
			st.setString(2, obj.getNome());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					obj.setCodigo(code);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Editora obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Editora "
					+ "SET nome_editora = ? "
					+ "WHERE cod_editora = ?", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getCodigo());
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);;
		}
	}

	@Override
	public void deleteByCode(String codigo) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM Editora WHERE cod_editora = ?");
			st.setString(1, codigo);
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);;
		}
	}

	@Override
	public Editora findByCode(String codigo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Editora.* FROM Editora WHERE Editora.cod_editora = ?");
			st.setString(1, codigo);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Editora editora = instantiateEditora(rs);
				return editora;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Editora> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Editora.* FROM Editora");
			rs = st.executeQuery();
			
			Editora editora;
			List<Editora> list = new ArrayList<>();
			while (rs.next()) {
				editora = instantiateEditora(rs);
				list.add(editora);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private String generateId() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String key = uuid.substring(0, 10).toUpperCase();
		return key;
	}
	
	private Editora instantiateEditora(ResultSet rs) throws SQLException {
		Editora editora = new Editora();
		editora.setCodigo(rs.getString("cod_editora"));
		editora.setNome(rs.getString("nome_editora"));
		return editora;
	}

}
