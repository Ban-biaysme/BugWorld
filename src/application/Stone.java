package application;

public class Stone implements BugWorldObject{

	double x, y;
	String imagePath;
	
	public Stone(double x, double y, String imagePath) {
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
	}
	
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
