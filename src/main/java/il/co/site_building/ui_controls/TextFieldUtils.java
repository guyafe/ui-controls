package il.co.site_building.ui_controls;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TextFieldUtils {

  private TextFieldUtils() {
  }

  public static final String BORDER_READ =
      "-fx-text-box-border: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; fx-border-width: 3px; -fx-border-color: red";
  public static final String BORDER_BLACK =
      "-fx-text-box-border: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; fx-border-width: 3px; -fx-border-color: black";

  public static void setVerifyNumericField(TextField textField,
                                           Property<Number> minValueProperty,
                                           Property<Number> maxValueProperty,
                                           NumericKeyEventVerifier verifier) {
    textField.setOnKeyTyped((KeyEvent keyEvent) -> {
      try {
        verifier.verifyNumber(keyEvent, minValueProperty, maxValueProperty);
        textField.setStyle(BORDER_BLACK);
      } catch (IllegalArgumentException ignore) {
        handleNotNumber(textField);
      } finally {
        String newValueStr = ((TextField) keyEvent.getTarget()).getText();
        textField.positionCaret(newValueStr.length());
      }
    });
  }

  public static void setVerifyNumericIntegerField(TextField textField,
                                                   Property<Number> minValue,
                                                   Property<Number> maxValue) {
    setVerifyNumericField(textField, minValue, maxValue, TextFieldUtils::verifyIntegerNumber);
  }

  public static void setVerifyNumericDoubleField(TextField textField,
                                                   Property<Number> minValue,
                                                   Property<Number> maxValue) {
    setVerifyNumericField(textField, minValue, maxValue, TextFieldUtils::verifyDoubleNumber);
  }

  public static void bindDoubleProperties(NumericFieldController controller,
                                          StringProperty name,
                                          StringProperty value,
                                          DoubleProperty sliderMinValue,
                                          DoubleProperty sliderMaxValue,
                                          DoubleProperty prefWidthProperty) {
    bindProperties(controller,
                   name,
                   value,
                   sliderMinValue,
                   sliderMaxValue,
                   prefWidthProperty,
                   TextFieldUtils::verifyDoubleNumber);
    setSliderNumericDoubleListener(controller.getSlider(), controller.getValue());
  }


  public static void bindIntegerProperties(NumericFieldController controller,
                                           StringProperty name,
                                           StringProperty value,
                                           IntegerProperty sliderMinValue,
                                           IntegerProperty sliderMaxValue,
                                           DoubleProperty prefWidthProperty) {
    bindProperties(controller,
                   name,
                   value,
                   sliderMinValue,
                   sliderMaxValue,
                   prefWidthProperty,
                   TextFieldUtils::verifyIntegerNumber);
    setSliderNumericIntegerListener(controller.getSlider(), controller.getValue());
  }

  public static void bindProperties(NumericFieldController controller,
                                    StringProperty name,
                                    StringProperty value,
                                    Property<Number> sliderMinValue,
                                    Property<Number> sliderMaxValue,
                                    DoubleProperty prefWidthProperty,
                                    NumericKeyEventVerifier verifier) {
    controller.getName().textProperty().bindBidirectional(name);
    controller.getValue().textProperty().bindBidirectional(value);
    controller.getSlider().minProperty().bindBidirectional(sliderMinValue);
    controller.getSlider().maxProperty().bindBidirectional(sliderMaxValue);
    controller.getAnchor().prefWidthProperty().bindBidirectional(prefWidthProperty);
    controller.getValue().setStyle(BORDER_BLACK);
    setVerifyNumericField(controller.getValue(), sliderMinValue, sliderMaxValue, verifier);
  }

  private static void setSliderNumericDoubleListener(Slider slider, TextField value) {
    slider.valueProperty()
          .addListener(((observable, oldValue, newValue) -> value.textProperty()
                                                                 .setValue(Double.toString(newValue.doubleValue()))));
  }

  private static void setSliderNumericIntegerListener(Slider slider, TextField value) {
    slider.valueProperty()
          .addListener(((observable, oldValue, newValue) -> value.textProperty()
                                                                 .setValue(Integer.toString(newValue.intValue()))));
  }


  private static void handleNotNumber(TextField textField) {
    textField.setStyle(BORDER_READ);
  }

  private static void verifyDoubleNumber(KeyEvent keyEvent, Property<Number> minValue, Property<Number> maxValue) {
    String newValueStr = ((TextField) keyEvent.getTarget()).getText();
    double newValue = Double.parseDouble(newValueStr);
    if (newValue < minValue.getValue().doubleValue() || maxValue.getValue().doubleValue() < newValue) {
      throw new IllegalArgumentException();
    }
  }

  private static void verifyIntegerNumber(KeyEvent keyEvent, Property<Number> minValue, Property<Number> maxValue) {
    String newValueStr = ((TextField) keyEvent.getTarget()).getText();
    int newValue = Integer.parseInt(newValueStr);
    if (newValue < minValue.getValue().intValue() || maxValue.getValue().intValue() < newValue) {
      throw new IllegalArgumentException();
    }
  }
}

