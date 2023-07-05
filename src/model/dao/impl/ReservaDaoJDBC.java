package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.ReservaDao;
import model.entities.Aluno;
import model.entities.Funcionario;
import model.entities.Livro;
import model.entities.Professor;
import model.entities.Reserva;
import model.entities.Usuario;

public class ReservaDaoJDBC implements ReservaDao {

	private Connection conn;

	public ReservaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Reserva obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Reserva obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String CPF, String titulo, Date data) {
		// TODO Auto-generated method stub

	}

	@Override
	public Reserva find(String CPF, String titulo, Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> findAllByCPF(String CPF) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM Reserva WHERE CPF = ?");
			st.setString(1, CPF);
			rs = st.executeQuery();

			List<Reserva> list = new ArrayList<>();
			Map<String, Livro> mapLivro = new HashMap<>();
			Map<String, Usuario> mapUsuario = new HashMap<>();

			while (rs.next()) {

				Livro livro = mapLivro.get(rs.getString("ISBN"));
				Usuario usuario = mapUsuario.get(rs.getString("CPF"));

				if (livro == null) {
					livro = instantiateLivro(rs);
					mapLivro.put(rs.getString("ISBN"), livro);
				}

				if (usuario == null) {
					if(getTipo(rs.getString("CPF")).equals("Aluno")) {
						usuario = instantiateAluno(rs);
						mapUsuario.put(rs.getString("CPF"), usuario);	
					}

					if(getTipo(rs.getString("CPF")).equals("Professor")) {
						usuario = instantiateProfessor(rs);
						mapUsuario.put(rs.getString("CPF"), usuario);	
					}
					
					if(getTipo(rs.getString("CPF")).equals("Funcionario")) {
						usuario = instantiateFuncionario(rs);
						mapUsuario.put(rs.getString("CPF"), usuario);	
					}
				}
				
				Reserva obj = instantiateReserva(rs, usuario, livro, rs.getDate(3));
				list.add(obj);
			}

			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Reserva> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM Reserva");
			rs = st.executeQuery();

			List<Reserva> list = new ArrayList<>();
			Map<String, Livro> mapLivro = new HashMap<>();
			Map<String, Usuario> mapUsuario = new HashMap<>();

			while (rs.next()) {

				Livro livro = mapLivro.get(rs.getString("ISBN"));
				Usuario usuario = mapUsuario.get(rs.getString("CPF"));

				if (livro == null) {
					livro = instantiateLivro(rs);
					mapLivro.put(rs.getString("ISBN"), livro);
				}

				if (usuario == null) {
					if(getTipo(rs.getString("CPF")).equals("Aluno")) {
						usuario = instantiateAluno(rs);
						mapUsuario.put(rs.getString("CPF"), usuario);	
					}

					if(getTipo(rs.getString("CPF")).equals("Professor")) {
						usuario = instantiateProfessor(rs);
						mapUsuario.put(rs.getString("CPF"), usuario);	
					}
					
					if(getTipo(rs.getString("CPF")).equals("Funcionario")) {
						usuario = instantiateFuncionario(rs);
						mapUsuario.put(rs.getString("CPF"), usuario);	
					}
				}
				
				Reserva obj = instantiateReserva(rs, usuario, livro, rs.getDate(3));
				list.add(obj);
			}

			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	String getTipo(String CPF) {
		Connection conn = DB.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			List<String> list = new ArrayList<>();
			list.add("Aluno".trim());
			list.add("Professor".trim());
			list.add("Funcionario".trim());

			for (String tipo : list) {
				st = conn.prepareStatement("SELECT * FROM "+tipo.toString()+" NATURAL JOIN Usuario WHERE CPF = ?");
				st.setString(1, CPF);
				rs = st.executeQuery();
				
				int size = 0;
				while (rs.next()) {
					size++;
				}

				if (size > 0) {
					return tipo;
				}
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Professor instantiateProfessor(ResultSet rs) throws SQLException {
		Professor prof = new Professor();
		prof.setCPF(rs.getString("CPF"));
		return prof;
	}

	private Funcionario instantiateFuncionario(ResultSet rs) throws SQLException {
		Funcionario func = new Funcionario();
		func.setCPF(rs.getString("CPF"));
		return func;
	}

	private Aluno instantiateAluno(ResultSet rs) throws SQLException {
		Aluno aluno = new Aluno();
		aluno.setCPF(rs.getString("CPF"));
		return aluno;
	}

	private Livro instantiateLivro(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
		livro.setISBN(rs.getString("ISBN"));
		return livro;
	}

	private Reserva instantiateReserva(ResultSet rs, Usuario usuario, Livro livro, Date dataDeReserva)
			throws SQLException {
		Reserva reserva = new Reserva();
		reserva.setUsuario(usuario);
		reserva.setLivro(livro);
		reserva.setDataDeReserva(dataDeReserva);
		return reserva;
	}

}
