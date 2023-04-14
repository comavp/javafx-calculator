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

    private String DIVISION_BY_ZERO = "Деление на ноль невозможно";

    public void addSymbol(Event event) {
        if (DIVISION_BY_ZERO.equals(expression.getText())) {
            expression.setText("");
        }

        Button button = (Button) event.getSource();
        expression.setText(expression.getText() + button.getText());
    }

    public void getResult() {
        if (DIVISION_BY_ZERO.equals(expression.getText())) {
            return;
        }

        expression.setText(calculator.getExpressionResult(expression.getText()));
    }
}
