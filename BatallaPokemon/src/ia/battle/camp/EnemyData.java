package ia.battle.camp;


public class EnemyData {
	private FieldCell fieldCell;
	private int health;
	
	EnemyData(FieldCell fieldCell, int health) {
		this.setFieldCell(fieldCell);
		this.setHealth(health);
	}

	public FieldCell getFieldCell() {
		return fieldCell;
	}

	public void setFieldCell(FieldCell fieldCell) {
		this.fieldCell = fieldCell;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	
	
}
