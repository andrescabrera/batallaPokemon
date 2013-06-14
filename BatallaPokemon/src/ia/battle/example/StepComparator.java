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
	   return a.fCost - b.fCost;
    }	
}
