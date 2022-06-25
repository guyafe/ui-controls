package il.co.site_building.ui_controls;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class NumericDoubleFieldControl extends AnchorPane {

  private static final String FXML_LOCATION = "il/co/site_building/ui_controls/NumericDoubleField.fxml";
  private NumericDoubleFieldController controller;
  private final StringProperty name = new SimpleStringProperty("Name");
  private final StringProperty value = new SimpleStringProperty("0");
  private final DoubleProperty sliderMinValue = new SimpleDoubleProperty(0.0);
  private final DoubleProperty sliderMaxValue = new SimpleDoubleProperty(1.0);
  private final DoubleProperty anchorPrefWidth = new SimpleDoubleProperty();

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
    controller.getName().textProperty().bindBidirectional(name);
    controller.getValue().textProperty().bindBidirectional(value);
    controller.getSlider().minProperty().bindBidirectional(sliderMinValue);
    controller.getSlider().maxProperty().bindBidirectional(sliderMaxValue);
    controller.getAnchor().prefWidthProperty().bindBidirectional(this.prefWidthProperty());
  }

  public String getName() {
    return name.get();
  }

  public StringProperty nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public String getValue() {
    return value.get();
  }

  public StringProperty valueProperty() {
    return value;
  }

  public void setValue(String value) {
    this.value.set(value);
    try {
      controller.getSlider().setValue(Double.parseDouble(value));
    } catch (Exception ignore){}
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
