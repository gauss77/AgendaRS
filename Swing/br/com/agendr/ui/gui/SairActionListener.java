package br.com.agendr.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Classe que implementa o comportamento padr�o para sair
 * Chama a fun��o System.exit(0);
 * 
 * @author Sebasti�o Relson <sebastiaorelson@systecinfo.com.br>
 * 
 */

public class SairActionListener implements ActionListener {	
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
