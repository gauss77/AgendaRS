package br.com.agendr.rn.entidades;

public class Email extends Entidade{
	
	private String endereco;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	} // fim do m�todo setEndereco
	
	public String toString()
	{
		return getEndereco() + "(N�o Classificado)";
	}
} // fim da classe Email
