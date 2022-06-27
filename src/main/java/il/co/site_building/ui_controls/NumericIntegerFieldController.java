package il.co.site_building.ui_controls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class NumericIntegerFieldController implements Initializable, NumericFieldController {

  @FXML private Label name;
  @FXML private TextField value;
  @FXML private Slider slider;
  @FXML private AnchorPane anchor;

  @Override public void initialize(URL location, ResourceBundle resources) {
  }

  @Override
  public Label getName() {
    return name;
  }

  public void setName(Label name) {
    this.name = name;
  }

  @Override
  public TextField getValue() {
    return value;
  }

  public void setValue(TextField value) {
    this.value = value;
  }

  @Override public Property<Number> getMinProperty() {
    return slider.minProperty();
  }

  @Override public Property<Number> getMaxProperty() {
    return slider.maxProperty();
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
