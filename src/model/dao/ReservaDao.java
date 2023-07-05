package model.dao;

import java.util.Date;
import java.util.List;

import model.entities.Reserva;

public interface ReservaDao {
	void insert(Reserva obj);
	void update(Reserva obj);
	void delete(String CPF, String titulo, Date data);
	Reserva find(String CPF, String titulo, Date data);
	List<Reserva> findAllByCPF(String CPF);
	List<Reserva> findAll();
}
