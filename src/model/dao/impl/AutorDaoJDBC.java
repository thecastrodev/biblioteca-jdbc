package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.AutorDao;
import model.entities.Autor;

public class AutorDaoJDBC implements AutorDao {
	
	private Connection conn;
	
	public AutorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Autor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Autor "
					+ "(email, nome_autor, nacionalidade)"
					+ "VALUES (?, ?, ?)"
					, Statement.RETURN_GENERATED_KEYS );
			st.setString(1, obj.getEmail());
			st.setString(2, obj.getNome());
			st.setString(3, obj.getNacionalidade());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					obj.setEmail(rs.getString("email"));
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
	public void update(Autor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Autor "
					+ "SET email = ?, nome_autor = ?, nacionalidade = ? "
					+ "WHERE email = ?", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getEmail());
			st.setString(2, obj.getNome());
			st.setString(3, obj.getNacionalidade());
			st.setString(4, obj.getEmail());
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);;
		}
	}
	

	@Override
	public void deleteByEmail(String email) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM Autor WHERE email = ?");
			st.setString(1, email);
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);;
		}
	}

	@Override
	public Autor findByEmail(String email) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Autor.* FROM Autor WHERE Autor.email = ?");
			st.setString(1, email);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Autor autor = instantiateAutor(rs);
				return autor;
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
	public List<Autor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Autor.* FROM Autor");
			rs = st.executeQuery();
			
			Autor autor;
			List<Autor> list = new ArrayList<>();
			while (rs.next()) {
				autor = instantiateAutor(rs);
				list.add(autor);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	private Autor instantiateAutor(ResultSet rs) throws SQLException {
		Autor autor = new Autor();
		autor.setEmail(rs.getString("email"));
		autor.setNome(rs.getString("nome_autor"));
		autor.setNacionalidade(rs.getString("nacionalidade"));
		return autor;
	}

}
