package il.co.site_building.ui_controls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NumericDoubleFieldController implements Initializable {

  @FXML private Label name;
  @FXML private TextField value;
  @FXML private Slider slider;

  @Override public void initialize(URL location, ResourceBundle resources) {

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
}
