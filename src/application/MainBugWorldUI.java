package application;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainBugWorldUI extends Application {

	// things array list hold all the objects.ise insects, plants , obstacle etc.
	private ArrayList<BugWorldObject> things = new ArrayList<>();

	// List of bugs for animation fade effects
	private ArrayList<ImageView> bugImages = new ArrayList<ImageView>();

	Text text = new Text();
	Button addBugs = new Button();
	Button addPlants = new Button();
	Button addStones = new Button();
	Button movebug = new Button();
	Button clear = new Button();
	Button pressnhold = new Button();
	Button transition = new Button();

	Pane pane = new Pane();
	Group viewGroup = new Group();

	public static final int WINDOW_WIDTH = 850;
	public static final int WINDOW_HEIGHT = 600;

	public static final int IMG_WIDTH = 40;
	public static final int IMG_HEIGHT = 40;
	
	private Timeline timeline = new Timeline();
	double distanceNextY = 1, distanceNextX = 1;

	//Call add button and add things methods
	public MainBugWorldUI() {
		addButtons();
		addThings();
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("A  N I  M  A  T  E  D   B U G   W  O  R  L D");

		try {
			Image img = new Image(
					new FileInputStream("C:\\Users\\Biyas-Anirban\\eclipse-workspace\\BugWorldGUI\\bug.png"));
			ImageView imgview = new ImageView(img);

			imgview.setFitHeight(50);
			imgview.setFitWidth(50);
			imgview.setX(750);
			imgview.setY(10);

			pane.getChildren().addAll(text, imgview, addBugs, addPlants, movebug, addStones, clear, pressnhold,
					transition);

			// Add bugs on the window
			addBugs.setOnAction(action -> {
				addBug();
			});

			// add Plants on the window
			addPlants.setOnAction(action -> {
				addPlant();
			});

			// Move the bugs on the window
			movebug.setOnAction(action -> {
				moveBugs();
			});

			// Add stone by pressing the button
			addStones.setOnAction(action -> {
				addStones();
			});

			// Clear pane by using clear button
			clear.setOnAction(action -> {
				clearPane();
			});

			// Control movements of bugs
			pressnhold.setOnAction(action -> {
				controllMovements();
			});

			// Control movements of bugs
			transition.setOnAction(action -> {
				transition();
			});

			// divide the VBox in to two part, pane and viewport. None of them have more priority
			VBox.setVgrow(pane, Priority.NEVER);
			VBox.setVgrow(viewGroup, Priority.NEVER);

			// Set VBox pane height and width
			pane.setPrefHeight(100);
			pane.setPrefWidth(800);

			//Add pane and group in the parent VBox
			VBox vbox = new VBox(pane, viewGroup);
			
			//set ID for apply CSS style
			pane.setId("vbox1");
			vbox.setId("vbox2");

			// Create a new scene with fixed width and height.
			Scene scene = new Scene(vbox, WINDOW_WIDTH, WINDOW_HEIGHT);

			// Connect the CSS file to make some styling the elements
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addButtons() {

		text.setText("A N I M A T E D    B U G    W O R L D");
		text.setId("fancytext");
		text.setX(60);
		text.setY(40);

		InnerShadow is = new InnerShadow();
		is.setOffsetX(4.0f);
		is.setOffsetY(4.0f);
		text.setEffect(is);
		text.setFill(Color.AQUA);

		addBugs.setText("ADD BUGS");
		addBugs.setId("fancybtn");
		addBugs.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		addBugs.setLayoutX(10);
		addBugs.setLayoutY(60);

		addPlants.setText("ADD PLANTS");
		addPlants.setId("fancybtn2");
		addPlants.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		addPlants.setLayoutX(115);
		addPlants.setLayoutY(60);

		movebug.setText("MOVE BUGS");
		movebug.setId("fancybtn3");
		movebug.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		movebug.setLayoutX(230);
		movebug.setLayoutY(60);

		addStones.setText("ADD STONES");
		addStones.setId("fancybtn4");
		addStones.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		addStones.setLayoutX(340);
		addStones.setLayoutY(60);

		clear.setText("CLEAR PANE");
		clear.setId("fancybtn5");
		clear.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		clear.setLayoutX(460);
		clear.setLayoutY(60);

		pressnhold.setText("PRESS N HOLD");
		pressnhold.setId("fancybtn6");
		pressnhold.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		pressnhold.setLayoutX(580);
		pressnhold.setLayoutY(60);

		transition.setText("TRANSITION");
		transition.setId("fancybtn7");
		transition.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		transition.setLayoutX(720);
		transition.setLayoutY(60);

	}

	// addThings method add the bugs, plants and stones in the main array list
	public void addThings() {
		
		double x = 100;
		double y = 150;
		double tree_y_pos = 600;

		// Add butterfly and Ants to the list
		for (int j = 0; j < 2; j++) {
			things.add(new Butterfly((x * j) + 60, (y * j) + 200, "Images/butterfly.jpg"));
		}

		for (int i = 0; i < 2; i++) {
			things.add(new Ant((x * i) + 200, (y * i) + 200, "Images/ant.jpg"));
		}

		// Add lady bugs to the list
		things.add(new LadyBug(100, 400, "Images/ladybug.jpg"));
		things.add(new LadyBug(350, 280, "Images/ladybug.jpg"));

		// Add Bee to the list
		things.add(new Bee(450, 450, "Images/bee.png"));

		// Add trees to the list
		things.add(new Tree(50, tree_y_pos, "Images/tree.png"));
		things.add(new Tree(200, tree_y_pos, "Images/tree.jpg"));
		things.add(new Tree(350, tree_y_pos, "Images/tree.png"));
		things.add(new Tree(500, tree_y_pos, "Images/tree.jpg"));
		things.add(new Tree(650, tree_y_pos, "Images/tree.png"));
		things.add(new Tree(800, tree_y_pos, "Images/tree.jpg"));

		// Add grasses to the list
		things.add(new Grass(400, 350, "Images/grass.png"));
		things.add(new Grass(450, 300, "Images/grass.png"));
		things.add(new Grass(230, 480, "Images/grass.png"));
		things.add(new Grass(150, 250, "Images/grass.png"));

		// Add obstacles
		things.add(new Stone(600, 480, "Images/stone.jpg"));
		things.add(new Stone(450, 500, "Images/stone.jpg"));
		things.add(new Stone(700, 600, "Images/stone.jpg"));
	}

	// Add bugs on the window pane
	public void addBug() {
			try {
				for (BugWorldObject obj : things) {
					ImageView iv = getImage(obj);
					if (obj instanceof Bug) {
						//bugImages array list used to play and pause the bugs on the screen
						bugImages.add(iv);
						viewGroup.getChildren().add(iv);
						KeyFrame frame = getFrame(iv, (Bug) obj);
						timeline.getKeyFrames().add(frame);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	// Add plants on the window pane
	public void addPlant() {
		try {
			for (BugWorldObject obj : things) {
				ImageView iv = getImage(obj);
				if (obj instanceof Plant) {
					viewGroup.getChildren().add(iv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Stones on the window pane
	public void addStones() {
		try {
			for (BugWorldObject obj : things) {
				ImageView iv = getImage(obj);
				if (obj instanceof Stone) {
					viewGroup.getChildren().add(iv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Clear all objects from the pane
	public void clearPane() {
		try {
			viewGroup.getChildren().removeAll(viewGroup.getChildren());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// move the bugs in random direction on the scene
	public void moveBugs() {
		try {
			//Timeline class used for display animation on scene
			Timeline timeline = new Timeline();

			for (BugWorldObject obj : things) {
				ImageView iv = getImage(obj);
				viewGroup.getChildren().add(iv);

				if (obj instanceof Bug) {
					// Only sets the bugs on the key frame
					// getFrame method get the frame for the bugs and add them to the Timeline
					KeyFrame frame = getFrame(iv, (Bug) obj);
					timeline.getKeyFrames().add(frame);
				}

			}
			// Make the animation continuously
			timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
			timeline.play();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create images at the bug position and bug image path
	// Helper method only access from this class

	private ImageView getImage(BugWorldObject obj) {
		try {

			FileInputStream input = new FileInputStream(obj.getImagePath());
			Image image = new Image(input);
			
			// add image with image view to display the images on the pane
			ImageView imgview = new ImageView(image);

			// Get image X and Y position from the class and set it with image view to
			// display on the scene.
			imgview.setX(obj.getX());
			imgview.setY(obj.getY());

			imgview.setFitHeight(IMG_WIDTH);
			imgview.setFitWidth(IMG_HEIGHT);

			return imgview;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// helper method will move the bugs on the screen
	private KeyFrame getFrame(ImageView iv, Bug b) {

		KeyFrame frame = new KeyFrame(Duration.millis(15), e -> {

			//Bounds
			Bounds boundsInScene = iv.localToScene(iv.getBoundsInLocal());
			double distanceNextY = 0;
			double distanceNextX = 0;
			
			int r_direction = b.getRandomDir();

			if (r_direction == 1) {
				distanceNextY = -1;
				distanceNextX = 0;

			} else if (r_direction == 2) {
				distanceNextY = -1;
				distanceNextX = 1;

			} else if (r_direction == 3) {
				distanceNextX = 1;
				distanceNextY = 0;

			} else if (r_direction == 4) {
				distanceNextY = 1;
				distanceNextX = 1;

			} else if (r_direction == 5) {
				distanceNextX = 0;
				distanceNextY = 1;

			} else if (r_direction == 6) {
				distanceNextX = -1;
				distanceNextY = 1;

			} else if (r_direction == 7) {
				distanceNextY = 0;
				distanceNextX = -1;

			} else if (r_direction == 8) {
				distanceNextY = -1;
				distanceNextX = -1;
			}

			if (boundsInScene.getMinY() <= 200 && (r_direction == 8 || r_direction == 1 || r_direction == 2)) {

				b.setRandomDir((int) (Math.random() * 7) + 1);

			} else if (boundsInScene.getMinX() >= 550 && (r_direction == 2 || r_direction == 3 || r_direction == 4)) {

				b.setRandomDir((int) (Math.random() * 7) + 1);

			} else if (boundsInScene.getMinY() >= 500 && (r_direction == 4 || r_direction == 5 || r_direction == 6)) {

				b.setRandomDir((int) (Math.random() * 7) + 1);

			} else if (boundsInScene.getMinX() < 50 && (r_direction == 6 || r_direction == 7 || r_direction == 8)) {

				b.setRandomDir((int) (Math.random() * 7) + 1);
			}

			b.setY(boundsInScene.getMinY());
			b.setX(boundsInScene.getMinX());

			// This will move the bug within the frame
			//set the image new position after alter the location of a node
			iv.setTranslateX(iv.getTranslateX() + distanceNextX);
			iv.setTranslateY(iv.getTranslateY() + distanceNextY);
		});
		return frame;

	}
	// Control the animation by PLAY and PAUSE
	// while pressing the button the bugs will move
	// Bugs stop moving while release the button

	public void controllMovements() {
		if (bugImages.isEmpty()) {
			addBug();
		}
		
		try {
			timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);

			pressnhold.pressedProperty().addListener((observable, wasPressed, pressed) -> {
				
				System.out.println("button changed");
				if (pressed) {
					timeline.play();
				} else {
					timeline.pause();
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Fade out the bugs while click the button in every 3 seconds
	// fade transition also give the fade effect on the main heading
	
	public void transition() {
		try {
			for (ImageView iv : bugImages) {
				FadeTransition ft = new FadeTransition(Duration.millis(3000), iv);
				ft.setFromValue(2.0);
				ft.setToValue(0.1);
				ft.setCycleCount(Timeline.INDEFINITE);
				ft.setAutoReverse(true);
				ft.play();
			}

			FadeTransition ft = new FadeTransition(Duration.millis(3000), text);
			ft.setFromValue(1.0);
			ft.setToValue(0.2);
			ft.setCycleCount(Timeline.INDEFINITE);
			ft.setAutoReverse(true);
			ft.play();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
