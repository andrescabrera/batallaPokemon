package ia.battle.example;

import java.util.ArrayList;
import java.util.List;

import ia.battle.camp.EnemyData;
import ia.battle.camp.FieldCell;
import ia.battle.entities.Action;
import ia.battle.entities.Attack;
import ia.battle.entities.Move;

public class OffensiveTreeStrategy implements TurnStrategy {
	
	private PathFindingStrategy pathFinder = new AStarAlgorithm();
	
	@Override
	public List<Action> getActions(EnemyData enemyData, AndrewWarrior warrior) {
		// TODO Crear las acciones de ataque basandome en un arbol por ejemplo
		//return moves hasta el enemigo; 3 por turno.
		List<Action> actionDelTurno = new ArrayList<Action>();
		
		List<FieldCell> fullPath = pathFinder.findCompletePath(warrior.getPosition(), enemyData.getFieldCell());
		
		fullPath.remove(fullPath.get(fullPath.size() - 1)); //sacar la celda del enemigo
		fullPath.remove(fullPath.get(0));
		MovePathBuilder mPathBuilder = new MovePathBuilder();
		
		while(fullPath.size() > 0 && actionDelTurno.size() < 3) {
			Move moveAction = mPathBuilder.buildMovementPath(fullPath, warrior.getSpeed());
			actionDelTurno.add(moveAction);
		}
		
		while(actionDelTurno.size() < 3) {
			System.out.println("No complete las 3 actions, entonces te pego");
			actionDelTurno.add(new Attack());
		}
		
		return actionDelTurno;
	}

}
