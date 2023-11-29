import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.geometry.*;
import javafx.scene.text.Font;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class WildGuessDemo extends Application {
    Label text;
    Label geography;
    Label characteristics;
    int sideWidth = 235;
    

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Image image = new Image("file:resources/question.gif");
        ImageView imageView = new ImageView(image);




/*left buttons*/
        //continents button
        Button continentB = new Button("Continent   ▾");
        ContextMenu continentMenu = new ContextMenu();
        for (String continent : new String[]{"Africa", "Antarctica", "Asia", "Europe", "North America", "Oceania", "South America"}) {
            MenuItem contItem = new MenuItem(continent);
            contItem.setOnAction(e -> {
                System.out.println("Selected continent: " + contItem.getText());
                // You can perform other actions here based on the selected item
            });
            continentMenu.getItems().add(contItem);
        }
        // Toggle ContextMenu when the button is clicked
        continentB.setOnAction(e -> {
            if(continentMenu.isShowing()) {
                continentMenu.hide();
            } else {
                continentMenu.show(continentB, Side.BOTTOM, 0, 0);
            }
        });


        //habitat button
        Button habitatB = new Button("Habitat   ▾");
        ContextMenu habitatMenu = new ContextMenu();
        for (String habitat : new String[]{"Forest", "Grassland", "Desert", "Tundra", "Freshwater", "Marine", "Mountain", "Urban"}) {
            MenuItem habitatItem = new MenuItem(habitat);
            habitatItem.setOnAction(e -> {
                System.out.println("Selected habitat: " + habitatItem.getText());
                // You can perform other actions here based on the selected item
            });
            habitatMenu.getItems().add(habitatItem);
        }
        // Toggle ContextMenu when the button is clicked
        habitatB.setOnAction(e -> {
            if(habitatMenu.isShowing()) {
                habitatMenu.hide();
            } else {
                habitatMenu.show(habitatB, Side.BOTTOM, 0, 0);
            }
        });


/*right buttons*/
        //size button
        Button sizeB = new Button("Size   ▾");
        ContextMenu sizeMenu = new ContextMenu();
        for (String size : new String[]{"X small", "Small", "Medium", "Large", "X large"}) {
            MenuItem sizeItem = new MenuItem(size);
            sizeItem.setOnAction(e -> {
                System.out.println("Animal size: " + sizeItem.getText());
                // You can perform other actions here based on the selected item
            });
            sizeMenu.getItems().add(sizeItem);
        }
        // Toggle ContextMenu when the button is clicked
        sizeB.setOnAction(e -> {
            if(sizeMenu.isShowing()) {
                sizeMenu.hide();
            } else {
                sizeMenu.show(sizeB, Side.BOTTOM, 0, 0);
            }
        });

        //color button
        Button colorB = new Button("Color   ▾");
        ContextMenu colorMenu = new ContextMenu();
        for (String color : new String[]{"White", "Black", "Brown", "Green", "Blue"}) {
            MenuItem colorItem = new MenuItem(color);
            colorItem.setOnAction(e -> {
                System.out.println("Animal color: " + colorItem.getText());
                // You can perform other actions here based on the selected item
            });
            colorMenu.getItems().add(colorItem);
        }
        // Toggle ContextMenu when the button is clicked
        colorB.setOnAction(e -> {
            if(colorMenu.isShowing()) {
                colorMenu.hide();
            } else {
                colorMenu.show(colorB, Side.BOTTOM, 0, 0);
            }
        });


        //top borderPane
        HBox top = new HBox();
        top.setAlignment(Pos.TOP_CENTER);
        text = new Label("GUESS THE ANIMAL!");
        text.setFont(new Font("Arial", 24));
        root.setTop(top);
        top.getChildren().addAll(text);
        top.getStyleClass().add("top-borderpane");


        //left borderPane
        VBox left = new VBox();
        left.getStyleClass().add("side-borderpane");
        left.setPrefWidth(sideWidth);

        HBox geo = new HBox();
        geo.setPadding(new Insets(10, 0, 10, 0)); //top, right, bottom, left
        geo.setSpacing(10);

        geography = new Label("Geography:");
        geography.setFont(new Font("Arial", 18));


        geo.getChildren().addAll(continentB, habitatB);
        left.getChildren().addAll(geography, geo);
        left.setAlignment(Pos.TOP_CENTER);
        root.setLeft(left);



        //right borderPane
        VBox right = new VBox();
        right.getStyleClass().add("side-borderpane");
        right.setPrefWidth(sideWidth);

        HBox chara = new HBox();
        chara.setPadding(new Insets(10, 0, 10, 0)); //top, right, bottom, left
        chara.setSpacing(10);

        characteristics = new Label("Characteristics:");
        characteristics.setFont(new Font("Arial", 18));


        chara.getChildren().addAll(sizeB, colorB);
        right.getChildren().addAll(characteristics, chara);
        right.setAlignment(Pos.TOP_CENTER);
        root.setRight(right);



        //center borderPane
        Button guessB = new Button("Guess");
        guessB.getStyleClass().add("guess-b");
        guessB.setPrefHeight(40);
        HBox hText = new HBox();
        hText.setSpacing(5);
        hText.setAlignment(Pos.BOTTOM_CENTER);
        TextField guess = new TextField();
        guess.setFont(new Font("Arial", 14));
        guess.setPromptText("Guess the animal...");
        guess.setPrefWidth(800);
        guess.setPrefHeight(40);
        VBox center = new VBox();
        center.setSpacing(50);
        center.setPadding(new Insets(10, 10, 10, 10));
        center.getStyleClass().add("center-borderpane");
        imageView.setFitHeight(300); // Set height
        imageView.setFitWidth(300);  // Set width
        imageView.setPreserveRatio(true); // Preserve the aspect ratio
        hText.getChildren().addAll(guess, guessB);
        center.getChildren().addAll(imageView, hText);
        center.setAlignment(Pos.TOP_CENTER);
        root.setCenter(center);





        //scene
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setTitle("Wild Guess!");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        scene.getStylesheets().add("style.css");
    }

    public static void main(String[] args) {
        launch(args);
    }
}