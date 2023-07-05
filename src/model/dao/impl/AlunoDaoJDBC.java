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
import model.dao.AlunoDao;
import model.entities.Aluno;
import model.entities.Categoria;
import model.entities.Curso;
import model.entities.Editora;
import model.entities.Livro;

public class AlunoDaoJDBC implements AlunoDao {
	
	private Connection conn;
	
	public AlunoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Aluno obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Aluno obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByCPF(String CPF) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aluno findByCPF(String CPF) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ "SELECT p.mat_siape, u.CPF, u.nome_usuario, u.endereco, c.*, a.data_de_ingresso, a.data_de_conclusao_prevista "
					+ "FROM Aluno a "
					+ "NATURAL JOIN Usuario u "
					+ "NATURAL JOIN Curso c "
					+ "WHERE u.CPF = ?");
			st.setString(1, CPF);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Curso curso = instantiateCurso(rs);
				Aluno aluno = instantiateAluno(rs, curso);
				return aluno;
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
	public List<Aluno> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ "SELECT a.mat, u.CPF, u.nome_usuario, u.endereco, c.*, a.data_de_ingresso, a.data_de_conclusao_prevista "
					+ "FROM Aluno a "
					+ "NATURAL JOIN Usuario u "
					+ "NATURAL JOIN Curso c ");
			rs = st.executeQuery();
			
			List<Aluno> list = new ArrayList<>(); 
			Map<String, Curso> mapCurso = new HashMap<>();
			
			while (rs.next()) {
				
				Curso curso = mapCurso.get(rs.getString("cod_curso"));
				
				if (curso == null) {
					curso = instantiateCurso(rs);
					mapCurso.put(rs.getString("cod_curso"), curso);
				}
				
				Aluno obj = instantiateAluno(rs, curso);
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
	
	private Aluno instantiateAluno(ResultSet rs, Curso curso) throws SQLException {
		Aluno aluno = new Aluno();
		aluno.setCPF(rs.getString("CPF"));
		aluno.setNome(rs.getString("nome_usuario"));
		aluno.setEndereco(rs.getString("endereco"));
		aluno.setMat(rs.getString("mat"));
		aluno.setCurso(curso);
		return aluno;
	}
	
	private Curso instantiateCurso(ResultSet rs) throws SQLException {
		Curso curso = new Curso();
		curso.setCodigo(rs.getString("cod_curso"));
		curso.setNome(rs.getString("nome_curso"));
		return curso;
	}
}
