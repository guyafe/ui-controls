package il.co.site_building.ui_controls;

import javafx.beans.property.Property;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public interface NumericFieldController {

  Label getName();

  TextField getValue();

  Slider getSlider();
  AnchorPane getAnchor();
  Property<Number> getMinProperty();

  Property<Number> getMaxProperty();

}
