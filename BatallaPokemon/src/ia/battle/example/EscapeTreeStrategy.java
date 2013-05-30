package ia.battle.example;

import java.util.List;

import ia.battle.camp.EnemyData;
import ia.battle.camp.FieldCell;
import ia.battle.entities.Action;

public class EscapeTreeStrategy implements TurnStrategy {

	private PathFindingStrategy pathFinder = new AStarAlgorithm();
	
	private FieldCell getIdealPosition(EnemyData enemyData) {
		FieldCell idealEscapePosition = null;
		// TODO Calculo la mejor posicion de escape (por ejemplo una punta del mapa)
		
		return idealEscapePosition;
	}

	@Override
	public List<Action> getActions(EnemyData enemyData, AndrewWarrior warrior) {
		// TODO Crear las acciones de escape basandome en un arbol, en este caso
		
		return null;
	}
}
