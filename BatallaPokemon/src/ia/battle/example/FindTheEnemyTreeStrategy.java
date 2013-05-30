package ia.battle.example;

import ia.battle.camp.EnemyData;
import ia.battle.camp.FieldCell;
import ia.battle.entities.Action;

import java.util.List;

public class FindTheEnemyTreeStrategy implements TurnStrategy {
	
	private PathFindingStrategy pathFinder = new AStarAlgorithm();
	
	@Override
	public List<Action> getActions(EnemyData enemyData, AndrewWarrior warrior) {
		// TODO Completar las acciones de acuerdo a los criterios de busqueda de
		// enemigos, usando en este caso un arbol con la información disponible.

		// TODO NO deberíamos tener al enemigo en el rango de vision... esto se
		// debería chequear en el arbol de TreeStrategyDispatcher

		return null;
	}

	private FieldCell getIdealPosition(List<EnemyData> enemyData) {
		FieldCell posicionDeBusqueda = null;

		// TODO Calcular la posicion de movimiento de acuerdo a los datos
		// aprendidos. Por ejemplo las ultimas veces visto (List<enemyData>)

		return posicionDeBusqueda;
	}

}
