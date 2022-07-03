package util;

import javafx.scene.control.Button;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;


public class Validationutil {
        public static Object validate(LinkedHashMap<TextField, Pattern> map, Button btn) {
            btn.setDisable(true);
            for (TextField textFieldKey : map.keySet()) {
                Pattern patternValue = map.get(textFieldKey);
                if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                    if (!textFieldKey.getText().isEmpty()) {
                        textFieldKey.setStyle("-fx-border-color: red");

                    }
                    return textFieldKey;
                }
                textFieldKey.setStyle("-fx-border-color: blue");

            }
            btn.setDisable(false);
            return true;
        }
    }

