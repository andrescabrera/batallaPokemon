package ia.battle.example;

import ia.battle.entities.Warrior;
import ia.battle.entities.WarriorManager;
import ia.exceptions.RuleException;

public class AndrewWarriorManager extends WarriorManager {

	@Override
	public Warrior getNextWarrior() throws RuleException {
		//name, health, defense, strength, speed, range
		AndrewWarrior andrew = new AndrewWarrior("Andrew", 50, 5, 10, 1, 1000);
		//fruta de stats, para probar.
//		try {
//			andrew.setPosition(BattleField.getInstance().getFieldCell(0, 0));
//		} catch (OutOfMapException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return andrew;
	}

}
