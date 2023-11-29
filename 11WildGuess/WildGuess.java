import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.stage.Modality;


public class WildGuess extends Application {
    Label text;
    int sideWidth = 260;
    private Map<String, String> selectedAnimal; // Holds the characteristics of the randomly selected animal
    private Label guessResult; // Label to display guess results
    private Map<String, List<String>> dataMap;
    BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        Image image = new Image("file:resources/question.gif");
        ImageView imageView = new ImageView(image);

        // UI components setup
        text = new Label("GUESS THE ANIMAL!");
        text.setFont(new Font("Arial", 24));

        Label geography = new Label("Geography:");
        geography.setFont(new Font("Arial", 18));
        //geography.getStyleClass().add("guess-label");

        Label characteristics = new Label("Characteristics:");
        characteristics.setFont(new Font("Arial", 18));
        //characteristics.getStyleClass().add("guess-label");

        Label type = new Label("Type:");
        type.setFont(new Font("Arial", 18));
        type.getStyleClass().add("guess-label");

        Label behavior = new Label("Behavior:");
        behavior.setFont(new Font("Arial", 18));
        behavior.getStyleClass().add("guess-label");

        Label tips = new Label("Tips:");
        tips.setFont(new Font("Arial", 18));
        tips.getStyleClass().add("guess-label");

        // Read CSV and populate context menus
        Map<String, List<String>> animalData = readCsvFile("C:/Users/Tobia/OneDrive/Dokumenter/11WildGuess/resources/animal_data.csv");
        selectRandomAnimal(animalData);
        dataMap = readCsvFile("C:/Users/Tobia/OneDrive/Dokumenter/11WildGuess/resources/animal_data.csv");

        for (String key : dataMap.keySet()) {
    System.out.println(key + " list size: " + dataMap.get(key).size());
}




        if (animalData == null || animalData.isEmpty()) {
    throw new IllegalStateException("No data was read from the CSV file.");
}
selectRandomAnimal(animalData);





        // Initialize the guess result label
        guessResult = new Label();
        guessResult.setFont(new Font("Arial", 16));

        // ContextMenus
        ContextMenu continentMenu = createContextMenu("Continent", animalData.get("Continent"));
        ContextMenu habitatMenu = createContextMenu("Primary Habitat", animalData.get("Primary Habitat"));
        ContextMenu habitat2Menu = createContextMenu("Secondary Habitat", animalData.get("Secondary Habitat"));
        ContextMenu habitatTMenu = createContextMenu("Habitat-Type", animalData.get("Habitat-Type"));
        ContextMenu sizeMenu = createContextMenu("Size", animalData.get("Size"));
        ContextMenu colorMenu = createContextMenu("Color", animalData.get("Color"));

        ContextMenu classMenu = createContextMenu("Class", animalData.get("Class"));
        ContextMenu familyMenu = createContextMenu("Order/Family", animalData.get("Order/Family"));
        ContextMenu skinMenu = createContextMenu("Skin", animalData.get("Skin"));
        ContextMenu limbsMenu = createContextMenu("Limbs", animalData.get("Limbs"));
        ContextMenu dietMenu = createContextMenu("Diet", animalData.get("Diet"));
        ContextMenu groupMenu = createContextMenu("Group-Type", animalData.get("Group-Type"));
        ContextMenu domesMenu = createContextMenu("Domesticated", animalData.get("Domesticated"));
        ContextMenu fLetterMenu = createContextMenu("First Letter", animalData.get("First Letter"));
        ContextMenu lLetterMenu = createContextMenu("Last Letter", animalData.get("Last Letter"));


        // Buttons with ContextMenus
        Button continentB = new Button("Continent ▾");
        continentB.setOnAction(e -> continentMenu.show(continentB, Side.BOTTOM, 0, 0));

        Button habitatB = new Button("Primary Habitat ▾");
        habitatB.setOnAction(e -> habitatMenu.show(habitatB, Side.BOTTOM, 0, 0));
        Button habitat2 = new Button("Secondary Habitat ▾");
        habitat2.setOnAction(e -> habitat2Menu.show(habitat2, Side.BOTTOM, 0, 0));

        Button sizeB = new Button("Size ▾");
        sizeB.setOnAction(e -> sizeMenu.show(sizeB, Side.BOTTOM, 0, 0));
        Button colorB = new Button("Color ▾");
        colorB.setOnAction(e -> colorMenu.show(colorB, Side.BOTTOM, 0, 0));

        Button classB = new Button("Class ▾");
        classB.setOnAction(e -> classMenu.show(classB, Side.BOTTOM, 0, 0));

        Button familyB = new Button("Family/Order ▾");
        familyB.setOnAction(e -> familyMenu.show(familyB, Side.BOTTOM, 0, 0));

        Button skinB = new Button("Skin Type ▾");
        skinB.setOnAction(e -> skinMenu.show(skinB, Side.BOTTOM, 0, 0));

        Button limbsB = new Button("Limbs ▾");
        limbsB.setOnAction(e -> limbsMenu.show(limbsB, Side.BOTTOM, 0, 0));

        Button dietB = new Button("Diet ▾");
        dietB.setOnAction(e -> dietMenu.show(dietB, Side.BOTTOM, 0, 0));

        Button groupB = new Button("Group Type ▾");
        groupB.setOnAction(e -> groupMenu.show(groupB, Side.BOTTOM, 0, 0));

        Button domB = new Button("Domesticated ▾");
        domB.setOnAction(e -> domesMenu.show(domB, Side.BOTTOM, 0, 0));

        Button fLetterB = new Button("First Letter ▾");
        fLetterB.setOnAction(e -> fLetterMenu.show(fLetterB, Side.BOTTOM, 0, 0));

        Button lLetterB = new Button("Last Letter ▾");
        lLetterB.setOnAction(e -> lLetterMenu.show(lLetterB, Side.BOTTOM, 0, 0));







////////////////////////UI///////////////////////////////////////////////
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

        FlowPane familyOrder = new FlowPane();
        familyOrder.setPadding(new Insets(10, 0, 10, 0)); //top, right, bottom, left
        familyOrder.setHgap(10);
        familyOrder.setVgap(10);
        familyOrder.setAlignment(Pos.CENTER);

        FlowPane geo = new FlowPane();
        geo.setPadding(new Insets(10, 0, 10, 0)); //top, right, bottom, left
        geo.setHgap(10);
        geo.setVgap(10);
        geo.setAlignment(Pos.CENTER);

        FlowPane behv = new FlowPane();
        behv.setPadding(new Insets(10, 0, 10, 0)); //top, right, bottom, left
        behv.setHgap(10);
        behv.setVgap(10);
        behv.setAlignment(Pos.CENTER);

        geography = new Label("Geography:");
        geography.setFont(new Font("Arial", 18));

        familyOrder.getChildren().addAll(classB, familyB);
        geo.getChildren().addAll(continentB, habitatB, habitat2);
        behv.getChildren().addAll(dietB, groupB, domB);
        left.getChildren().addAll(geography, geo, type, familyOrder, behavior, behv);
        left.setAlignment(Pos.TOP_CENTER);
        root.setLeft(left);



        //right borderPane
        VBox right = new VBox();
        right.getStyleClass().add("side-borderpane");
        right.setPrefWidth(sideWidth);

        FlowPane chara = new FlowPane();
        chara.setPadding(new Insets(10, 0, 10, 0)); //top, right, bottom, left
        chara.setHgap(10);
        chara.setVgap(10);
        chara.setAlignment(Pos.CENTER);

        FlowPane tipsP = new FlowPane();
        tipsP.setPadding(new Insets(10, 0, 10, 0)); //top, right, bottom, left
        tipsP.setHgap(10);
        tipsP.setVgap(10);
        tipsP.setAlignment(Pos.CENTER);

        characteristics = new Label("Characteristics:");
        characteristics.setFont(new Font("Arial", 18));


        chara.getChildren().addAll(sizeB, colorB, skinB, limbsB);
        tipsP.getChildren().addAll(fLetterB, lLetterB);
        right.getChildren().addAll(characteristics, chara, tips, tipsP);
        right.setAlignment(Pos.TOP_CENTER);
        root.setRight(right);



        //center borderPane
        //textinput
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

        guessB.setOnAction(e -> {
            String userGuess = guess.getText().trim();
            String correctAnimal = selectedAnimal.get("Animal");
            if (userGuess.equalsIgnoreCase(correctAnimal)) {
                showPopup(primaryStage);
            } else {
            // Handle incorrect guess (optional)
            guessResult.setText("Incorrect. Try again!");
            guessResult.setTextFill(Color.RED);
            }
        });
        


        //image
        imageView.setFitHeight(300); // Set height
        imageView.setFitWidth(300);  // Set width
        imageView.setPreserveRatio(true); // Preserve the aspect ratio
        StackPane imageContainer = new StackPane(imageView);
        imageContainer.setAlignment(Pos.TOP_CENTER);




        //aent
        VBox center = new VBox();
        center.setSpacing(50);
        center.setPadding(new Insets(30, 10, 30, 10));
        center.getStyleClass().add("center-borderpane");
        hText.getChildren().addAll(guess, guessB);
        hText.setAlignment(Pos.BOTTOM_CENTER);
        center.getChildren().addAll(imageContainer, guessResult, hText);
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



private Random rand = new Random();

private void selectRandomAnimal(Map<String, List<String>> dataMap) {
    int animalIndex = rand.nextInt(dataMap.get("Animal").size());

    Map<String, String> selectedAnimalAttributes = new HashMap<>();
    for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
        String key = entry.getKey();
        List<String> values = entry.getValue();
        if (!values.isEmpty() && animalIndex < values.size()) {
            selectedAnimalAttributes.put(key, values.get(animalIndex));
        } else {
            selectedAnimalAttributes.put(key, "N/A");
        }
    }

    // Debugging output to verify selectedAnimal is set
    System.out.println("Selected Animal: " + selectedAnimalAttributes);
    this.selectedAnimal = selectedAnimalAttributes; // Make sure you set the class variable
}


private String getAttributeForAnimal(String attributeKey, String animalName, Map<String, List<String>> dataMap) {
    // Implement logic to determine the attribute value based on the animal name
    // This could involve looking up a mapping or applying some logic
    // Placeholder for your implementation
    return findAttributeValueForAnimal(attributeKey, animalName, dataMap);
}

private String findAttributeValueForAnimal(String attributeKey, String animalName, Map<String, List<String>> dataMap) {
    List<String> animals = dataMap.get("Animal");
    for (int i = 0; i < animals.size(); i++) {
        if (animals.get(i).equals(animalName)) {
            List<String> attributeList = dataMap.get(attributeKey);
            if (i < attributeList.size()) {
                return attributeList.get(i);  // This ensures that the attribute matches the animal
            } else {
                return "N/A";  // Or some other default value or error handling
            }
        }
    }
    return "Unknown"; // Default value if no matching animal is found
}





private void validateGuess(String characteristic, MenuItem item) {
    if (selectedAnimal == null) {
        // Handle the case where selectedAnimal has not been initialized
        // For example, initialize it or show an error message
        System.out.println("Error: No animal has been selected yet.");
        return;
    }
    String guess = item.getText();
    // Retrieve the correct data for the characteristic. It may contain multiple, comma-separated values.
    String correctData = selectedAnimal.get(characteristic);
        
    // Split the correct data into an array of valid options.
    String[] validOptions = correctData.split(",\\s*");
    
    // Check if the guess is one of the valid options.
    boolean isCorrect = Arrays.asList(validOptions).contains(guess);
    
    // Update the guess result label accordingly.
    guessResult.setText(isCorrect ? guess + " is correct!" : guess + " is incorrect.");
    guessResult.setTextFill(isCorrect ? Color.GREEN : Color.RED);

        System.out.println("User Guess: " + guess);
    System.out.println("Correct Data: " + correctData);

        String userGuess = item.getText();
    
}






private Map<String, List<String>> readCsvFile(String filename) {
    Map<String, List<String>> dataMap = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line = br.readLine(); // Read the header line
        if (line != null) {
            // Remove BOM and non-printable characters
            line = line.replaceAll("[^\\x20-\\x7E]", "");
            String[] headers = line.split(";");
            for (int i = 0; i < headers.length; i++) {
                headers[i] = headers[i].trim();
                dataMap.put(headers[i], new ArrayList<>());
            }

            while ((line = br.readLine()) != null) {
                // Remove BOM and non-printable characters
                line = line.replaceAll("[^\\x20-\\x7E]", "");
                String[] values = line.split(";");
                for (int i = 0; i < headers.length; i++) {
                    if (i < values.length) {
                        String value = values[i].trim();
                        String[] splitValues = value.split(",\\s*");
                        List<String> currentData = dataMap.get(headers[i]);
                        currentData.addAll(Arrays.asList(splitValues));
                    }
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    // Remove duplicates and empty entries
    for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
        entry.setValue(entry.getValue().stream()
                .filter(s -> !s.isEmpty())
                .distinct()
                .collect(Collectors.toList()));
    }
        //System.out.println(dataMap);
        return dataMap;
    }


private ContextMenu createContextMenu(String characteristic, List<String> options) {
    ContextMenu menu = new ContextMenu();
    if (options != null) {
        for (String option : options) {
            MenuItem item = new MenuItem(option);
            item.setOnAction(e -> validateGuess(characteristic, item));
            menu.getItems().add(item);
        }
    }
    return menu;
}

private void showPopup(Stage primaryStage) {
    // Popup layout
    VBox popupLayout = new VBox(10);
    popupLayout.setAlignment(Pos.CENTER);
    Label message = new Label("Congratulations! You guessed the correct animal! Want to play again?");
    Button quitButton = new Button("Quit");
    Button playAgainButton = new Button("Play Again");

    quitButton.setOnAction(e -> primaryStage.close()); // Close the primary stage
    playAgainButton.setOnAction(e -> {
        selectRandomAnimal(dataMap); // Assuming dataMap is accessible here
        primaryStage.close(); // Close the popup
    });

    popupLayout.getChildren().addAll(message, playAgainButton, quitButton);

    // Popup scene
    Scene popupScene = new Scene(popupLayout, 300, 200);
    Stage popupStage = new Stage();
    popupStage.initModality(Modality.APPLICATION_MODAL); // Block input events to other stages
    popupStage.setScene(popupScene);
    popupStage.showAndWait(); // Show and wait until it's closed
}
}