package il.co.site_building.ui_controls;

import static il.co.site_building.ui_controls.TextFieldUtils.BORDER_BLACK;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;

public class IntegerTextFieldControl extends TextField {


  private final IntegerProperty minValue = new SimpleIntegerProperty(Integer.MIN_VALUE);
  private final IntegerProperty maxValue = new SimpleIntegerProperty(Integer.MAX_VALUE);

  public IntegerTextFieldControl() {
    super();
    setStyle(BORDER_BLACK);
    TextFieldUtils.setVerifyNumericIntegerField(this, minValue, maxValue);
  }

  public int getMinValue() {
    return minValue.get();
  }

  public IntegerProperty minValueProperty() {
    return minValue;
  }

  public void setMinValue(int minValue) {
    this.minValue.set(minValue);
  }

  public int getMaxValue() {
    return maxValue.get();
  }

  public IntegerProperty maxValueProperty() {
    return maxValue;
  }

  public void setMaxValue(int maxValue) {
    this.maxValue.set(maxValue);
  }
}
