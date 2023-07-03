package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.ProfessorDao;
import model.entities.Aluno;
import model.entities.Curso;
import model.entities.Professor;

public class ProfessorDaoJDBC implements ProfessorDao {
	
private Connection conn;
	
	public ProfessorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Professor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Professor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByCPF(String CPF) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Professor findByCPF(String CPF) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ "SELECT p.mat_siape, u.CPF, u.nome_usuario, u.endereco, c.*, p.regime_de_trabalho "
					+ "FROM Professor p "
					+ "NATURAL JOIN Usuario u "
					+ "NATURAL JOIN Curso c "
					+ "WHERE u.CPF = ?");
			st.setString(1, CPF);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Curso curso = instantiateCurso(rs);
				Professor prof = instantiateProfessor(rs, curso);
				return prof;
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
	public List<Professor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ "SELECT p.mat_siape, u.CPF, u.nome_usuario, u.endereco, c.*, p.regime_de_trabalho "
					+ "FROM Professor p "
					+ "NATURAL JOIN Usuario u "
					+ "NATURAL JOIN Curso c ");
			rs = st.executeQuery();
			
			List<Professor> list = new ArrayList<>(); 
			Map<String, Curso> mapCurso = new HashMap<>();
			
			while (rs.next()) {
				
				Curso curso = mapCurso.get(rs.getString("cod_curso"));
				
				if (curso == null) {
					curso = instantiateCurso(rs);
					mapCurso.put(rs.getString("cod_curso"), curso);
				}
				
				Professor obj = instantiateProfessor(rs, curso);
				list.add(obj);
			}
			
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Professor instantiateProfessor(ResultSet rs, Curso curso) throws SQLException {
		Professor prof = new Professor();
		prof.setCPF(rs.getString("CPF"));
		prof.setNome(rs.getString("nome_usuario"));
		prof.setEndereco(rs.getString("endereco"));
		prof.setMatSiape(rs.getString("mat_siape"));
		prof.setRegimeDeTrabalho(rs.getString("regime_de_trabalho"));
		prof.setCurso(curso);
		return prof;
	}
	
	private Curso instantiateCurso(ResultSet rs) throws SQLException {
		Curso curso = new Curso();
		curso.setCodigo(rs.getString("cod_curso"));
		curso.setNome(rs.getString("nome_curso"));
		return curso;
	}
}
