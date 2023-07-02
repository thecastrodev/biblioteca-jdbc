package model.entities;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String CPF;
	private String endereco;
	private String nome;
	private String senha;
	private ArrayList<String> listaTelefones;
	
	public Usuario() {}

	public Usuario(String CPF, String endereco, String nome, String senha, ArrayList<String> listaTelefones) {
		this.CPF = CPF;
		this.endereco = endereco;
		this.nome = nome;
		this.senha = senha;
		this.listaTelefones = listaTelefones;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<String> getListaTelefones() {
		return listaTelefones;
	}

	public void setListaTelefones(ArrayList<String> listaTelefones) {
		this.listaTelefones = listaTelefones;
	}

	@Override
	public String toString() {
		return "Usuario [CPF=" + CPF + ", endereco=" + endereco + ", nome=" + nome + ", senha=" + senha
				+ ", listaTelefones=" + listaTelefones + "]";
	}
	
}
