package ia.battle.example;

import java.util.List;

import ia.battle.camp.EnemyData;
import ia.battle.camp.FieldCell;
import ia.battle.entities.Action;

public interface TurnStrategy {
	List<Action> getActions(EnemyData enemyData, AndrewWarrior warrior);
}
