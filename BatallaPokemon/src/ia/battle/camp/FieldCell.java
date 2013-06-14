package ia.battle.camp;

public class FieldCell implements Comparable {
	private int x, y;
	private FieldCellType fieldCellType;
	private SpecialItem specialItem;

	FieldCell(FieldCellType type, int x, int y) {
		this.fieldCellType = type;
		this.x = x;
		this.y = y;
	}

	public FieldCellType getFieldCellType() {
		return fieldCellType;
	}

	public SpecialItem getSpecialItem() {
		return specialItem;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String toString() {
		switch (fieldCellType) {
		case NORMAL:
			return "_!";
		case BLOCKED:
			return "X!";
		}

		return "X!";
	}

	@Override
	public int compareTo(Object o) {
		if(o == this) return 0;
		
		return 1;
	}

}
