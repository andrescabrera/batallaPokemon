package ia.battle.camp;

import ia.battle.entities.Action;
import ia.battle.entities.Attack;
import ia.battle.entities.Move;
import ia.battle.entities.Warrior;
import ia.battle.entities.WarriorManager;
import ia.battle.example.AndrewWarriorManager;
import ia.exceptions.OutOfMapException;
import ia.exceptions.RuleException;

import java.io.Console;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BattleField {

	private static BattleField instance = new BattleField();

	private WarriorManager wm1, wm2;
	private Warrior currentWarrior, warrior1, warrior2;
	private int warrior1Count, warrior2Count;
	
	private int w1Health, w2Health; //Agregado por Iñaki p/ poder matar al otro. 
	private FieldCell[][] cells;

	private Random random = new Random();

	private BattleField() {
		initCells();
	}

	public static BattleField getInstance() {
		return instance;
	}

	private void initCells() {

		int height = ConfigurationManager.getInstance().getMapHeight();
		int width = ConfigurationManager.getInstance().getMapWidth();

		cells = new FieldCell[height][width];

		for (int i = 1; i < height; i++)
			for (int j = 1; j < width; j++) {
				if (Math.abs(random.nextGaussian()) > 2)
					cells[i][j] = new FieldCell(FieldCellType.BLOCKED, i, j);
				else
					cells[i][j] = new FieldCell(FieldCellType.NORMAL, i, j);

			}

		// TODO: Ubicar SpecialItems

	}

	private void showMap() {

		int height = ConfigurationManager.getInstance().getMapHeight();
		int width = ConfigurationManager.getInstance().getMapWidth();

		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				if (cells[i][j] == warrior1.getPosition())
					System.out.print("1!");
				else if (cells[i][j] == warrior2.getPosition())
					System.out.print("2!");
				else
					System.out.print(cells[i][j]);
			}
			System.out.println();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public FieldCell getFieldCell(int x, int y) throws OutOfMapException {

		if (x > cells.length - 1)
			throw new OutOfMapException();

		if (x < 0)
			throw new OutOfMapException();

		if (y > cells[0].length - 1)
			throw new OutOfMapException();

		if (y < 0)
			throw new OutOfMapException();

		return cells[x][y];
	}

	public EnemyData getEnemyPosition() {

		if(getEnemyDistance() <= currentWarrior.getRange()){
			//enemigo dentro del alcance
			return new EnemyData(getEnemy().getPosition(), getEnemy().getHealth());
		}
		//no lo veo
		return null; 
	}
	
	private int getEnemyDistance(){
		Double x = Math.floor(Math.sqrt((currentWarrior.getPosition().getX()-getEnemy().getPosition().getX())^2
				+ (currentWarrior.getPosition().getY()-getEnemy().getPosition().getY())^2));
		return x.intValue();
	}

	public ArrayList<FieldCell> getSpecialItems() {
		ArrayList<FieldCell> items = new ArrayList<FieldCell>();

		return items;
	}

	private void fight() {

		// TODO:Borrar
		wm1 = new AndrewWarriorManager();
		wm2 = new AndrewWarriorManager();

		warrior1Count = 1;
		warrior2Count = 1;

		// Solicita los warriors

		try {
			warrior1 = wm1.getNextWarrior();
			w1Health = warrior1.getHealth(); //Necesito la vida p/matar al otro -Iñaki-
		} catch (RuleException e1) {
			// Pierde el warrior1
			e1.printStackTrace();
		}

		try {
			warrior2 = wm2.getNextWarrior();
			w2Health = warrior2.getHealth();
		} catch (RuleException e) {
			// Pierde el warrior2
			e.printStackTrace();
		}

		// TODO: Los ubica en el mapa (poner en zonas opuestas)

		try {
			warrior1.setPosition(cells[random.nextInt(cells.length-1)][random
					.nextInt(cells[0].length-1)]);
		} catch (RuleException e) {
			e.printStackTrace(); // TODO Si la posicion inicial no esta
									// disponible?
		}

		try {
			warrior2.setPosition(cells[random.nextInt(cells.length-1)][random
					.nextInt(cells[0].length-1)]);
		} catch (RuleException e) {
			e.printStackTrace(); // TODO Si la posicion inicial no esta
									// disponible?
		}

		// TODO: Determinar quien empieza
		currentWarrior = warrior1;

		// TODO: Ciclo de lucha
		int currentHealth; 	//para ver si mate al otro -Iñaki-
		do {
			BattleField.getInstance().showMap();

			ArrayList<Action> turno = currentWarrior.playTurn(System
					.currentTimeMillis());
			if (turno != null)
				realizarTurno(turno);

			if (currentWarrior == warrior1){
				currentWarrior = warrior2;
				currentHealth = w2Health;
			}else{
				currentWarrior = warrior1;
				currentHealth = w1Health;
			}

		}// while (currentWarrior.getHealth() > 0);
		//ni el battlefield ni el enemigo
		//pueden modificar la health de un warrior.
		
		while(currentHealth > 0);
		if(currentWarrior == warrior1){
			System.out.println("Warrior 2 Wins");
			return;
		}
		else{
			System.out.println("Warrior 1 Wins");
			return;
		}
	}

	private void realizarTurno(ArrayList<Action> turno) {
		BattleLoop: for (Action action : turno) {
			if (action instanceof Move) {
				List<FieldCell> celdasAMover = ((Move) action).move();
				try {
					moverJugador(celdasAMover);
				} catch (RuleException ex) {
					ex.printStackTrace(); // TODO : si esta mal el movimiento
											// mueve hasta donde puede
				}
			} else if (action instanceof Attack) {
//				((Attack) action).attack();
				
//				Comentado por Iñaki
//				Attack solo sirve para identificar una accion de ataque
//				pero el warrior no tiene acceso al enemigo para hacerle daño
//				ni el metodo tiene retorno.
				
				if(getEnemy()==warrior1){
					w1Health-=currentWarrior.getStrength();
				}else{
					w2Health-=currentWarrior.getStrength();
				}
				System.out.println("w1: "+w1Health+"; w2: "+w2Health);
			}
			if(w1Health<=0 || w2Health<=0) break BattleLoop;
		}
	}

	private Warrior getEnemy() {

		if (currentWarrior == warrior1) {
			return warrior2;
		}
		
		return warrior1; 
	}

	private void moverJugador(List<FieldCell> celdasAMover) throws RuleException {
		// Obtengo la posicion del enemigo
		FieldCell posicionDelEnemigo = getEnemy().getPosition();

		// Muevo y si hay alguno problema lo elevo
		for (FieldCell fieldCell : celdasAMover) {
			if (fieldCell.getFieldCellType() == FieldCellType.BLOCKED
					|| fieldCell == posicionDelEnemigo) {
				throw new RuleException();
			}
			currentWarrior.setPosition(fieldCell);
		}
	}

	public static void main(String[] args) {

		BattleField.getInstance().fight();
	}

}
