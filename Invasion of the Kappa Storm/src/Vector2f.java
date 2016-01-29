/* 
	File Name: Vector2f.java
	Name: Alexander Lu
	Date: Match 27, 2015
	Description: This class holds the necessary information and functions for vectors in a 2 dimensional plane
*/

public class Vector2f {
	
	//Fields
	public float x;
	public float y;
	
	//Constructor
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	//Accessor and mutator
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	//Other methods
	
	public void addX(float step){
		x += step;
	}
	
	public void addY(float step){
		y += step;
	}
	
	public void addVector(Vector2f other){
		this.x += other.x;
		this.y += other.y;
	}
	
	public float getDifference(Vector2f other){
		return (float) Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
	}
	
	public String toString(){
		return (x + " " + y);
	}
}