package br.com.agendr.rn.entidades;

public class Telefone extends Entidade {
	
	private String numero;

	/**
	 * @return um numero de telefone Formatado (XX) XXXX-XXXX
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero string contendo o n�mero do telefone
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	} // fim do m�todo setNumero
	
	public String toString()
	{
		return this.numero + " (N�o classificado)";
	}
	
} // fim da classe Telefone
