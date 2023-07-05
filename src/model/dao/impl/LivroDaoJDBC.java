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
import model.dao.LivroDao;
import model.entities.Categoria;
import model.entities.Editora;
import model.entities.Livro;

public class LivroDaoJDBC implements LivroDao {
	
	private Connection conn;
	
	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Livro obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Livro obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByISBN(String ISBN) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Livro findByISBN(String ISBN) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ "SELECT Livro.*, e.nome_editora AS Editora, c.nome_categoria AS Categoria FROM Livro "
					+ "NATURAL JOIN Editora e "
					+ "NATURAL JOIN Categoria c "
					+ "WHERE Livro.ISBN = ?");
			st.setString(1, ISBN);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Editora editora = instantiateEditora(rs);
				Categoria categoria = instantiateCategoria(rs);
				Livro livro = instantiateLivro(rs, editora, categoria);
				return livro;
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
	public List<Livro> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT Livro.*, e.nome_editora AS Editora, c.nome_categoria AS Categoria "
					+ "FROM Livro "
					+ "NATURAL JOIN Editora e "
					+ "NATURAL JOIN Categoria c "
					+ "ORDER BY Livro.ISBN");
			rs = st.executeQuery();
			
			List<Livro> list = new ArrayList<>(); 
			Map<String, Editora> mapEditora = new HashMap<>();
			Map<String, Categoria> mapCategoria = new HashMap<>();
			
			while (rs.next()) {
				
				Editora editora = mapEditora.get(rs.getString("cod_editora"));
				Categoria categoria = mapCategoria.get(rs.getString("cod_categoria"));
				
				if (editora == null) {
					editora = instantiateEditora(rs);
					mapEditora.put(rs.getString("cod_editora"), editora);
				}
				
				if (categoria == null) {
					categoria = instantiateCategoria(rs);
					mapCategoria.put(rs.getString("cod_categoria"), categoria);
				}
				
				Livro obj = instantiateLivro(rs, editora, categoria);
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
	public List<Livro> findByCategoria(String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livro> findByEditora(String editora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livro> findByAutor(String editora) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Livro> findByAnoDeLancamento(String editora) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Livro instantiateLivro(ResultSet rs, Editora editora, Categoria categoria) throws SQLException {
		Livro livro = new Livro();
		livro.setISBN(rs.getString("ISBN"));
		livro.setTitulo(rs.getString("titulo"));
		livro.setAnoDeLancamento(rs.getString("ano_de_lancamento"));
		
		livro.setEditora(editora);
		livro.setCategoria(categoria);
		return livro;
	}
	
	private Editora instantiateEditora(ResultSet rs) throws SQLException {
		Editora editora = new Editora();
		editora.setCodigo(rs.getString("cod_editora"));
		editora.setNome(rs.getString("Editora"));
		return editora;
	}
	
	private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setCodigo(rs.getString("cod_categoria"));
		categoria.setNome(rs.getString("Categoria"));
		return categoria;
	}
}
