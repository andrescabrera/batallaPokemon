package ia.battle.example;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

import ia.battle.camp.BattleField;
import ia.battle.camp.EnemyData;
import ia.battle.camp.FieldCell;
import ia.battle.entities.Action;
import ia.battle.entities.Warrior;
import ia.exceptions.RuleException;

public class AndrewWarrior extends Warrior {

	private TurnStrategy turnStrategy;
	private AIStrategyDispatcher aiStrategy = new TreeStrategyDispatcher();
	
	public AndrewWarrior(String name, int health, int defense, int strength,
			int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
		
	}

	@Override
	public ArrayList<Action> playTurn(long tick) {
		
		List<Action> actions = null;
		EnemyData enemyData = BattleField.getInstance().getEnemyPosition();
		
		//Le pido a ArbolStrategy (aiStrategy) su mejor estrategia de juego: ej. huir o atacar?
		turnStrategy = aiStrategy.findStrategy(enemyData, this);
		
		//Le pido a la estrategia de juego que complete las acciones según el algoritmo indicado.
		actions = turnStrategy.getActions(enemyData, this);
		return (ArrayList<Action>) actions;
	}	

	public int getEnemyDistance(FieldCell posicionDelEnemigo) {
		//TODO calculo cuantas celdas hay hasta el enemigo
		return 0;
	}
	
}
