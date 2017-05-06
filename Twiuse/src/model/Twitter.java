package model;

import java.util.Date;

public class Twitter {
	private int idPessoa;
	private String mensagem;
	private Date dataTwitter;
	/**
	 * @return the idPessoa
	 */
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
	
}
