package il.co.site_building.ui_controls;

import java.io.PrintStream;

import javafx.scene.control.TextArea;

public class TextAreaConsoleControl extends TextArea {

  private final UiStdOutputStream outputStream;

  public TextAreaConsoleControl() {
    outputStream = new UiStdOutputStream(this);
    PrintStream ps = new PrintStream(outputStream, true);
    System.setOut(ps);
    System.setErr(ps);
  }
}
