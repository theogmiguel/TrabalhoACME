import java.util.ArrayList;

public class CadastroEntregas {

	private ArrayList<Entrega> entregas;

	public CadastroEntregas() {
		entregas = new ArrayList<>();
	}

	/**
	 * Recebe como parâmetro uma nova entrega e
	 * cadastra-a no sistema. Não pode haver entregas com o mesmo código. Retorna
	 * true se o cadastro teve sucesso; ou false em caso contrário.
	 */
	public boolean novaEntrega(Entrega entrega) {
		int novoCodigo = entrega.getCodigo();
		for (Entrega e : entregas) {
			if (novoCodigo == e.getCodigo()) {
				return false;
			}
		}
		return entregas.add(entrega);
	}

	/**
	 * Retorna a entrega com o código indicado. Se não houver
	 * entrega com este código retorna null
	 */
	public Entrega pesquisaEntrega(int codigo) {
		for (Entrega e : entregas) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Retorna uma coleção de entregas com o e-mail do
	 * cliente indicado. Se não houver nenhuma entrega com cliente com este e-mail retorna null.
	 */
	public ArrayList<Entrega> pesquisaEntrega(String email) {
		ArrayList<Entrega> entregasAux = new ArrayList<>();
		for (Entrega e : entregas) {
			if (email.equals(e.getCliente().getEmail())) {
				entregasAux.add(e);
			}
		}
		return entregasAux;
	}

	public ArrayList<Entrega> getEntregas() {
		return entregas;
	}
}
