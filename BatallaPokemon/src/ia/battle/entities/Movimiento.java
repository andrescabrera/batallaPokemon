package ia.battle.entities;

import ia.battle.camp.FieldCell;
import java.util.List;

public class Movimiento extends Move {

	private List<FieldCell> movimientos;
	
	public Movimiento(List<FieldCell> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public List<FieldCell> move() {
		return movimientos;
	}

}
