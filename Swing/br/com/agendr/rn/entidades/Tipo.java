package br.com.agendr.rn.entidades;

import br.com.agendr.td.Crud;

/**
 * Classe Abstrata para defini��o de Tipo
 * 
 * @author Sebasti�o Relson
 * 
 * @since Quinta-feira, 17 de Maio de 2007
 *
 */
public abstract class Tipo extends Entidade implements Crud{
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString()
	{
		return getDescricao();
	}
}
