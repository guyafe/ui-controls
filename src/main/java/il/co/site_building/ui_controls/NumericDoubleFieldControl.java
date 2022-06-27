package il.co.site_building.ui_controls;

import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
  private final StringProperty value = new SimpleStringProperty("0.0");
  private final DoubleProperty sliderMinValue = new SimpleDoubleProperty(0.0);
  private final DoubleProperty sliderMaxValue = new SimpleDoubleProperty(1.0);
  private final DoubleProperty majorTickUnit = new SimpleDoubleProperty(0.1);
  private final DoubleProperty minorTickUnit = new SimpleDoubleProperty(0.5);
  private final BooleanProperty snapToTicks = new SimpleBooleanProperty(true);

  public NumericDoubleFieldControl() {
    super();
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FXML_LOCATION));
      controller = new NumericDoubleFieldController();
      loader.setController(controller);
      Node n = loader.load();
      this.getChildren().add(n);
      TextFieldUtils.bindDoubleProperties(controller,
                                          name,
                                          value,
                                          sliderMinValue,
                                          sliderMaxValue,
                                          majorTickUnit,
                                          minorTickUnit,
                                          snapToTicks,
                                          prefWidthProperty());
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    } catch (Exception ignore) {
    }
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

  public double getMajorTickUnit() {
    return majorTickUnit.get();
  }

  public DoubleProperty majorTickUnitProperty() {
    return majorTickUnit;
  }

  public void setMajorTickUnit(double majorTickUnit) {
    this.majorTickUnit.set(majorTickUnit);
  }

  public double getMinorTickUnit() {
    return minorTickUnit.get();
  }

  public DoubleProperty minorTickUnitProperty() {
    return minorTickUnit;
  }

  public void setMinorTickUnit(double minorTickUnit) {
    this.minorTickUnit.set(minorTickUnit);
  }

  public boolean isSnapToTicks() {
    return snapToTicks.get();
  }

  public BooleanProperty snapToTicksProperty() {
    return snapToTicks;
  }

  public void setSnapToTicks(boolean snapToTicks) {
    this.snapToTicks.set(snapToTicks);
  }
}
