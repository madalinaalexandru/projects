package customization;

import java.awt.*;

public class ColorClass {

    private final Color backgroundColor;
    private final Color buttonBackgroundColor;
    private final Color labelFontColor;
    private final Color textFieldFontColor;
    private final Color buttonTextFontColor;
    private final Color customBorderColor;

    public ColorClass() {

        backgroundColor = Color.white;
        buttonBackgroundColor = new Color(83, 140, 198);

        labelFontColor = new Color(64, 64, 64);
        textFieldFontColor = new Color(89, 89, 89);
        buttonTextFontColor = Color.white;

        customBorderColor = new Color(217, 217, 217);

    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getLabelFontColor() {
        return labelFontColor;
    }

    public Color getTextFieldFontColor() {
        return textFieldFontColor;
    }

    public Color getButtonTextFontColor() {
        return buttonTextFontColor;
    }

    public Color getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    public Color getCustomBorderColor() {
        return customBorderColor;
    }
}
