package ia.battle.entities;

import ia.battle.camp.FieldCell;
import java.util.List;

public abstract class Move extends Action {

	public abstract List<FieldCell> move();

}
