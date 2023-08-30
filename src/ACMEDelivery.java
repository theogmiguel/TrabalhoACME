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
	private Scanner entrada;

	public void executa() {
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


	}


}
