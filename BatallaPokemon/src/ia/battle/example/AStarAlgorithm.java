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

	private FieldCell destino;
	private FieldCell inicio;

	@Override
	public List<FieldCell> findCompletePath(FieldCell inicio, FieldCell destino) {
		this.inicio = inicio;
		this.destino = destino;

		StepComparator stepComparator = new StepComparator(destino);
		PriorityQueue<Step> listaAbierta = new PriorityQueue<Step>(1000,
				stepComparator);

		TreeSet<FieldCell> listaCerrada = new TreeSet<FieldCell>();

		listaAbierta.add(new Step(inicio));

		Step step;

		do {

			step = listaAbierta.poll();

			listaCerrada.add(step.nodoActual);
			if (isGoal(step, destino)) {
				return stepToFieldCell(step);
			}

			for (Step pasoSiguiente : getPosibleSteps(step, listaCerrada)) {
				if (!listaAbierta.contains(pasoSiguiente)) {
					pasoSiguiente.fCost = getFCost(pasoSiguiente);
					listaAbierta.add(pasoSiguiente);
				}
			}
		} while (!listaAbierta.isEmpty());
		return null;
	}

	int getFCost(Step step) {
		// TODO DEBERIA ESTAR CACHEADO EN EL STEP!
		int gCost = 0;
		Step currentStep = step;
		while (currentStep != null) {
			gCost += getStepCost(currentStep);
			currentStep = currentStep.padre;
		}

		int h = 10 * Math.abs(destino.getX() - step.nodoActual.getX())
				- Math.abs(destino.getY() - step.nodoActual.getY());
		
		return gCost + h;
	}

	private int getStepCost(Step step) {
		int cont = 0;
		if (step.padre == null)
			return 0;
		if ((step.nodoActual.getX() - step.padre.nodoActual.getX()) != 0)
			cont++;
		if ((step.nodoActual.getY() - step.padre.nodoActual.getY()) != 0)
			cont++;
		if (cont == 1)
			return 10;
		else if (cont == 2)
			return 14;
		else
			return 0;
	}

	private List<FieldCell> stepToFieldCell(Step step) {
		ArrayList<FieldCell> reversepath = new ArrayList<FieldCell>();

		while (step != null) {
			reversepath.add(step.nodoActual);
			step = step.padre;
		}

		ArrayList<FieldCell> path = new ArrayList<FieldCell>();
		for (int i = reversepath.size() - 1; i > -1; i--) {
			path.add(reversepath.get(i));
		}

		return path;
	}

	List<Step> getPosibleSteps(Step step, TreeSet<FieldCell> listaCerrada) {
		ArrayList<Step> proximosSteps = new ArrayList<Step>();
		BattleField battleField = BattleField.getInstance();

		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				if (x == 0 && y == 0) {
					continue;
				}

				try {
					FieldCell proxima = battleField.getFieldCell(x
							+ step.nodoActual.getX(),
							y + step.nodoActual.getY());
					if (proxima == null) {
						throw new OutOfMapException();
					}
					if (proxima.getFieldCellType() != FieldCellType.BLOCKED
							&& !listaCerrada.contains(proxima)) {
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
