package ia.battle.example;

import java.util.Comparator;

import ia.battle.camp.FieldCell;

public class StepComparator implements Comparator<Step>
{
	FieldCell destino;
	public StepComparator(FieldCell detino)
	{
		this.destino = destino;
	}  
	
	@Override
    public int compare(Step a, Step b)
    {
	   return getStepAcumCost(a) - getStepAcumCost(b);
    }	
		
	int	getStepAcumCost(Step step){
		//TODO DEBERIA ESTAR CACHEADO EN EL STEP!
		int g = 0;
		Step currentStep = step;
		while(currentStep != null)
		{
			g += getStepAcumCost(currentStep);
			currentStep = currentStep.padre;
		}
		
		int h = Math.abs(destino.getX() - step.nodoActual.getX()) - Math.abs(destino.getY() - step.nodoActual.getY());
		return g + h;
	}
}
