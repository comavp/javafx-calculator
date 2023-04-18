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

    public void removeSymbols(Event event) {
        Button button = (Button) event.getSource();

        if ("<-".equals(button.getText())) {
            removeElement();
        } else if ("C".equals(button.getText())) {
            removeAllElements();
        }
    }

    public void getResult() {
        if (isResultComputed) {
            return;
        }

        expression.setText(calculator.getExpressionResult(expression.getText()));
        isResultComputed = true;
    }

    private void addElement(Event event) {
        expression.setText(expression.getText() + ((Button) event.getSource()).getText());
    }

    private void removeElement() {
        if (expression.getText().length() < 1) {
            return;
        }

        String currentText = expression.getText();
        expression.setText(currentText.substring(0, currentText.length() - 1));
    }

    private void removeAllElements() {
        expression.setText("");
    }
}
