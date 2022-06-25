package il.co.site_building.ui_controls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class NumericIntegerFieldController implements Initializable {

  private static final String BORDER_READ =
      "-fx-text-box-border: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; fx-border-width: 3px; -fx-border-color: red";
  private static final String BORDER_BLACK =
      "-fx-text-box-border: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; fx-border-width: 3px; -fx-border-color: black";
  @FXML private Label name;
  @FXML private TextField value;
  @FXML private Slider slider;
  @FXML private AnchorPane anchor;

  @Override public void initialize(URL location, ResourceBundle resources) {
    value.setStyle(BORDER_BLACK);
    setVerifyNumericFields();
    setSliderNumericListener();
  }

  private void setSliderNumericListener() {
    slider.valueProperty()
          .addListener(((observable, oldValue, newValue) -> value.textProperty()
                                                                 .setValue(Integer.toString(newValue.intValue()))));
  }

  private void setVerifyNumericFields() {
    value.setOnKeyTyped((KeyEvent keyEvent) -> {
      try {
        verifyNumber(keyEvent);
        value.setStyle(BORDER_BLACK);
      } catch (IllegalArgumentException ignore) {
        handleNotNumber();
      } finally {
        String newValueStr = ((TextField) keyEvent.getTarget()).getText();
        value.positionCaret(newValueStr.length());
      }
    });
  }

  private void handleNotNumber() {
    value.setStyle(BORDER_READ);
  }

  private void verifyNumber(KeyEvent keyEvent) {
    String newValueStr = ((TextField) keyEvent.getTarget()).getText();
    int newValue = Integer.parseInt(newValueStr);
    if (newValue < slider.minProperty().getValue() || slider.maxProperty().getValue() < newValue) {
      throw new IllegalArgumentException();
    }
    slider.setValue(newValue);
  }

  public Label getName() {
    return name;
  }

  public void setName(Label name) {
    this.name = name;
  }

  public TextField getValue() {
    return value;
  }

  public void setValue(TextField value) {
    this.value = value;
  }

  public Slider getSlider() {
    return slider;
  }

  public void setSlider(Slider slider) {
    this.slider = slider;
  }

  public AnchorPane getAnchor() {
    return anchor;
  }

  public void setAnchor(AnchorPane anchor) {
    this.anchor = anchor;
  }
}
