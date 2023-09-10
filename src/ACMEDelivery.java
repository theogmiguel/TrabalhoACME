import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ACMEDelivery {

	private Scanner entrada = null;                  // Atributo para entrada de dados
	private PrintStream saidaPadrao = System.out;     // 	Guarda a saida padrão - tela (console)
	private Clientela novaClientela;
	private CadastroEntregas entregasPendentes;

	public ACMEDelivery() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader("arquivoentrada.txt"));
			entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
			PrintStream streamSaida = new PrintStream(new File("arquivosaida.txt"), Charset.forName("UTF-8"));
			System.setOut(streamSaida);             // Usa como saida um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
		entrada.useLocale(Locale.ENGLISH);

		entregasPendentes = new CadastroEntregas();
		novaClientela = new Clientela();
	}

	public void executa() {
		// 1
		cadastrarNovoClientes();
		printarClientesCad();

		// 2
		cadastrarNovaEntregas();
		printarEntregasCad();

		// 3
		mostrarClientesCad();

		// 4
		mostrarEntregasCad();

		// 5
		procurarCliente();

		// 6
		procurarEntrega();

		// 7
		entregaClientDeterminado();

		// 8
		entregaMaiorValor();

		// 9
		mostrarEndEntrega();

		// 10
		somatorioValorCliente();
	}

	/**
	 * 1) Cadastra clientes printa usando a outra classe "printarClientesCad".
	 */
	private void cadastrarNovoClientes() {
		String email = "";
		String nome = "";
		String endereco = "";

		do {
			email = entrada.nextLine();
			if (!email.equals("-1")) {
				nome = entrada.nextLine();
				endereco = entrada.nextLine();

				Cliente novoCliente = new Cliente(email, nome, endereco);
				novaClientela.cadastraCliente(novoCliente);
				;
			}
		} while (!email.equals("-1"));
	}
	private void printarClientesCad() {
		for (Cliente c : novaClientela.getClientes()) {
			System.out.println("1;" + c.getEmail() + ";" + c.getNome() + ";" + c.getEndereco());
		}
	}

	/**
	 * 2) Cadastra entregas e printa usando a outra classe "printarEntregasCad".
	 */
	private void cadastrarNovaEntregas() {
		int codigo = 0;
		double preco = 0;
		String descricao = "";
		String email = "";

		do {
			codigo = Integer.parseInt(entrada.nextLine());
			if (codigo != -1) {
				preco = Double.parseDouble(entrada.nextLine());
				descricao = entrada.nextLine();
				email = entrada.nextLine();

				Cliente cliente = novaClientela.pesquisaCliente(email);
				if (cliente != null) {
					Entrega entrega = new Entrega(codigo, preco, descricao, cliente);
					entregasPendentes.novaEntrega(entrega);
					cliente.adicionaEntrega(entrega);
				}
			}
		} while (codigo != -1);
	}
	private void printarEntregasCad() {
		for (Entrega e : entregasPendentes.getEntregas()) {
			System.out.println("2;" + e.getCodigo() + ";" + e.getValor() + ";" + e.getDescricao() + "" + e.getCliente().getEmail());
		}
	}

	/**
	 * 3) Mostra quantos clientes foram cadastrados.
	 */
	private void mostrarClientesCad() {
		System.out.println("3;" + novaClientela.getClientes().size());
	}

	/**
	 * 4) Mostra quantas entregas foram cadastradas.
	 */
	private void mostrarEntregasCad() {
		System.out.println("4;" + entregasPendentes.getEntregas().size());
	}

	/**
	 * 5) Mostra os dados de um determinado cliente.
	 */
	private void procurarCliente() {
		String email = entrada.nextLine();

		Cliente c = novaClientela.pesquisaCliente(email);
		if (c != null) {
			System.out.println("5;" + c.getEmail() + ";" + c.getNome() + ";" + c.getEndereco());
		} else {
			System.out.println("5;Cliente inexistente");
		}
	}

	/**
	 * 6) Mostra os dados de uma determinada entrega.
	 */
	private void procurarEntrega() {
		int codigo = Integer.parseInt(entrada.nextLine());

		Entrega e = entregasPendentes.pesquisaEntrega(codigo);
		if (e != null) {
			System.out.println("6;" + e.getCodigo() + ";" + e.getValor() + ";" + e.getDescricao() + ";" + e.getCliente().getEmail() + ";" + e.getCliente().getNome() + ";" + e.getCliente().getEndereco());
		} else {
			System.out.println("6;Entrega inexistente");
		}
	}

	/**
	 * 7) Mostra os dados das entregas de um determinado cliente.
	 */
	private void entregaClientDeterminado() {
		String email = entrada.nextLine();

		Cliente c = novaClientela.pesquisaCliente(email);
		if (c != null) {
			for (Entrega e : c.getEntregas()) {
				System.out.println("7;" + email + ";" + e.getCodigo() + ";" + e.getCodigo() + ";" + e.getValor() + ";" + e.getDescricao());
			}
		} else {
			System.out.println("7;Cliente inexistente");
		}
	}

	/**
	 * 8) Mostra os dados da entrega de maior valor.
	 */
	private void entregaMaiorValor() {
		if (entregasPendentes.getEntregas().size() == 0) {
			System.out.println("8;Entrega inexistente");
		} else {
			ArrayList<Entrega> entregas = entregasPendentes.getEntregas();
			double maiorValor = 0;
			Entrega entregaMaior = null;
			for (int i = 0; i < entregas.size(); i++) {
				if (i == 0) {
					entregaMaior = entregas.get(i);
					maiorValor = entregas.get(i).getValor();
				} else {
					if (maiorValor < entregas.get(i).getValor()) {
						entregaMaior = entregas.get(i);
						maiorValor = entregas.get(i).getValor();
					}
				}
			}
			System.out.println("8;" + entregaMaior.getCodigo() + ";" + entregaMaior.getValor() + ";" + entregaMaior.getDescricao());
		}
	}

	/**
	 * 9) Mostra o endereço de uma determinada entrega.
	 */
	private void mostrarEndEntrega() {
		int codigo = entrada.nextInt();

		Entrega e = entregasPendentes.pesquisaEntrega(codigo);
		if (e != null) {
			System.out.println("9;" + e.getCodigo() + ";" + e.getValor() +  ";" + e.getDescricao() + ";" + e.getCliente().getEndereco());
		} else {
			System.out.println("9;Entrega inexistente");
		}
	}

	/**
	 * 10) Mostra o somatório de valores de entregas de determinado cliente.
	 */
	private void somatorioValorCliente() {
		String email = entrada.next();

		Cliente c = novaClientela.pesquisaCliente(email);
		double valor = 0;
		if (c != null) {
			for (Entrega e : c.getEntregas()) {
				valor += e.getValor();
			}
			System.out.printf("10;%s;%s;%.2f", c.getEmail(), c.getNome(), valor);
		} else {
			System.out.println("10;Cliente inexistente");
		}
	}
}
