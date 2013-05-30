package ia.battle.example;

import ia.battle.camp.EnemyData;
import ia.battle.camp.FieldCell;

public class TreeStrategyDispatcher implements AIStrategyDispatcher {

	@Override
	public TurnStrategy findStrategy(EnemyData enemyData, AndrewWarrior warrior) {
		
		//Escoge el tipo de juego a hacer, ofensivo, defensivo, de busqueda, etc..
		
		//TODO arbol de decision basado en por ejemplo esta información: 
		
		//Datos del enemigo, la distancia a su posicion y su salud
		FieldCell enemyPosition = enemyData.getFieldCell();
		int enemyHealth = enemyData.getHealth();
		int enemyDistance = warrior.getEnemyDistance(enemyPosition);
		
		//Datos de nuestro warrior
		FieldCell position = warrior.getPosition();
		int health = warrior.getHealth();
		int defense = warrior.getDefense();
		int range = warrior.getRange();
		int speed = warrior.getSpeed();
		int strength = warrior.getStrength();
		
		//TODO Por ejemplo si el arbol indica que es conveniente atacar:
		return new OffensiveTreeStrategy();
	}

}