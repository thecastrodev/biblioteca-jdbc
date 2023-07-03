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
import model.dao.CursoDao;
import model.entities.Curso;

public class CursoDaoJDBC implements CursoDao {
	
	private Connection conn;
	
	public CursoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Curso obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Curso (cod_curso, nome_curso) VALUES (?, ?)"
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
	public void update(Curso obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Curso "
					+ "SET nome_curso = ? "
					+ "WHERE cod_curso = ?", Statement.RETURN_GENERATED_KEYS);
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
					"DELETE FROM Curso WHERE cod_curso = ?");
			st.setString(1, codigo);
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);;
		}
	}

	@Override
	public Curso findByCode(String codigo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Curso.* FROM Curso WHERE Curso.cod_curso = ?");
			st.setString(1, codigo);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Curso curso = instantiateCurso(rs);
				return curso;
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
	public List<Curso> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Curso.* FROM Curso");
			rs = st.executeQuery();
			
			Curso curso;
			List<Curso> list = new ArrayList<>();
			while (rs.next()) {
				curso = instantiateCurso(rs);
				list.add(curso);
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
	
	private Curso instantiateCurso(ResultSet rs) throws SQLException {
		Curso curso = new Curso();
		curso.setCodigo(rs.getString("cod_curso"));
		curso.setNome(rs.getString("nome_curso"));
		return curso;
	}

}
