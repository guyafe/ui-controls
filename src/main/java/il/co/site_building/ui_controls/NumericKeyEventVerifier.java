package il.co.site_building.ui_controls;

import javafx.beans.property.Property;
import javafx.scene.input.KeyEvent;

@FunctionalInterface
public interface NumericKeyEventVerifier {

  void verifyNumber(KeyEvent keyEvent, Property<Number> minValueProperty, Property<Number> maxValueProperty);
}
