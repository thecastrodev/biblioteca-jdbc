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
import model.dao.CategoriaDao;
import model.entities.Categoria;

public class CategoriaDaoJDBC implements CategoriaDao {
	
	private Connection conn;
	
	public CategoriaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Categoria obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Categoria (cod_categoria, nome_categoria) VALUES (?, ?)"
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
	public void update(Categoria obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Categoria "
					+ "SET nome_categoria = ? "
					+ "WHERE cod_categoria = ?", Statement.RETURN_GENERATED_KEYS);
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
					"DELETE FROM Categoria WHERE cod_categoria = ?");
			st.setString(1, codigo);
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);;
		}
	}

	@Override
	public Categoria findByCode(String codigo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Categoria.* FROM Categoria WHERE Categoria.cod_categoria = ?");
			st.setString(1, codigo);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Categoria categoria = instantiateCategoria(rs);
				return categoria;
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
	public List<Categoria> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Categoria.* FROM Categoria");
			rs = st.executeQuery();
			
			Categoria categoria;
			List<Categoria> list = new ArrayList<>();
			while (rs.next()) {
				categoria = instantiateCategoria(rs);
				list.add(categoria);
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
	
	private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setCodigo(rs.getString("cod_categoria"));
		categoria.setNome(rs.getString("nome_categoria"));
		return categoria;
	}

}
