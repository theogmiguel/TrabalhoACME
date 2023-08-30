import java.util.ArrayList;

public class Cliente {
	private String email;
	private String nome;
	private String endereco;
	private ArrayList<Entrega> entregas;


	public Cliente(String email, String nome, String endereco) {
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		entregas = new ArrayList<>();
	}

	public boolean adicionaEntrega(Entrega entrega) {
		return entregas.add(entrega);
	}

	public ArrayList<Entrega> pesquisaEntregas() {
		return entregas;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}
}
