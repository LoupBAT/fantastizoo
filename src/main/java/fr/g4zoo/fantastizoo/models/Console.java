package fr.g4zoo.fantastizoo.models;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class Console extends OutputStream {
    private TextArea output;

    public Console(TextArea textArea) {
        this.output = textArea;
    }

    @Override
    public void write(int b) {
        Platform.runLater(() -> output.appendText(String.valueOf((char) b)));
    }

    @Override
    public void write(byte[] b, int off, int len) throws UnsupportedEncodingException {
        final String text = new String(b, off, len, "UTF-8");
        Platform.runLater(() -> output.appendText(text));
    }
}
