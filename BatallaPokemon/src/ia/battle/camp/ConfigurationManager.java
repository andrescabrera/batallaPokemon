package ia.battle.camp;

public final class ConfigurationManager {

	private static ConfigurationManager instance = new ConfigurationManager();

	public static ConfigurationManager getInstance() {
		return instance;
	}

	public int getMaxPointsPerWarrior() {
		return 10000;
	}
	
	public int getMapHeight() {
		return 30;
	}
	
	public int getMapWidth() {
		return 30;
	}
	
	public int getTurnsToShrink() {
		return 5000;
	}
	
	
}
