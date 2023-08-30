import java.util.ArrayList;

public class Clientela {

	private ArrayList<Cliente> clientes;

	public boolean cadastraCliente(Cliente cliente) {
		String novoEmail = cliente.getEmail();
		for (Cliente c : clientes) {
			if (novoEmail.equals(c.getEmail())) {
				return false;
			}
		}
		return clientes.add(cliente);
	}

	public Cliente pesquisaCliente(String email) {
		for (Cliente c : clientes) {
			if (c.getEmail().equals(email)) {
				return c;
			}
		}
		return null;
	}

}
