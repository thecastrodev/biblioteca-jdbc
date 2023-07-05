package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Livro livro;
	private Usuario usuario;
	private Date dataDeReserva;
	
	public Reserva() {}
	
	public Reserva(Livro livro, Usuario usuario) {
		this.livro = livro;
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataDeReserva() {
		return dataDeReserva;
	}

	public void setDataDeReserva(Date dataDeReserva) {
		this.dataDeReserva = dataDeReserva;
	}

	@Override
	public String toString() {
		return "Reserva [livro=" + livro + ", usuario=" + usuario + ", dataDeReserva=" + dataDeReserva + "]";
	}
}
