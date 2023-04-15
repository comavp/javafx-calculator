package ru.comavp.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ru.comavp.calculator.Calculator;
import ru.comavp.calculator.PostfixCalculator;

public class MainController {

    @FXML
    private Label expression;

    private Calculator calculator = new PostfixCalculator();

    private boolean isResultComputed = false;

    public void addDigit(Event event) {
        if (isResultComputed) {
            expression.setText("");
            isResultComputed = false;
        }

        addElement(event);
    }

    public void addArithmeticOperation(Event event) {
        if (isResultComputed) {
            isResultComputed = false;
        }

        addElement(event);
    }

    public void addSymbol(Event event) {
        Button button = (Button) event.getSource();

        if (isResultComputed) {
            if (".".equals(button.getText())) {
                expression.setText("0");
            } else {
                expression.setText("");
            }
            isResultComputed = false;
        }

        addElement(event);
    }

    private void addElement(Event event) {
        expression.setText(expression.getText() + ((Button) event.getSource()).getText());
    }

    public void getResult() {
        if (isResultComputed) {
            return;
        }

        expression.setText(calculator.getExpressionResult(expression.getText()));
        isResultComputed = true;
    }
}
