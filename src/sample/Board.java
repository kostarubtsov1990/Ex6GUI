package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Created by kostarubtsov1990 on 07/01/18.
 */
public class Board extends GridPane {

    private int [][] board;
    private static final int xSymbol = 1;
    private static final int oSymbol = 2;
    private static final int blank = 0;

    private Cell clickedNode;

    public Board (int [][] board) {
        this.board = board;
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

    public void SetBoard (int [][] board) {
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
