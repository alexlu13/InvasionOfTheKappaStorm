/* 
	File Name: Player.java
	Name: Alexander Lu
	Date: Match 27, 2015
	Description: This class is the basis for a Player unit, which extends from Unit
*/

import java.util.*;

public class Player extends Unit{

	private static int points = 0;
	private static final int IMAGE_ICON = 0;
	private static final float DEFAULT_STEP = GUI.getXResolution() * 0.00875f;
	private static final int MAX_HEALTH = 10;
	
	
	private static long startTime;
	private static long endTime;
	private static boolean updatedEndTime = false;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	
	public Player(float xCor, float yCor, float size) {
		super(xCor, yCor, size, DEFAULT_STEP, MAX_HEALTH);
		startTime = GUI.getTime();
		endTime = GUI.getTime();
		for (int i = 0; i < 3; i++){
			addBomb();
		}
	}
	
	//Movement methods
	//Note: these methods also checks to make sure that the player does not go out of bounds, hence the reason they are
	//overwritten

	public static void addPoints(){
		points++;
	}
	
	public static int getPoints(){
		return points;
	}
	
	public static long getTimeSurvived(){
		
		if (GUI.isDead() && !updatedEndTime){
			endTime = GUI.getTime();
			updatedEndTime = true;
		}
		
		if (GUI.isDead()){
			return endTime - startTime;
		}
		return GUI.getTime() - startTime;
	}
	
	public static void setPoints(int pnts){
		points = pnts;
	}
	
	public void moveUp() {
		
		ArrayList<PlayerHelper> helpers = GameHandler.getPlayerHelpers();
		
		if (!(getY() + step > GUI.getYResolution() - size / 2.0f)){
			coordinates.addY(step);
			
			for (int i = 0; i < helpers.size(); i++){
				helpers.get(i).moveUp(step);
			}
		}
	}

	public void moveDown() {
		
		ArrayList<PlayerHelper> helpers = GameHandler.getPlayerHelpers();
		
		if (!(getY() - step < size / 2.0f)){
			coordinates.addY(-step);
			
			for (int i = 0; i < helpers.size(); i++){
				helpers.get(i).moveDown(getStep());
			}
		}
	}
	
	public void moveLeft() {

		ArrayList<PlayerHelper> helpers = GameHandler.getPlayerHelpers();
		
		if (!(getX() - step < size / 2.0f)){
			coordinates.addX(-step);
			
			for (int i = 0; i < helpers.size(); i++){
				helpers.get(i).moveLeft(step);
			}
		}
	}

	public void moveRight() {
		
		ArrayList<PlayerHelper> helpers = GameHandler.getPlayerHelpers();
		
		if (!(getX() + step > GUI.getXResolution() - size / 2.0f)){
			coordinates.addX(step);
			
			for (int i = 0; i < helpers.size(); i++){
				helpers.get(i).moveRight(step);
			}
		}
	}

	public void slowDown(boolean slow){
		if (slow){
			step = DEFAULT_STEP / 2;
		}else{
			step = DEFAULT_STEP;
		}
	}
	
	//Shoot a bullet and add it to the array list
	public void shootBullet(){
		bullets.add(new Bullet (coordinates.getX() + size / 2.0f + Bullet.getDefaultSize() / 2.0f, coordinates.getY(), 2, Bullet.getDefaultSize(), false));
	}
	
	public void shootBulletFromHelper(PlayerHelper helper){
		bullets.add(new Bullet (helper.getX() + helper.getSize() / 2.0f + Bullet.getDefaultSize() / 2.0f, helper.getY(), 1, Bullet.getDefaultSize() / 2.0f, false));
	}
	
	public void shootDirectionalBulletFromHelper(PlayerHelper helper, float dir){
		bullets.add(new DirectionalBullet (helper.getX() + (float)(Math.cos(dir) * helper.getSize()), helper.getY() + (float)(Math.sin(dir) * helper.getSize()), 2, Bullet.getDefaultSize() / 2.0f, false, dir, Bullet.getDefaultStep()));
	}
	
	public void shootDirectionalBullet(DirectionalBullet shot){
		bullets.add(shot);
	}
	
	public void shootDirectionalBullet(float dir){
		bullets.add(new DirectionalBullet (coordinates.getX() + (float)(Math.cos(dir) * size), coordinates.getY() + (float)(Math.sin(dir) * size), 2, Bullet.getDefaultSize(), false, dir, Bullet.getDefaultStep()));
	}
	
	public void resetBullets(){
		bullets = new ArrayList<Bullet>();
	}
	
	public void resetBombs(){
		for (int i = 0; i < 3; i++){
			addBomb();
		}
	}
	
	public void addBomb(){
		bombs.add(new Bomb());
	}
	
	public void useBomb(){
		if (bombs.size() > 0){
			bombs.get(bombs.size() - 1).detonate();
			bombs.remove(bombs.get(bombs.size() - 1));
		}
	}
	
	public ArrayList getBombs(){
		return bombs;
	}
	
	//Update the bullets
	public void updateBullets(){
		for (int i = 0; i < bullets.size(); i++){
			bullets.get(i).move();
		}
	}
	
	//Accessor for bullets
	public ArrayList<Bullet> getBullets(){
		return bullets;
	}
	
	//Remove bullet from array list
	public void removeBullet(Bullet bullet){
		bullets.remove(bullet);
	}

	//Receive damage
	public boolean damage(){
		health--;
		
		if(health > 0){
			return false;
		}else{
			return true;
		}
	}
}
