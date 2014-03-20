package br.com.agendr.ui.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import br.com.agendr.rn.entidades.Contato;
import br.com.agendr.rn.entidades.ContatoTipo;
import br.com.agendr.rn.entidades.Tipo;
import br.com.agendr.rn.entidades.TipoImpl;


public class ContatoCadastroJFrame extends JFrame{
	
	public static final long serialVersionUID = 1;
	
	private JPanel jpanel = new JPanel(new BorderLayout());
	
	private JPanel   centralJPanel = new JPanel(new GridLayout(5,1));
	
	private AplicarJButton aplicarJButton = new AplicarJButton();
	
	public JPanel botoesJPanel = new JPanel();
	
	public JTextField nomeJTextField = new JTextField();
	public JTextField sobreNomeJTextField = new JTextField();
	
	public SairJButton sairJButton = new SairJButton();
	public AdicionarJButton adicionarJButton = new AdicionarJButton();
	
	public Object[] listData; 
	
	public JList tipoJList = new JList();
	
	public JTabbedPane jTabbedPane = new JTabbedPane();
	
	public Contato contato = null;
	
	/**
	 * Construtor sem argumentos usado quando se deseja inserir um novo contato
	 * 
	 * @author Sebasti�o Relson
	 */
	public ContatoCadastroJFrame()
	{
		super("Novo Contato");
		configurarInterface();
	} // fim do construtor sem argumento
	
	/**
	 * Construtor com um argumento passando um contato no argumento para edi��o.	  
	 * @param contato Contato a ser editato
	 */
	public ContatoCadastroJFrame(Contato contato)
	{
		super("Editar Contato (" + contato.getNome() + ")");
		this.contato = contato;		
		configurarInterface();
		
		/**
		 * Configura tamb�m os valores iniciais dos JTextField com o nome e
		 * sobrenome do contato		  
		 */
		nomeJTextField.setText(contato.getNome());
		sobreNomeJTextField.setText(contato.getSobrenome());
		
		configurarTiposSelecionado();
		
	} // fim do construtor com um argumento
	
	/**
	 *  Configurar o JList para que fiquem selecionado os configurados
	 *  para o contato.
	 */	
	public void configurarTiposSelecionado()
	{
		try
		{
			Tipo tipos[] = contato.getTipos();
			
			int indices[] = new int[tipos.length];
			
			for(int i = 0; i < tipos.length; ++i)
			{
				int codigo = tipos[i].getCodigo();
				for(int j = 0; j < listData.length; ++j)					
				{						
					int codigoLst =  ((TipoImpl)listData[j]).getCodigo();					
					if (codigo == codigoLst)
					{
						indices[i] = j;
						break;
					} // fim do if					
				} // fim do for interno
			} // fim do for externo
			
			tipoJList.setSelectedIndices(indices);
			
		} // fim do try
		catch(Exception exception)
		{
			JOptionPane.showMessageDialog(this,
					"Erro ao tentar configurar os tipos selecionados:\n" 
					+ exception.getMessage(), 
					"Erro", 
					JOptionPane.WARNING_MESSAGE
				); // fim da chamada do m�tod showMessageDialog do Objeto JOptonPane
			
			
		} // fim do catch
		
	} // fim do m�todo configurarTiposSelecionado
	
	/**
	 * 
	 *  Verifica se o contato � refer�ncia do contato � nulo se for
	 *  ent�o o formul�rio ser� configurado para inser��o de um novo contato
	 *  por�m se existir um refer�ncia de um contato prepara para altera��o.
	 *  
	 *  @author Sebasti�o Relson 
	 */
	public void configurarInterface()
	{
		setLayout(new FlowLayout());
		try
		{
			
			listData =  TipoImpl.getTipos("tiposcontato");
			
			tipoJList.setListData(listData);
			tipoJList.setBorder(new TitledBorder(""));
			JPanel listJPanel = new JPanel(new BorderLayout(5,5));
			
			listJPanel.setBorder(new SoftBevelBorder(10));
			
			listJPanel.add(new JLabel("Tipo do Contato:"), BorderLayout.NORTH);
			
			listJPanel.add(tipoJList, BorderLayout.CENTER);
			
			jpanel.add(listJPanel, BorderLayout.WEST);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		centralJPanel.add(new JLabel("Nome:"));
		
		centralJPanel.add(nomeJTextField);
		
		centralJPanel.add(new JLabel("Sobrenome:"));
		
		centralJPanel.add(sobreNomeJTextField);
		
		centralJPanel.setBorder(new SoftBevelBorder(10));
		
		botoesJPanel.setLayout(new GridLayout(1,3,5,5));

		sairJButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					ContatoCadastroJFrame.this.dispose();
				}
			}
		); 
		
		botoesJPanel.add(sairJButton);
				
		centralJPanel.add(botoesJPanel);
				
		jpanel.add(centralJPanel, BorderLayout.CENTER);
		
		add(jpanel);
		
		//--
		
		if (contato != null)
		{	
			adicionarJButton.setVisible(false);
			botoesJPanel.add(adicionarJButton);
			
			aplicarJButton.addActionListener(new AplicarJButtonActionListener());
			
			botoesJPanel.add(aplicarJButton);
			
			setSize(500, 370);
			
			setConfiguracaoJTabbedPane();		
			add(jTabbedPane);
			
		} // fim do if
		else
		{
			aplicarJButton.setVisible(false);
			botoesJPanel.add(aplicarJButton);
			
			adicionarJButton.addActionListener(new AdicionarJButtonActionListener());
			
			botoesJPanel.add(adicionarJButton);
			
			setSize(500, 180);
			
		} // fim do else
				
		setIconImage(new ImageIcon(getClass().getResource("adicionar16.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);		
	} // fim do m�todo set

	/**
	 * M�todo que adiciona um JPanel no JTabbedPane para cada
	 * conjunto de atribuo exceto Tipo de Contato, nome e Sobrenome. <br>
	 * Os atributos s�o: <br>
	 * - <b> e-mail <br>
	 * - Telefone <br>
	 * - Endere�o <br>
	 * - Observa��o <br>
	 * - Site <br> </b>
	 * 
	 * @author Sebasti�o Relson
	 */
	public void setConfiguracaoJTabbedPane()		
	{
		ImageIcon anexoIcon = new ImageIcon(getClass().getResource("anexo16.png")); 
		
		try
		{		
			EmailListaJPanel emailTab = new EmailListaJPanel(this.contato);
			
			jTabbedPane.addTab("e-mail", 
					anexoIcon,
					emailTab,
					"Lista de e-mails do Contato");
		} // fim do try
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(
					ContatoCadastroJFrame.this, 
					"Erro ao tentar obter a lista de e-mails para o contato" + 
					exception.getMessage(),
					"Contato", 
					JOptionPane.WARNING_MESSAGE
				);
		} // fim do catch
		
		try
		{
		
			TelefoneListaJPanel telefoneTab = new TelefoneListaJPanel(this.contato);
			
			jTabbedPane.addTab("Telefone", 
					anexoIcon,
					telefoneTab,
					"Lista de n�meros de telefone do Contato");			
			
		} // fim do try
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(
					ContatoCadastroJFrame.this, 
					"Erro ao tentar obter a lista de telefones para o contato" + 
					exception.getMessage(),
					"Contato", 
					JOptionPane.WARNING_MESSAGE
				);
		} // fim do catch
		
		try
		{		
			EnderecoListaJPanel enderecoTab = new EnderecoListaJPanel(this.contato);
			
			jTabbedPane.addTab("Endere�os", 
					anexoIcon,
					enderecoTab,
					"Lista de Endere�os relacionados ao Contato");
		} // fim do try
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(
					ContatoCadastroJFrame.this, 
					"Erro ao tentar obter a lista de Endere�os para o contato" + 
					exception.getMessage(),
					"Contato", 
					JOptionPane.WARNING_MESSAGE
				);
		}// fim do catch
		
		try
		{		
			ObservacaoListaJPanel observacaoTab = new ObservacaoListaJPanel(this.contato);
			
			jTabbedPane.addTab("Observa��o", 
					anexoIcon,
					observacaoTab,
					"Lista de Observa��es a respeito do Contato");
		} // fim do try
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(
					ContatoCadastroJFrame.this, 
					"Erro ao tentar obter a lista de observa��es para o contato" + 
					exception.getMessage(),
					"Contato", 
					JOptionPane.WARNING_MESSAGE
				);
		} // fim do catch
		
		try
		{
			SiteListaJPanel siteTab = new SiteListaJPanel(this.contato);
			
			jTabbedPane.addTab("Site", 
					anexoIcon,
					siteTab,
					"Lista de Sites do Contato");
			
			jTabbedPane.setPreferredSize(new Dimension(this.getWidth() - 20, this.getHeight()/2));
		} // fim do try
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(
					ContatoCadastroJFrame.this, 
					"Erro ao tentar obter a lista de sites para o contato" + 
					exception.getMessage(),
					"Contato", 
					JOptionPane.WARNING_MESSAGE
				);
		} // fim do catch		
	} // fim do m�todo setConfiguracaoJTabbedPane 
	
	/**
	 *  M�todo para testar a classe para inser��o de um novo contato
	 *  
	 * @param args par�metro ignorado.
	 */
	
	public static void main(String args[])
	{
		new ContatoCadastroJFrame();
	} // fim do m�todo main
	
	/**
	 * Classe privada que implementa a interface ActionListener � respons�vel por
	 * <b> Aplicar </b> as altera��es feitas nas informa��es b�sicas do contato. </p>
	 * As altera��e b�sicas s�o: </p> <br>
	 * Nome <br>
	 * Sobrenome<br>
	 * ou Tipo
	 * @author Sebasti�o Relson 05/06/2007
	 *
	 */
	private class AplicarJButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String novoNome = nomeJTextField.getText();
			String novoSobrenome = sobreNomeJTextField.getText();
			
			if (!(contato.getNome().equals(novoNome)) ||  !(contato.getSobrenome().equals(novoSobrenome)))
			{
				contato.setNome(novoNome);
				contato.setSobrenome(novoSobrenome);				
			} // fim do if
			
			Tipo tipos[] = new Tipo[tipoJList.getSelectedIndices().length];
			
			int j = 0;
			
			for(int i:tipoJList.getSelectedIndices())
			{
				tipos[j] = (Tipo)listData[i];
				j++;
			} // fim do for
			
			ContatoTipo contatoTipos[] = new ContatoTipo[tipos.length];
			
			int i = 0;
			
			for(Tipo tipo:tipos)
			{
				contatoTipos[i] = new ContatoTipo();
				contatoTipos[i].setCodigo(tipo.getCodigo());
				contatoTipos[i].setDescricao(tipo.getDescricao());
				i++;
			} // fim do for
			
			contato.setTipos(contatoTipos);
			
			contato.atualizar();
			
		} // fim do m�todo actionPerformed
	} // fim da classe privada interna AplicarJButtonActionListener
	
	/**
	 * Classe privada que implementa a interface ActionListener � respons�vel por 
	 * adicionar um novo contato ao clicar no bot�o adicionar.
	 *  
	 * @author Sebasti�o Relson 05/06/2007
	 *
	 */	
	private class AdicionarJButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				contato = new Contato();
				
				String nome = nomeJTextField.getText();
				String sobrenome = sobreNomeJTextField.getText();
				
				// Verifica se o contato tem um nome.
				if (nome.isEmpty())
				{
					nomeJTextField.requestFocus();
					throw new Exception("� necess�rio um nome para o contato");					
				} // fim do if
				
				// Verifica se nenhum Tipo Foi selecionado para o contato.
				if (tipoJList.getSelectedIndices().length <= 0)
				{
					throw new Exception("� necess�rio um tipo para o contato");
					
				} // fim do if
				
				contato.setNome(nome);
				contato.setSobrenome(sobrenome);
				
				Tipo tipos[] = new Tipo[tipoJList.getSelectedIndices().length];
				
				int j = 0;
				
				for(int i:tipoJList.getSelectedIndices())
				{
					tipos[j] = (Tipo)listData[i];
					j++;
				} // fim do for
				
				ContatoTipo contatoTipos[] = new ContatoTipo[tipos.length];
				
				int i = 0;
				
				for(Tipo tipo:tipos)
				{
					contatoTipos[i] = new ContatoTipo();
					contatoTipos[i].setCodigo(tipo.getCodigo());
					contatoTipos[i].setDescricao(tipo.getDescricao());
					i++;
				} // fim do for
				
				contato.setTipos(contatoTipos);
				contato.novo();
				ContatoCadastroJFrame.this.dispose();
				
			} // fim do try
			catch(Exception exception)
			{
				JOptionPane.showMessageDialog(ContatoCadastroJFrame.this, exception.getMessage(),"Novo Contato", JOptionPane.WARNING_MESSAGE);				
			} // fim do catch
		} // fim do m�todo actionPerformed
	} // fim da classe interna AdicionarJButtonActionListener
} // fim da classe ContatoCadastroJFrame