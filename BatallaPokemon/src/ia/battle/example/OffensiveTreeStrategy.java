package ia.battle.example;

import java.util.List;

import ia.battle.camp.EnemyData;
import ia.battle.camp.FieldCell;
import ia.battle.entities.Action;

public class OffensiveTreeStrategy implements TurnStrategy {
	
	private PathFindingStrategy pathFinder = new AStarAlgorithm();
	
	private FieldCell getIdealPosition(EnemyData enemyData) {
		FieldCell posicionDeAtaque = null;
		// TODO Calcular la posicion mas cerca de ataque 
		// (para ponerse al lado del enemigo)
		
		//Debería hacer el pathFinding hasta el enemigo y quedarme en la anteultima posicion
		return posicionDeAtaque;
	}

	@Override
	public List<Action> getActions(EnemyData enemyData, AndrewWarrior warrior) {
		// TODO Crear las acciones de ataque basandome en un arbol por ejemplo
		
		return null;
	}

}
