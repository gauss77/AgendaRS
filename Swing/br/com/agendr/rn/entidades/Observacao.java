package br.com.agendr.rn.entidades;

public class Observacao extends Entidade 
{
	private String descricao;

	public String getDescricao() {
		return descricao;
	} // fim do m�todo getDescricao
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	} // fim do m�todo setDescricao
	
	public String toString()
	{
		return getDescricao() + " (N�o classificada)";
	} // fim do m�todo
} // fim da classe observacao
