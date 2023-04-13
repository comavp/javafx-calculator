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

    public void addSymbol(Event event) {
        Button button = (Button) event.getSource();
        expression.setText(expression.getText() + button.getText());
    }

    public void getResult() {
        expression.setText(calculator.getExpressionResult(expression.getText()));
    }
}
