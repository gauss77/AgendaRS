package br.com.agendr.rn.entidades;

import br.com.agendr.td.ContatoTipoDT;

public class ContatoTipo extends Tipo {
	
	public static ContatoTipo[] getTipos(int codigoContato)
		throws Exception
	{
		return ContatoTipoDT.getTipos(codigoContato);
	}

	public void atualizar() {
		// TODO Implementar m�todo atualizar

	}

	public void excluir() {
		// TODO Implementar m�todo excluir

	}

	public void novo() {
		// TODO Implementar m�todo novo

	}

}
