package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class rayanCheckEquations extends Application {
	private Label rayanlabelpath;
    private Label filePathLabel;

	private Scene rayanStack;
	private Scene secen;

	@Override
	public void start(Stage primaryStage) {

		Label rayanLabel = new Label("Welcome World !!!");
		Image mainImage = new Image("welcome-back_5619946.png");
		ImageView mainImageView = new ImageView(mainImage);
		mainImageView.setFitHeight(100);
		mainImageView.setFitWidth(100);
		HBox rayanhbox = new HBox(210);
		rayanLabel.setStyle("-fx-font-family: 'Verdana'; -fx-font-size: 27px; -fx-font-weight: bold;");

		Label rayanghnimatLabel = new Label("Rayan Hamayel");
		rayanghnimatLabel.setStyle("-fx-font-family: 'Verdana'; -fx-font-size: 14px; -fx-font-weight: bold;");

		Label rayanNumber = new Label("1211073");
		rayanNumber.setStyle("-fx-font-family: 'Verdana'; -fx-font-size: 14px; -fx-font-weight: bold;");

		Label rayanDr = new Label("Dr Murad Njoum");
		rayanDr.setStyle("-fx-font-family: 'Verdana'; -fx-font-size: 14px; -fx-font-weight: bold;");

		VBox rayan2vbox = new VBox(20);
		rayan2vbox.setAlignment(Pos.CENTER);
		rayan2vbox.getChildren().addAll(rayanghnimatLabel, rayanNumber, rayanDr);
		Image mainImageStack = new Image("R (1).png");
		ImageView mainImageViewStack = new ImageView(mainImageStack);
		mainImageViewStack.setFitHeight(200);
		mainImageViewStack.setFitWidth(700);
		Button MainButton = new Button("Main Button");
		MainButton.setStyle("-fx-background-color: red; -fx-text-fill: white;-fx-font-size: 12;");

		VBox rayav = new VBox(60);
		rayav.getChildren().addAll(rayanLabel, rayan2vbox);
		VBox vboxes = new VBox(80);
		rayanhbox.getChildren().addAll(mainImageView, rayav);
		rayanhbox.setAlignment(Pos.TOP_LEFT);
		HBox hboxes = new HBox(30);

		vboxes.getChildren().addAll(rayanhbox, mainImageViewStack, MainButton);

		vboxes.setAlignment(Pos.CENTER);
//******************************************************
		VBox root = new VBox(); // Use VBox instead of AnchorPane
		root.setPrefSize(800, 600);

		Label fileLabel = new Label("Select File :");

		TextField pathField = new TextField();
		pathField.setLayoutX(70);

		pathField.setEditable(false);

		rayanlabelpath = new Label();
		pathField.setPrefSize(500, 26);
		TextArea equationTextArea = new TextArea(); // Use TextArea instead of Label
		equationTextArea.setPrefSize(500, 500);
		equationTextArea.setStyle("-fx-background-color: red;"); // Set background color for TextArea
		equationTextArea.setEditable(false);
		Button loadButton = new Button("Load");
		loadButton.setOnAction(event -> loadFileContents(pathField, equationTextArea));
		Image prevImage = new Image("icons8-previous-50.png"); // Replace with your actual image file path
		ImageView prevImageView = new ImageView(prevImage);
		prevImageView.setFitWidth(50);
		prevImageView.setFitHeight(30);
		Button prevButton = new Button("", prevImageView);
		prevButton.setPrefSize(100, 40);
		prevButton.setOnAction(event -> rayanController.showPrev(equationTextArea));
		prevButton.setStyle("-fx-background-color: #FFE7C1;");

		Image nextImage = new Image("icons8-next-64.png"); // Replace with your actual image file path
		ImageView nextImageView = new ImageView(nextImage);
		nextImageView.setFitWidth(50);
		nextImageView.setFitHeight(30);
		Button nextButton = new Button("", nextImageView);
		nextButton.setPrefSize(100, 40);
		nextButton.setOnAction(event -> rayanController.showNext(equationTextArea));
		nextButton.setStyle("-fx-background-color: #FFE7C1;");
		Image prevImage2 = new Image("icons8-back-100.png"); // Replace with your actual image file path
		ImageView prevImageView2 = new ImageView(prevImage2);
		Image prevImage3 = new Image("icons8-next-100 (2).png"); // Replace with your actual image file path
		ImageView prevImageView3 = new ImageView(prevImage3);
		Button nextbtn = new Button("", prevImageView3);
		
		Button prevtbtn = new Button("", prevImageView2);
		HBox errosHbox = new HBox(30);
		errosHbox.getChildren().addAll(prevtbtn, equationTextArea, nextbtn);
		errosHbox.setAlignment(Pos.CENTER);
		HBox fileRow = new HBox(fileLabel, pathField);
		fileRow.setSpacing(30);
		HBox buttonsRow = new HBox(prevButton, nextButton);
		VBox buttonsRowvbox = new VBox(fileRow, loadButton);
		buttonsRowvbox.setSpacing(20);
		buttonsRow.setAlignment(Pos.BASELINE_CENTER);
		
		buttonsRow.setSpacing(320);
		root.getChildren().addAll(buttonsRowvbox, rayanlabelpath, errosHbox, buttonsRow);

		root.setSpacing(50);

		Scene scene = new Scene(vboxes, 900, 600);
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(scene);
		primaryStage.show();
		MainButton.setOnAction(e -> {
			rayanStack = new Scene(root, 900, 600);
			primaryStage.setTitle("Check Stack");
			primaryStage.setScene(rayanStack);
			root.setStyle("-fx-background-color: #D2E3C8;");
			primaryStage.show();

		});
		nextbtn.setOnAction(e -> {
	        // Ensure that secen is properly initialized with a valid Scene instance
	        if (scene != null) {
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } else {
	            System.out.println("Error: secen is not initialized.");
	        }
	    });
		nextbtn.setOnAction(e -> {
	        // Ensure that secen is properly initialized with a valid Scene instance
	        if (scene != null) {
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } else {
	            System.out.println("Error: secen is not initialized.");
	        }
	    });
		
	}
	


	private void loadFileContents(TextField pathField, TextArea equationTextArea) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {
			String path = selectedFile.getAbsolutePath();
			pathField.setText(path);
			rayanlabelpath.setText("Selected File: " + path);
			rayanController.loadFileContents(pathField, equationTextArea);
		} else {
			rayanlabelpath.setText("Ohh enter a File.");
		}
	}

	public static void main(String[] args) {
		launch();
	}
}