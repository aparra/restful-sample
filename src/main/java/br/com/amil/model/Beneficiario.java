package br.com.amil.model;

import java.io.Serializable;
import java.util.Date;

public class Beneficiario implements Serializable {

	private static final long serialVersionUID = 8388084447122036772L;
	
	private Long id;
	private String nome;
	private Long cpf;
	private Date dataNascimento;
	private String modalidade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
}
