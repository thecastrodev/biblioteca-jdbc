package model.entities;

import java.util.ArrayList;

public class Funcionario extends Usuario {
	private static final long serialVersionUID = 1L;
	
	private String mat;
	
	public Funcionario(String nome, String CPF, String endereco, String mat, String senha, ArrayList<String> listaTelefones) {
		super(CPF, endereco, nome, senha, listaTelefones);
		this.mat = mat;
	}

	public String getMat() {
		return mat;
	}

	public void setMat(String mat) {
		this.mat = mat;
	}

	@Override
	public String toString() {
		return "Usuario [CPF=" + getCPF() + ", endereco=" + getEndereco() + ", nome=" + getNome() 
		+ ", senha=" + getSenha() + ", listaTelefones=" + getListaTelefones()
				+ "Funcionario [mat=" + mat + "]";
	}
}
