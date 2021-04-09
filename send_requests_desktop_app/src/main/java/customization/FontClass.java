package customization;

import java.awt.*;

public class FontClass {

    private final Font customTextFieldFont;
    private final Font customLabelFont;
    private final Font customButtonFont;
    private final Font textAreaTitleFont;
    private final Font textAreaContentFont;
    private final Font customSmallButtonFont;

    public FontClass() {

        customLabelFont = new Font("Calibri Light", Font.PLAIN, 24);
        customTextFieldFont = new Font("Calibri Light", Font.PLAIN, 18);
        customButtonFont = new Font("Calibri Light", Font.BOLD, 30);
        textAreaTitleFont = new Font("Calibri Light", Font.BOLD, 30);
        textAreaContentFont = new Font("Calibri Light", Font.PLAIN, 18);
        customSmallButtonFont = new Font("Calibri Light", Font.BOLD, 14);

    }

    public Font getCustomLabelFont() {
        return customLabelFont;
    }

    public Font getCustomTextFieldFont() {
        return customTextFieldFont;
    }

    public Font getCustomButtonFont() {
        return customButtonFont;
    }

    public Font getTextAreaTitleFont() {
        return textAreaTitleFont;
    }

    public Font getTextAreaContentFont() {
        return textAreaContentFont;
    }

    public Font getCustomSmallButtonFont() {
        return customSmallButtonFont;
    }
}
