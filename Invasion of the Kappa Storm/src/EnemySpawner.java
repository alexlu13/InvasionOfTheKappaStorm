
public class EnemySpawner {
	
	private static final int ZIG_ZAG_ENEMY = 0;
	private static final int WAVE_ENEMY = 1;
	private static final int ACCELERATING_STRAIGHT_ENEMY = 2;
	private static final int NON_ACCELERATING_STRAIGHT_ENEMY = 3;
	private static long prevTime;
	
	private static final int MIN_SPAWN_DELAY = 5;
	
	//Note: the spawn delay is measured in frames
	private static long spawnDelay = 60 * GUI.getMaxFPS();
	
	public static void setPrevTime(){
		prevTime = GUI.getTime();
	}
	
	public static void updateSpawnDelay(){	

		if (spawnDelay >= MIN_SPAWN_DELAY * GUI.getMaxFPS()){
			spawnDelay = 60 * GUI.getMaxFPS() - ((Player)GameHandler.getPlayer()).getPoints() / 5 * GUI.getMaxFPS();
		}
	}
	
	public static void resetSpawnDelay(){
		spawnDelay = 60 * GUI.getMaxFPS();
	}
	
	//Spawns a squadron of wave enemies of the indicated size
	//Note: then will spawn in one at a time from the same y-coordinate
	public static void spawnWaveSquadronOnSameYCoordinate(int size){
		
		float startingYCoordinate = (float) (Math.random() * (GUI.getYResolution() - 200) + 125);
	
		for (int i = 0; i < size; i++){
			EnemyHandler.spawnEnemy(WAVE_ENEMY, WaveEnemy.getDefaultSize(), GUI.getXResolution() + WaveEnemy.getDefaultSize() * i, startingYCoordinate, -1);
		}
	}
	
	//Note: they will spawn in all at once on the same x-coordinate
	public static void spawnWaveSquadronOnSameXCoordinate(int size){
		float startingYCoordinate = (float) (Math.random() * GUI.getYResolution() + 1);
		
		for (int i = 0; i < size; i++){
			EnemyHandler.spawnEnemy(WAVE_ENEMY, WaveEnemy.getDefaultSize(), GUI.getXResolution() + WaveEnemy.getDefaultSize(), startingYCoordinate + WaveEnemy.getDefaultSize() * i, -1);
		}
	}
	
	public static void spawnAcceleratingSquadronOnSameXCoordinate(){
		
		for (int i = 0; i < GUI.getYResolution() / AcceleratingStraightEnemy.getDefaultSize(); i++){
			EnemyHandler.spawnEnemy(ACCELERATING_STRAIGHT_ENEMY, AcceleratingStraightEnemy.getDefaultSize(), GUI.getXResolution() + AcceleratingStraightEnemy.getDefaultSize() / 2.0f, i * AcceleratingStraightEnemy.getDefaultSize(), AcceleratingStraightEnemy.getDefaultStep() * i * -4);
		}
	}
	
	public static void spawnNonAcceleratingSquadronInCirclePattern(){
		float startingYCoordinate = (float)(Math.random() * GUI.getYResolution()) + 1;
		float startingXCoordinate = (float)(Math.random() * GUI.getXResolution()) + GUI.getXResolution() / 2;
		
		for (int i = 0; i < 360; i+=10){
			//EnemyHandler.spawnEnemy(NON_ACCELERATING_STRAIGHT_ENEMY, NonAcceleratingStraightEnemy.getDefaultSize(), GUI.getXResolution() + NonAcceleratingStraightEnemy.getDefaultSize() / 2.0f, startingYCoordinate, i);
			EnemyHandler.spawnEnemy(NON_ACCELERATING_STRAIGHT_ENEMY, NonAcceleratingStraightEnemy.getDefaultSize(), startingXCoordinate, startingYCoordinate, i);
		}
	}
	
	public static void spawnNonAcceleratingSquadronInCirclePattern(Enemy enemy){
		float startingYCoordinate = enemy.getY();
		float startingXCoordinate = enemy.getX();
		
		for (int i = 0; i < 360; i+=10){
			//EnemyHandler.spawnEnemy(NON_ACCELERATING_STRAIGHT_ENEMY, NonAcceleratingStraightEnemy.getDefaultSize(), GUI.getXResolution() + NonAcceleratingStraightEnemy.getDefaultSize() / 2.0f, startingYCoordinate, i);
			EnemyHandler.spawnEnemy(NON_ACCELERATING_STRAIGHT_ENEMY, NonAcceleratingStraightEnemy.getDefaultSize(), startingXCoordinate, startingYCoordinate, i);
		}
	}
	
	//This method checks if sufficient time has passed for an enemy to spawn, and spawns an enemy if possible
	public static void checkTimeForSpawningEnemy(){

		long curTime = GUI.getTime();
		
		//Check the spawn delay
		if (curTime - prevTime >= spawnDelay){
			
			
			
			prevTime = curTime;
			
			int randomSpawnType = (int) (Math.random() * 4);
			int randomSize = (int) (Math.random() * 3 + 4);
			
			switch(randomSpawnType){
			
			case 0:
				spawnWaveSquadronOnSameYCoordinate(randomSize);
				break;
				
			case 1:
				spawnWaveSquadronOnSameXCoordinate(randomSize);
				break;
				
			case 2:
				spawnAcceleratingSquadronOnSameXCoordinate();
				break;
				
			case 3:
				
				EnemyHandler.spawnEnemy(ZIG_ZAG_ENEMY, ZigZagEnemy.getDefaultSize(), GUI.getXResolution() + ZigZagEnemy.getDefaultSize() / 2.0f, (float)(Math.random() * GUI.getYResolution()) + 1, (int)(Math.random() * 2));
				break;
				
			case 4:
				spawnNonAcceleratingSquadronInCirclePattern();
				break;
			}
		}
	}
	

}
