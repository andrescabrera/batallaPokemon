package ia.battle.example;

import java.util.List;

import ia.battle.camp.EnemyData;
import ia.battle.camp.FieldCell;
import ia.battle.entities.Action;

public class OffensiveTreeStrategy implements TurnStrategy {
	
	private PathFindingStrategy pathFinder = new AStarAlgorithm();
	
	private FieldCell getIdealPosition(AndrewWarrior warrior, EnemyData enemyData) {
		FieldCell posicionDeAtaque = null; //TODO Haces el pathFinding completo y le restas uno
		
		pathFinder.findCompletePath(warrior.getPosition(), enemyData.getFieldCell());
		return posicionDeAtaque;
	}

	@Override
	public List<Action> getActions(EnemyData enemyData, AndrewWarrior warrior) {
		// TODO Crear las acciones de ataque basandome en un arbol por ejemplo
		
		return null;
	}

}
