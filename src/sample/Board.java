package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static sample.Board.symbol.blank;
import static sample.Board.symbol.oSymbol;
import static sample.Board.symbol.xSymbol;

/**
 * Created by kostarubtsov1990 on 07/01/18.
 */
public class Board extends GridPane {

    enum symbol {xSymbol, oSymbol, blank}

    private symbol [][] board;
    private Cell clickedNode;
    Color firstPlayerColor, secondPlayerColor;

    public Board (int size, Color firstPlayerColor, Color secondPlayerColor) {
        this.board = new symbol[size][size];
        int centerX, centerY;

        if (size % 2 == 0) {
            centerX = size / 2 - 1;
            centerY = size / 2 - 1;
        }
        else {
            centerX = size / 2;
            centerY = size / 2;
        }


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = blank;
            }
        }

        this.board[centerX][centerY] = oSymbol;
        this.board[centerX + 1][centerY] = xSymbol;
        this.board[centerX][centerY + 1] = xSymbol;
        this.board[centerX + 1][centerY + 1] = oSymbol;

        this.firstPlayerColor = firstPlayerColor;
        this.secondPlayerColor = secondPlayerColor;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void SetBoard (symbol [][] board) {
        this.board = board;
    }

    public void draw() {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();
        int cellHeight = height / board.length;
        int cellWidth = width / board[0].length;

       /* for (int i = 0 ; i < board[0].length ; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            this.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0 ; i < board.length ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            this.getRowConstraints().add(rowConstraints);
        }*/
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == xSymbol) {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight,
                            Color.BLACK);
                    final int row = i, col = j;
                    rectangle.setOnMouseClicked(e-> {
                        clickedNode = new Cell(col, row);
                    });
                    this.add(rectangle, j, i);
                    GridPane.setColumnIndex(rectangle, j);
                    GridPane.setRowIndex(rectangle, i);
                }
                else if (board[i][j] == oSymbol) {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight,
                            Color.WHITE);
                    final int row = i, col = j;
                    rectangle.setOnMouseClicked(e-> {
                        clickedNode = new Cell(col, row);
                    });
                    this.add(rectangle, j, i);
                    GridPane.setColumnIndex(rectangle, j);
                    GridPane.setRowIndex(rectangle, i);
                }
                else {
                    Rectangle rectangle = new Rectangle(cellWidth, cellHeight,
                            Color.ORANGE);
                    final int row = i, col = j;
                    rectangle.setOnMouseClicked(e-> {
                        clickedNode = new Cell(col, row);
                    });
                    this.add(rectangle, j, i);
                    GridPane.setColumnIndex(rectangle, j);
                    GridPane.setRowIndex(rectangle, i);
                }
            }
        }
    }

    public Cell getClickedChildNode () {
        return clickedNode;
    }
}
