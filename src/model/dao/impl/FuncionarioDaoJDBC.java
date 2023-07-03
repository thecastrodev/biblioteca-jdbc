package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.FuncionarioDao;
import model.entities.Funcionario;

public class FuncionarioDaoJDBC implements FuncionarioDao {
	
private Connection conn;
	
	public FuncionarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Funcionario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Funcionario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByCPF(String CPF) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Funcionario findByCPF(String CPF) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ "SELECT f.mat, u.CPF, u.nome_usuario, u.endereco "
					+ "FROM Funcionario f "
					+ "NATURAL JOIN Usuario u "
					+ "WHERE u.CPF = ?");
			st.setString(1, CPF);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Funcionario func = instantiateFuncionario(rs);
				return func;
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
	public List<Funcionario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ "SELECT f.mat, u.CPF, u.nome_usuario, u.endereco "
					+ "FROM Funcionario f "
					+ "NATURAL JOIN Usuario u ");
			rs = st.executeQuery();
			
			Funcionario funcionario;
			List<Funcionario> list = new ArrayList<>();
			while (rs.next()) {
				funcionario = instantiateFuncionario(rs);
				list.add(funcionario);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	private Funcionario instantiateFuncionario(ResultSet rs) throws SQLException {
		Funcionario func = new Funcionario();
		func.setCPF(rs.getString("CPF"));
		func.setNome(rs.getString("nome_usuario"));
		func.setEndereco(rs.getString("endereco"));
		func.setMat(rs.getString("mat"));
		return func;
	}
	

}
