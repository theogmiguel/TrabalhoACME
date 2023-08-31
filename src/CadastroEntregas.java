import java.util.ArrayList;

public class CadastroEntregas {

	private ArrayList<Entrega> entregas;

	public CadastroEntregas() {
		entregas = new ArrayList<>();
	}

	public boolean novaEntrega(Entrega entrega) {
		int novoCodigo = entrega.getCodigo();
		for (Entrega e : entregas) {
			if (novoCodigo == e.getCodigo()) {
				return false;
			}
		}
		return entregas.add(entrega);
	}

	public Entrega pesquisaEntrega(int codigo) {
		for (Entrega e : entregas) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		return null;
	}

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
