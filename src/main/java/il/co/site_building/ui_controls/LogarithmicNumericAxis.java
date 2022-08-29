package il.co.site_building.ui_controls;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.chart.ValueAxis;
import javafx.util.Duration;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class LogarithmicNumericAxis extends ValueAxis<Double> {

  private static final double ANIMATION_TIME_MILLIS = 2000;
  private final Timeline lowerRangeTimeline = new Timeline();
  private final Timeline upperRangeTimeline = new Timeline();

  private final DoubleProperty logUpperBound = new SimpleDoubleProperty();
  private final DoubleProperty logLowerBound = new SimpleDoubleProperty();
  private final double base;

  public LogarithmicNumericAxis() {
    super();
    this.base = 10;
    bindBounds(base);
  }

  private void bindBounds(double base) {
    logLowerBound.bind(new LogBinding(base, lowerBoundProperty()));
    logUpperBound.bind(new LogBinding(base, upperBoundProperty()));
  }

  /*@Override protected List<Double> calculateMinorTickMarks() {
    Number[] range = (Number[]) getRange();
    List<Double> minorTickMarks = new ArrayList<>();
    try {
      if (range != null) {
        double upperBound = range[1].doubleValue();
        double upperBoundLog = Math.log(upperBound) / Math.log(base);
        int numberOfTicks = getMinorTickCount();
        for (double tickIndex = 0; tickIndex <= upperBoundLog; tickIndex++) {
          for (double minorTickIndex = 0; minorTickIndex < base; minorTickIndex += (1. / numberOfTicks)) {
            double value = minorTickIndex * Math.pow(base, tickIndex);
            minorTickMarks.add(value);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return minorTickMarks;
  }*/

  @Override protected List<Double> calculateMinorTickMarks() {
    Number[] range = (Number[]) getRange();
    List<Double> minorTickMarks = new ArrayList<>();
    try {
      if (range != null) {
        double upperBound = range[1].doubleValue();
        double upperBoundLog = Math.log(upperBound) / Math.log(base);
        int numberOfTicks = getMinorTickCount();
        for (double groupIndex = 0; groupIndex <= upperBoundLog; groupIndex++) {
          for (double minorTickIndex = 1; minorTickIndex <= numberOfTicks; minorTickIndex++) {
            double increment = minorTickIndex * base / numberOfTicks;
            double value = increment * Math.pow(base, groupIndex);
            minorTickMarks.add(value);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return minorTickMarks;
  }

  @Override protected void setRange(Object range, boolean animate) {
    if (range != null) {
      double lowerBound = ((Number[]) range)[0].doubleValue();
      double upperBound = ((Number[]) range)[1].doubleValue();
      if (animate) {
        try {
          lowerRangeTimeline.getKeyFrames().clear();
          upperRangeTimeline.getKeyFrames().clear();

          lowerRangeTimeline.getKeyFrames()
                            .addAll(new KeyFrame(Duration.ZERO,
                                                 new KeyValue(lowerBoundProperty(), lowerBoundProperty().get())),
                                    new KeyFrame(new Duration(ANIMATION_TIME_MILLIS),
                                                 new KeyValue(lowerBoundProperty(), lowerBound)));
          upperRangeTimeline.getKeyFrames()
                            .addAll(new KeyFrame(Duration.ZERO,
                                                 new KeyValue(upperBoundProperty(), upperBoundProperty().get())),
                                    new KeyFrame(new Duration(ANIMATION_TIME_MILLIS),
                                                 new KeyValue(upperBoundProperty(), upperBound)));
          lowerRangeTimeline.play();
          upperRangeTimeline.play();
        } catch (Exception e) {
          lowerBoundProperty().set(lowerBound);
          upperBoundProperty().set(upperBound);
        }
      }
      lowerBoundProperty().set(lowerBound);
      upperBoundProperty().set(upperBound);
    }
  }

  @Override protected Object getRange() {
    return new Number[]{getLowerBound(), getUpperBound()};
  }

  @Override protected List<Double> calculateTickValues(double length, Object range) {
    List<Double> tickValues = new ArrayList<>();
    if (range != null) {
      double upperBound = ((Number[]) range)[1].doubleValue();
      double logUpperBoundValue = Math.log(upperBound) / Math.log(base);
      int numberOfTicks = getMinorTickCount();
      for (double groupIndex = 0; groupIndex <= logUpperBoundValue; groupIndex++) {
        for (double minorTickIndex = 1; minorTickIndex <= numberOfTicks; minorTickIndex++) {
          double value = minorTickIndex * Math.pow(base, groupIndex) / numberOfTicks;
          tickValues.add(value);
        }
      }
    }
    return tickValues;
  }

  @Override protected String getTickMarkLabel(Double value) {
    NumberFormat labelFormat = NumberFormat.getInstance();
    labelFormat.setMaximumIntegerDigits(6);
    labelFormat.setMinimumIntegerDigits(1);
    return labelFormat.format(value);
  }

  @Override
  public Double getValueForDisplay(double displayPosition) {
    double delta = logUpperBound.get() - logLowerBound.get();
    if (getSide().isVertical()) {
      return Math.pow(base, (((displayPosition - getHeight()) / -getHeight()) * delta) + logLowerBound.get());
    } else {
      return Math.pow(base, (((displayPosition / getWidth()) * delta) + logLowerBound.get()));
    }
  }

  @Override
  public double getDisplayPosition(Double value) {
    try {
      if (value < 1E-10) {
        value = 1E-10;
      }
      double delta = logUpperBound.get() - logLowerBound.get();
      double deltaV = Math.log(value) / Math.log(base) - logLowerBound.get();
      if (getSide().isVertical()) {
        return (1. - ((deltaV) / delta)) * getHeight();
      } else {
        return ((deltaV) / delta) * getWidth();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  @Override
  protected Object autoRange(double minValue, double maxValue, double length, double labelSize) {
    return new Double[]{minValue, maxValue};
  }

  private static class LogBinding extends DoubleBinding {

    private final double base;
    private final DoubleProperty superBoundProperty;

    private LogBinding(double base, DoubleProperty superBoundProperty) {
      super.bind(superBoundProperty);
      this.base = base;
      this.superBoundProperty = superBoundProperty;
    }

    @Override protected double computeValue() {
      double value = superBoundProperty.getValue();
      return Double.max(Math.log(value) / Math.log(base), 0);
    }
  }
}
