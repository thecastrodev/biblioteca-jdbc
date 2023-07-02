package model.entities;

import java.util.ArrayList;

public class Aluno extends Usuario {
	private static final long serialVersionUID = 1L;
	
	private String mat;
	private Curso curso;
	
	public Aluno() {}

	public Aluno(String CPF, String endereco, String nome, String senha, ArrayList<String> listaTelefones, String mat, Curso curso) {
		super(CPF, endereco, nome, senha, listaTelefones);
		this.mat = mat;
		this.curso = curso;
	}

	public String getMat() {
		return mat;
	}

	public void setMat(String mat) {
		this.mat = mat;
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
				+ "Aluno [mat=" + mat + ", curso=" + curso + "]";
	}
	
}
