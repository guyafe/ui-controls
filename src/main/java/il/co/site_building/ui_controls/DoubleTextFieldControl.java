package il.co.site_building.ui_controls;

import static il.co.site_building.ui_controls.TextFieldUtils.BORDER_BLACK;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;

public class DoubleTextFieldControl extends TextField {


  private final DoubleProperty minValue = new SimpleDoubleProperty(Double.MIN_VALUE);
  private final DoubleProperty maxValue = new SimpleDoubleProperty(Double.MAX_VALUE);

  public DoubleTextFieldControl() {
    super();
    setStyle(BORDER_BLACK);
    TextFieldUtils.setVerifyNumericDoubleField(this, minValue, maxValue);
  }

  public double getMinValue() {
    return minValue.get();
  }

  public DoubleProperty minValueProperty() {
    return minValue;
  }

  public void setMinValue(double minValue) {
    this.minValue.set(minValue);
  }

  public double getMaxValue() {
    return maxValue.get();
  }

  public DoubleProperty maxValueProperty() {
    return maxValue;
  }

  public void setMaxValue(double maxValue) {
    this.maxValue.set(maxValue);
  }
}
