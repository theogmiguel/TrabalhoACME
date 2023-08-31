import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEDelivery {

	private CadastroEntregas cadastroEntregas;
	private Clientela clientela;
	private Scanner entrada = null;                  // Atributo para entrada de dados
	private PrintStream saidaPadrao = System.out;	 // 	Guarda a saida padrão - tela (console)
	private Clientela novaClientela;

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

		novaClientela = new Clientela();
	}

	public void executa() {
		adicionarClientes();
	}

	private void adicionarClientes() {
		String email = "";
		String nome = "";
		String endereco = "";

		email = entrada.nextLine();
		do {
			nome = entrada.nextLine();
			endereco = entrada.nextLine();
			entrada.nextLine();

			Cliente novoCliente = new Cliente(email, nome, endereco);
			novaClientela.cadastraCliente(novoCliente);

			System.out.println(email);
			System.out.println(nome);
			System.out.println(endereco);

		} while (!email.equals("-1"));
	}
}
