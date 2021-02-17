import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class Controller extends Application {
    @FXML
    private ImageView image;

    @FXML
    private RadioButton playfairCheck;

    @FXML
    private TextField keyArea;

    @FXML
    private Button encryptButt;

    @FXML
    private TextArea cipherText;

    @FXML
    private ToggleGroup encryptMethod;

    @FXML
    private RadioButton vigenerCheck;

    @FXML
    private TextArea plainText;

    @FXML
    private RadioButton railwayCheck;

    @FXML
    private Menu fileButt;

    @FXML
    private Button decryptButt;


    @FXML
    void decrypt() {
        Encryptor encryptor = switch (getEncryptionMethod()) {
            case RailwayFence -> new RailwayEncryptor();
            case Vigener -> new VigenerEncryptor("rus");
            case Playfair -> new PlayfairEncryptor();
        };
        plainText.setText(encryptor.decrypt(cipherText.getText(), keyArea.getText()));
    }

    @FXML
    void encrypt() {
        Encryptor encryptor = switch (getEncryptionMethod()) {
            case RailwayFence -> new RailwayEncryptor();
            case Vigener -> new VigenerEncryptor("rus");
            case Playfair -> new PlayfairEncryptor();
        };
        cipherText.setText(encryptor.encrypt(plainText.getText(), keyArea.getText()));
    }

    @FXML
    void chooseFile() {
        try {
            getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("application.fxml"));
        primaryStage.setTitle("Encryptor");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void getFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/home/vladislav"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            plainText.setText(reader.readLine());
        }
    }

    private EncryptionMethod getEncryptionMethod() {
        EncryptionMethod selected = null;
        if (railwayCheck.isSelected()) selected = EncryptionMethod.RailwayFence;
        else if (vigenerCheck.isSelected()) selected = EncryptionMethod.Vigener;
        else if (playfairCheck.isSelected()) selected = EncryptionMethod.Playfair;
        return selected;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
