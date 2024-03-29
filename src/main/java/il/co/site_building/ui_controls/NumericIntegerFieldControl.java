package il.co.site_building.ui_controls;

import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class NumericIntegerFieldControl extends AnchorPane {

  private static final String FXML_LOCATION = "il/co/site_building/ui_controls/NumericIntegerField.fxml";
  private NumericIntegerFieldController controller;
  private final StringProperty name = new SimpleStringProperty("Name");
  private final StringProperty value = new SimpleStringProperty("0");
  private final IntegerProperty sliderMinValue = new SimpleIntegerProperty(0);
  private final IntegerProperty sliderMaxValue = new SimpleIntegerProperty(100);
  private final DoubleProperty majorTickUnit = new SimpleDoubleProperty(1);
  private final DoubleProperty minorTickUnit = new SimpleDoubleProperty(10);
  private final BooleanProperty snapToTicks = new SimpleBooleanProperty(true);


  public NumericIntegerFieldControl() {
    super();
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FXML_LOCATION));
      controller = new NumericIntegerFieldController();
      loader.setController(controller);
      Node n = loader.load();
      this.getChildren().add(n);
      TextFieldUtils.bindIntegerProperties(controller,
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
      this.controller.getSlider().setValue(Integer.parseInt(value));
    } catch (Exception ignore) {
    }
  }

  public int getSliderMinValue() {
    return sliderMinValue.get();
  }

  public IntegerProperty sliderMinValueProperty() {
    return sliderMinValue;
  }

  public void setSliderMinValue(int sliderMinValue) {
    this.sliderMinValue.set(sliderMinValue);
  }

  public int getSliderMaxValue() {
    return sliderMaxValue.get();
  }

  public IntegerProperty sliderMaxValueProperty() {
    return sliderMaxValue;
  }

  public void setSliderMaxValue(int sliderMaxValue) {
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
