package address.view;

import javafx.fxml.FXML;
import address.MainApp;
import javax.swing.text.html.ImageView;

public class EcmojiController {
    @FXML
    private ImageView myImageView;
    private MainApp mainApp;

    public void handleTouchShow(){
        mainApp.showEcmojiCmt();
    }
}
