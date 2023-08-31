import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEDelivery {

	private Scanner entrada = null;                  // Atributo para entrada de dados
	private PrintStream saidaPadrao = System.out;	 // 	Guarda a saida padrão - tela (console)
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
		adicionarClientes();
		printarClientesCad();

		// 2
		adicionarEntregas();
		printarEntregasCad();

		// 3
		mostrarClientesCad();

		// 4
		mostrarEntregasCad();

		// 5
		procurarCliente();

		// 6
		procurarEntrega();
	}


	private void adicionarClientes() {
		String email = "";
		String nome = "";
		String endereco = "";

		do {
			email = entrada.nextLine();
			if (!email.equals("-1")) {
				nome = entrada.nextLine();
				endereco = entrada.nextLine();

				Cliente novoCliente = new Cliente(email, nome, endereco);
				novaClientela.cadastraCliente(novoCliente);;
			}
		} while (!email.equals("-1"));
	}

	private void printarClientesCad() {
		for (Cliente c : novaClientela.getClientes()) {
			System.out.println("1;" + c.getEmail() + ";" + c.getNome() + ";" + c.getEndereco());
		}
	}

	private void adicionarEntregas() {
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

				Cliente c = novaClientela.pesquisaCliente(email);
				if (c != null) {
					Entrega entrega = new Entrega(codigo, preco, descricao, c);
					entregasPendentes.novaEntrega(entrega);
				}
			}
		} while (codigo != -1);
	}

	private void printarEntregasCad() {
		for (Entrega e : entregasPendentes.getEntregas()) {
			System.out.println("2;" + e.getCodigo() + ";" + e.getValor() +  ";" + e.getDescricao() + "" + e.getCliente().getEmail());
		}
	}

	private void mostrarClientesCad() {
		System.out.println("3;" + novaClientela.getClientes().size());
	}

	private void mostrarEntregasCad() {
		System.out.println("4;" + entregasPendentes.getEntregas().size());
	}

	private void procurarCliente() {
		String email = entrada.nextLine();

		Cliente c = novaClientela.pesquisaCliente(email);
		if (c != null) {
			System.out.println("5;" + c.getEmail() + ";" + c.getNome() + ";" + c.getEndereco());
		} else {
			System.out.println("5;Cliente inexistente");
		}
	}

	private void procurarEntrega() {
		int codigo = Integer.parseInt(entrada.nextLine());

		Entrega e = entregasPendentes.pesquisaEntrega(codigo);
		if (e != null) {
			System.out.println("6;" + e.getCodigo() + ";" + e.getValor() + ";" + e.getDescricao() + ";" + e.getCliente().getEmail() + ";" + e.getCliente().getNome() + ";" + e.getCliente().getEndereco());
		} else {
			System.out.println("6;Cliente inexistente");
		}
	}
}
