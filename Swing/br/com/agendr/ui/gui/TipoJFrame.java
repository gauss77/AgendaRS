package br.com.agendr.ui.gui;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.com.agendr.rn.entidades.Tipo;
import br.com.agendr.rn.entidades.TipoImpl;

/**
 * Classe para o tratamento de tipo das entidades que implementa a classe
 * abstrata Tipo
 * 
 * @author Sebasti�o Relson
 * 
 * @since quinta-feira, 17 de Maio de 2007
 * 
 * @see br.com.agendr.rn.entidades.Tipo 
 * 
 */
public class TipoJFrame extends JFrame {

	public static final long serialVersionUID = 1;

	// Bot�es
	public AdicionarJButton adicionarJButton = new AdicionarJButton();
	public EditarJButton editarJButton = new EditarJButton();
	public ExcluirJButton excluirJButton = new ExcluirJButton();
	public SairJButton sairJButton = new SairJButton();
	
	// Esses bot�es servir�o apenas para o layout
	public SairJButton hideJButton[] = new SairJButton[5];
	
	public JList jList;
	
	private Tipo tipos[];
	
	private String nomeTipo;

	public JPanel jPanel = new JPanel(new BorderLayout(15,15));

	// Caixa para comportar os bot�es de Adicionar, Editar e Excluir
	public JPanel direitaPainel = new JPanel(new GridLayout(9,1));

	// Construtores
	public TipoJFrame(String tipo) {
		super("Tipo " + tipo);
		
		this.nomeTipo = tipo;
		
		// popula o jList
		carregaLista();
		
		// insere o JList no meio delegado por um JScrollPane.
		JScrollPane jScrollPane = new JScrollPane(jList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
			);	
		
		add(jScrollPane, BorderLayout.CENTER);

		// configurando os bot�es do lado esquerdo		
		// bot�o adicionar
		adicionarJButton.addActionListener(new AdicionarJButtonActionListener());
		direitaPainel.add(adicionarJButton);
		
		// bot�o editar
		editarJButton.addActionListener(new EditarJButtonActionListener());
		direitaPainel.add(editarJButton);
		
		// bot�o excluir
		excluirJButton.addActionListener(new ExcluirJButtonActionListener());
		direitaPainel.add(excluirJButton);
		
		/* 
		 * Adicionando os bot�es que ficar�o ocultos servir�o para deixar proporcional
		 * o layout da grade
		 */
		for (int i= 0; i < hideJButton.length; ++i) {
			hideJButton[i] = new SairJButton();
			hideJButton[i].setVisible(false);
			direitaPainel.add(hideJButton[i]);
		}
		
		// adicionando ent�o o bot�o sair
		direitaPainel.add(sairJButton);
		add(direitaPainel, BorderLayout.EAST);
		
		sairJButton.addActionListener(new SairJButtonActionListener());
		
		// configurando as propriedades b�sicas para a apresenta��o do JFrame
		setSize(300, 270);
		setResizable(false); // n�o permite redimencionar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("editar16.png")).getImage());
		setVisible(true);
	} // fim do construtor com um argumento

	/**
	 * M�todo para Teste
	 */
	public static void main(String args[]) {
		new TipoJFrame("email");
	} // fim do m�todo main
	
	/**
	 * Carrega a lista de tipos, buscando-o do banco da fonte de dados
	 * @author Sebasti�o Relson
	 */
	public void carregaLista() {
		// populando o array de tipos
		try
		{
			tipos = TipoImpl.getTipos("tipos" + nomeTipo);
			
			if(jList == null)			
				jList = new JList(tipos);
			else
				jList.setListData(tipos);
		} // fim do try
		catch(Exception exception)
		{
			JOptionPane.showMessageDialog(null, "Erro ao tentar carregar os tipos " +
					nomeTipo + "\n" + exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			jList = new JList();
			this.dispose();
			
		} // fim do catch	
	} // fim do m�todo carrega lista
	

	// Classes Internas para os Events Listener dos Componentes
	/**
	 * ActionListener para o tratamento das a��es do bot�o sair
	 */
	private class SairJButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			try
			{
				TipoJFrame.this.dispose();
			}// fim do try
			catch (Exception exception)
			{
				JOptionPane.showMessageDialog(TipoJFrame.this, "Erro ao tentar excluir o tipo\n" +
						exception.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
			} // fim do catch
			catch (Throwable throwable)
			{
			JOptionPane.showMessageDialog(TipoJFrame.this, "Erro ao tentar excluir o tipo\n" +
					throwable.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);			
				
			}
		} // fim do m�todo actionPerformed
	} // fim da classe interna SairJButtonActionListener
	
	/**
	 * ActionListener para tratamento do bot�o excluir 
	 */
	private class ExcluirJButtonActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			try
			{	
				Object selecaoTipos[]= jList.getSelectedValues();
				
				int i = selecaoTipos.length;
				
				// Quando nenhum item est� selecionado
				if (i == 0)
					throw new Exception("Nenhum Item Selecionado");
				
				for(Object object:selecaoTipos)
					((Tipo)object).excluir();
				
				carregaLista();
				
			} // fim do try
			catch (Exception exception)
			{
				JOptionPane.showMessageDialog(TipoJFrame.this, "Erro ao tentar excluir o tipo\n" +
						exception.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
			} // fim do catch
		} // fim do m�todo actionPerformed
	} // fim da classe interna ExcluirJButtonActionListener
	
	private class AdicionarJButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			Tipo novoTipo = new TipoImpl("tipos" + nomeTipo);
			String descricao = JOptionPane.showInputDialog(
					TipoJFrame.this, 
					"Descri��o:",					
					"Novo Tipo",					
					JOptionPane.QUESTION_MESSAGE
					);
			
			try
			{
				// ao clicar em cancel ou fechar a jenela.
				if (descricao == null)
					return;
				
				if (descricao.trim().isEmpty())
					throw new Exception("Descri��o em branco");
				
				novoTipo.setDescricao(descricao.trim());
				novoTipo.novo();
				
				carregaLista();
			} // fim do try
			catch (Exception exception)
			{
				JOptionPane.showMessageDialog(TipoJFrame.this, "Erro ao tentar inserir um novo tipo\n" +
						exception.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
			} // fim do catch
		} // fim do m�todo actionPerformed
	} // fim da classe AdicionarJButtonActionListener 
	
	private class EditarJButtonActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			int i = jList.getSelectedIndex();
			
			try
			{
				if( i == -1)
					throw new Exception("Nenhum item selecionado");
				
				Tipo selecionadoTipo = tipos[i];
				
				String novaDescricao = JOptionPane.showInputDialog(
						TipoJFrame.this,
						"Nova Descri��o para o tipo:",						
						selecionadoTipo.getDescricao()						
					);
				
				// atualiza o objeto se a descri��o for diferente
				if (!novaDescricao.trim().equals(selecionadoTipo.getDescricao().trim()))
				{
					selecionadoTipo.setDescricao(novaDescricao);
					selecionadoTipo.atualizar();
					carregaLista();
				}				
			} // fim do try
			catch (Exception exception)
			{
				JOptionPane.showMessageDialog(TipoJFrame.this, "Erro ao tentar alterar o tipo\n" +
						exception.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
			} // fim do catch
		} // fim do m�todo actionPerformed		
	} // fim da classse EditarJButtonActionListener
	
} // fim da classe TipoJFrame