package il.co.site_building.ui_controls;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NumericDoubleFieldControl extends AnchorPane {

  private static final String FXML_LOCATION = "il/co/site_building/ui_controls/NumericDoubleField.fxml";
  private NumericDoubleFieldController controller;
  private final ObjectProperty<String> name = new SimpleObjectProperty<>("Name");
  private final ObjectProperty<String> value = new SimpleObjectProperty<>("0");
  private final DoubleProperty sliderMinValue = new SimpleDoubleProperty(0.0);
  private final DoubleProperty sliderMaxValue = new SimpleDoubleProperty(1.0);

  public NumericDoubleFieldControl() {
    super();
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FXML_LOCATION));
      controller = new NumericDoubleFieldController();
      loader.setController(controller);
      Node n = loader.load();
      this.getChildren().add(n);
      bindProperties();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void bindProperties() {
    controller.getName().textProperty().bind(name);
    controller.getValue().textProperty().bind(value);
    controller.getSlider().minProperty().bind(sliderMinValue);
    controller.getSlider().maxProperty().bind(sliderMaxValue);
  }

  public String getName() {
    return name.get();
  }

  public ObjectProperty<String> nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public String getValue() {
    return value.get();
  }

  public ObjectProperty<String> valueProperty() {
    return value;
  }

  public void setValue(String value) {
    this.value.set(value);
  }

  public double getSliderMinValue() {
    return sliderMinValue.get();
  }

  public DoubleProperty sliderMinValueProperty() {
    return sliderMinValue;
  }

  public void setSliderMinValue(double sliderMinValue) {
    this.sliderMinValue.set(sliderMinValue);
  }

  public double getSliderMaxValue() {
    return sliderMaxValue.get();
  }

  public DoubleProperty sliderMaxValueProperty() {
    return sliderMaxValue;
  }

  public void setSliderMaxValue(double sliderMaxValue) {
    this.sliderMaxValue.set(sliderMaxValue);
  }
}
