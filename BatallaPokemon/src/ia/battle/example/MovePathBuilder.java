package ia.battle.example;

import ia.battle.camp.FieldCell;
import ia.battle.entities.Move;
import ia.battle.entities.Movimiento;

import java.util.ArrayList;
import java.util.List;

public class MovePathBuilder {

	public Move buildMovementPath(List<FieldCell> fullPath, int warriorSpeed) {
		
		fullPath.remove(fullPath.get(fullPath.size() - 1)); //sacar la celda del enemigo		
		
		return new Movimiento(cropPath(fullPath, warriorSpeed));
	}

	//recortar el path de acuerdo a la speed y checkear que no llegue a destino antes de la speed total.
	private List<FieldCell> cropPath(List<FieldCell> fullPath, int warriorSpeed) {
		List<FieldCell> actionPath = new ArrayList<FieldCell>();
		for (int i = 0; i < warriorSpeed && i < fullPath.size(); i++) {
			actionPath.add(fullPath.get(i));
			fullPath.remove(i); //Lo saco del fullPath
		}
		
		return actionPath;
	}
	
}
