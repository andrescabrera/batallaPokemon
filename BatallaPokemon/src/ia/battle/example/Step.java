package ia.battle.example;

import ia.battle.camp.FieldCell;

public class Step {
	public FieldCell nodoActual;
	public Step padre;

	public Step(FieldCell nodo) {
		this.nodoActual = nodo;
		this.padre = null;
	}

	public Step(FieldCell nodo, Step padre) {
		this.nodoActual = nodo;
		this.padre = padre;
	}

	@Override
	public boolean equals(Object o) {
		try {

			if (((Step) o).nodoActual.getX() == this.nodoActual.getX()
					&& ((Step) o).nodoActual.getY() == this.nodoActual.getY()) {
				return true;
			}

		} catch (Exception e) {
		}

		return false;

	}
}
