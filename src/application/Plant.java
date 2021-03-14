package application;

public class Plant implements BugWorldObject{
	
	/**
	 * x and y variable are generate the position on the screen
	 * imagePath holds the image location
	 */
	
	private double x;
	private double y;
	private String imagePath;
	
	
	public Plant(double x, double y, String imagePath) {
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
	}
	
	/**
	 * Getter and setter methods will give access of these private variables from the other classes
	 */
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
