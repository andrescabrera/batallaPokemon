package ia.battle.example;

import java.util.Comparator;

import ia.battle.camp.FieldCell;

public class StepComparator implements Comparator<Step>
{
	FieldCell destino;
	public StepComparator(FieldCell destino)
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
			g += getStepCost(currentStep);
			currentStep = currentStep.padre;
		}
		
		int h = 10*Math.abs(destino.getX() - step.nodoActual.getX()) - Math.abs(destino.getY() - step.nodoActual.getY());
		return g + h;
	}
	
	private int getStepCost(Step step) {
		int cont = 0;
		if(step.padre == null) return 0;
		if((step.nodoActual.getX() - step.padre.nodoActual.getX()) != 0) cont++;
		if((step.nodoActual.getY() - step.padre.nodoActual.getY()) != 0) cont++;
		if(cont == 1) return 10;
		else if(cont == 2) return 14;
		else return 0;
	}
}
