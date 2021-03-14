package application;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class AnimatedCirclesMain extends Application{

	private ArrayList<Circle> circlelist = new ArrayList<>();
	float x=100, y=100;
	float dx= -1.5f, dy=-1.5f;
	
	final int HEIGHT= 400, WIDTH = 400;
	
	Group viewGroup = new Group();
	VBox vbox = new VBox();
	
	Timeline timeline = new Timeline();
	 double distanceNextY=1, distanceNextX=1;
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	Circle circle=new Circle(100,100,30, Color.AQUA);
    	Circle circle1=new Circle(200,150,50, Color.DIMGREY);
    	Circle circle2=new Circle(300,200,30, Color.CORAL);
    	circlelist.add(circle);
    	circlelist.add(circle1);
    	circlelist.add(circle2); 
    	
    	try {
			for (Circle c : circlelist) {
					viewGroup.getChildren().addAll(c);
					KeyFrame frame= getFrame(c);
					timeline.getKeyFrames().add(frame);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	final Scene scene = new Scene(viewGroup, HEIGHT, WIDTH, Color.BLACK);

        primaryStage.setTitle("A N I M A T E D  C I R C L E S");
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
		
        //Show the window
		primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    private KeyFrame getFrame(Circle circle) {			
		//give the number inside keyFrame for move the bugs
		KeyFrame frame= new KeyFrame(Duration.millis(20), e-> {

			if(circle.getCenterX()+circle.getTranslateX()< circle.getRadius() ||circle.getCenterX()+circle.getTranslateX()+circle.getRadius() > 400){
    			dx=-dx;;
    		}
    		
    		if(circle.getCenterY()+circle.getTranslateY()< circle.getRadius() ||circle.getCenterY()+circle.getTranslateY()+circle.getRadius() > 400){
    			dy=-dy;;
    		}
    		circle.setTranslateX(circle.getTranslateX() + dx);
    		circle.setTranslateY(circle.getTranslateY() + dy);
		});
		
		return frame;
	}
    public static void main(String[] args) {
        Application.launch(args);
    }
}