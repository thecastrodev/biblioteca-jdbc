package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.ReservaDao;
import model.entities.Reserva;

public class ReservaTest {

	public void init() {
		ReservaDao reservaDao = DaoFactory.createReservaDao();
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== TEST 1: reserva findAllByCPF ===");
		List<Reserva> list = reservaDao.findAllByCPF("12345678901");
		for (Reserva obj : list) {
			System.out.println(""
					+ "ISBN: " + obj.getLivro().getISBN()
					+ "\nCPF: " + obj.getUsuario().getCPF()
					+ "\nData de Reserva: " + obj.getDataDeReserva()
					+ "\n----------------------------");
		}
		
		System.out.println("\n\n=== TEST 2: reserva findAll ===");
		list = reservaDao.findAll();
		for (Reserva obj : list) {
			System.out.println(""
					+ "ISBN: " + obj.getLivro().getISBN()
					+ "\nCPF: " + obj.getUsuario().getCPF()
					+ "\nData de Reserva: " + obj.getDataDeReserva()
					+ "\n----------------------------");
		}
		
		scanner.close();
		DB.closeConnection();
	}

}
