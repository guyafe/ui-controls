package il.co.site_building.ui_controls;

import java.io.OutputStream;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import javax.annotation.Nonnull;

public class UiStdOutputStream  extends OutputStream {

  private final TextArea textArea;

  public UiStdOutputStream(TextArea textArea) {
    this.textArea = textArea;
  }

  @Override
  public void write(int b) {
    Platform.runLater(() -> textArea.appendText(String.valueOf((char) b)));
  }

  @Override
  public void write(@Nonnull byte[] b) {
    String s = new String(b);
    Platform.runLater(() -> textArea.appendText(s));
  }

  @Override
  public void write(@Nonnull byte[] b, int off, int len) {
    String s = new String(b, off, len);
    Platform.runLater(() -> textArea.appendText(s));
  }

}
