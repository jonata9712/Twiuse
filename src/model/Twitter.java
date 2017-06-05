package model;

import java.util.Date;

public class Twitter {
	private int idTwitter;
	private int idPessoa;
	private String mensagem;
	private Date dataTwitter;
	private Pessoa pessoa;
	/**
	 * @return the idPessoa
	 */
	public Twitter(int idPessoa, String mensagem, Date dataTwitter, int idTwitter){
		this.idPessoa = idPessoa;
		this.mensagem = mensagem;
		this.dataTwitter = dataTwitter;
		this.idTwitter = idTwitter;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}
	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	/**
	 * @return the dataTwitter
	 */
	public Date getDataTwitter() {
		return dataTwitter;
	}
	/**
	 * @param dataTwitter the dataTwitter to set
	 */
	public void setDataTwitter(Date dataTwitter) {
		this.dataTwitter = dataTwitter;
	}
	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}
	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
