package ia.battle.example;

import ia.battle.camp.EnemyData;
import ia.battle.entities.Warrior;

public interface AIStrategyDispatcher {
	TurnStrategy findStrategy(EnemyData enemyData, AndrewWarrior warrior);
}
