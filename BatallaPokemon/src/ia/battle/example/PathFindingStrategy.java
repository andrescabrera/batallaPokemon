package ia.battle.example;

import ia.battle.camp.FieldCell;

import java.util.List;

public interface PathFindingStrategy {
	List<FieldCell> findPath(FieldCell source, FieldCell target, int warriorSpeed);
	List<FieldCell> findCompletePath(FieldCell source, FieldCell target);
}
