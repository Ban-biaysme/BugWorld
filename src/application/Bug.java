package application;

public class Bug implements BugWorldObject{
	
	/**
	 * x and y variable are generate the position on the screen
	 * imagePath holds the image location
	 * randomDir to move the bug in different direction
	 */
	private double x;
	private double y;
	private String imagePath;
	private int randomDir;
	
	public Bug(double x, double y, String imagePath) {
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
		this.randomDir=(int) (Math.random()*7)+1;
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
	
	public int getRandomDir() {
		return randomDir;
	}

	public void setRandomDir(int randomDir) {
		this.randomDir = randomDir;
	}

}
