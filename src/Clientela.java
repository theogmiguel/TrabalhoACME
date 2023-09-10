import java.util.ArrayList;

public class Clientela {

	private ArrayList<Cliente> clientes;

	public Clientela() {
		this.clientes = new ArrayList<>();
	}

	/**
	 *  Recebe como parâmetro um novo cliente e cadastradoo no sistema. Não pode haver clientes com o mesmo e-mail. Retorna true se o
	 * 	cadastro teve sucesso; ou false em caso contrário.
	 */
	public boolean cadastraCliente(Cliente cliente) {
		String novoEmail = cliente.getEmail();
		for (Cliente c : clientes) {
			if (novoEmail.equals(c.getEmail())) {
				return false;
			}
		}
		return clientes.add(cliente);
	}

	/**
	 * Retorna o cliente com o e-mail indicado. Se não
	 * houver nenhum cliente com este e-mail retorna null.
	 */
	public Cliente pesquisaCliente(String email) {
		for (Cliente c : clientes) {
			if (c.getEmail().equals(email)) {
				return c;
			}
		}
		return null;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
}
