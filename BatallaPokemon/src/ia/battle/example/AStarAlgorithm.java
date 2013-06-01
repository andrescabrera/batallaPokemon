package ia.battle.example;

import ia.battle.camp.BattleField;
import ia.battle.camp.FieldCell;
import ia.battle.camp.FieldCellType;
import ia.exceptions.OutOfMapException;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class AStarAlgorithm implements PathFindingStrategy {

	@Override
	public List<FieldCell> findPath(FieldCell source, FieldCell target,
			int warriorSpeed) {
		//A* hasta el objetivo indicado haciendo solo los saltos indicados por warriorSpeed
		List<FieldCell> fullPath = findCompletePath(source, target);
		
		return cropPath(fullPath, warriorSpeed);
	}

	private List<FieldCell> cropPath(List<FieldCell> fullPath, int warriorSpeed) {
		
		return null;
	}

	@Override
	public List<FieldCell> findCompletePath(FieldCell inicio, FieldCell destino) {
			

		StepComparator stepComparator = new StepComparator(destino);
		PriorityQueue<Step> listaAbierta = new PriorityQueue<Step>(1000, stepComparator);
		
			TreeSet<FieldCell> listaCerrada = new TreeSet<FieldCell>();
				
			listaAbierta.add(new Step(inicio));
			
			do
			{
				Step step = listaAbierta.poll();
				listaCerrada.add(step.nodoActual);
				if(isGoal(step, destino))
				{
					return stepToFieldCell(step);
				}
				
				for(Step pasoSiguiente : getPosibleSteps(step, listaCerrada))
				{
					listaAbierta.add(pasoSiguiente);		
				}
			}while(!listaAbierta.isEmpty());
			return null;
		}

	private List<FieldCell> stepToFieldCell(Step step) {
		ArrayList<FieldCell> reversepath = new ArrayList<FieldCell>();
		
		while(step != null){ 
			reversepath.add(step.nodoActual);
			step = step.padre;
		}
			
		ArrayList<FieldCell> path = new ArrayList<FieldCell>();
		for(int i = reversepath.size();  i > -1; i--){
			path.add(reversepath.get(i));
		}
		
		return path;
	}

	List<Step> getPosibleSteps(Step step, TreeSet<FieldCell> listaCerrada) {
		ArrayList<Step> proximosSteps = new ArrayList<Step>();
		BattleField battleField = BattleField.getInstance();

		for (int x = -1; x < 1; x++) {
			for (int y = -1; y < 1; y++) {
				if (x == 0 && y == 0) {
					continue;
				}

				try {
					FieldCell proxima = battleField.getFieldCell(x
							+ step.nodoActual.getX(),
							y + step.nodoActual.getY());
					if (proxima.getFieldCellType() != FieldCellType.BLOCKED
							&& listaCerrada.contains(proxima)) {
						proximosSteps.add(new Step(proxima, step));
					}
				} catch (OutOfMapException ex) {
				}

			}
		}
		return proximosSteps;
	}

	boolean isGoal(Step step, FieldCell target) {
		return step.nodoActual.getX() == target.getX()
				&& step.nodoActual.getY() == target.getY();
	}
}
