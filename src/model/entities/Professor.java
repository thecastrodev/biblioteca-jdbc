package model.entities;

import java.util.ArrayList;

public class Professor extends Usuario {
	private static final long serialVersionUID = 1L;
	
	private String matSiape;
	private String regimeDeTrabalho;
	private Curso curso;
	
	public Professor(String nome, String edereco, String CPF,String senha, String matSiape, String regimeDeTrabalho, Curso curso, ArrayList<String> listaTelefones) {
		super(CPF, edereco, nome, senha, listaTelefones);
		this.matSiape = matSiape;
		this.regimeDeTrabalho = regimeDeTrabalho;
		this.curso = curso;
	}

	public String getMatSiape() {
		return matSiape;
	}

	public void setMatSiape(String matSiape) {
		this.matSiape = matSiape;
	}

	public String getRegimeDeTrabalho() {
		return regimeDeTrabalho;
	}

	public void setRegimeDeTrabalho(String regimeDeTrabalho) {
		this.regimeDeTrabalho = regimeDeTrabalho;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Usuario [CPF=" + getCPF() + ", endereco=" + getEndereco() + ", nome=" + getNome() 
				+ ", senha=" + getSenha() + ", listaTelefones=" + getListaTelefones()
				+ "Professor [matSiape=" + matSiape + ", regimeDeTrabalho=" + regimeDeTrabalho + ", curso=" + curso + "]";
	}

}
