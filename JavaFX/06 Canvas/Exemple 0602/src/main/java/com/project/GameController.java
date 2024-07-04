package com.project;

import java.net.URL;
import java.util.Calendar;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.StrokeLineCap;

public class GameController {

    public double hores = 0;
    public double minuts = 0;
    public double segons = 0;
    public double millis = 0;

    public GameObj selectedObj = new GameObjLinies();

    Canvas canvas;
    GraphicsContext gc;
    GameTimer animationTimer;
    boolean showFps = false; 

    public GameController(Canvas canvas) {

        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

        animationTimer = new GameTimer(this::run, this::draw, 0);
        start();
    }

    // Start animation timer
    public void start() {
        animationTimer.start();
    }

    // Stop animation timer
    public void stop() {
        animationTimer.stop();
    }

    // Run game (and animations)
    private void run(double fps) {

        if (animationTimer.fps < 1) { return; }

        // Compute global attributes
        Calendar cal = Calendar.getInstance();
        this.hores = cal.get(Calendar.HOUR_OF_DAY);
        this.minuts = cal.get(Calendar.MINUTE);
        this.segons = cal.get(Calendar.SECOND);
        this.millis = cal.get(Calendar.MILLISECOND);
    }

    public void draw() {

        // Clean drawing area
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw grid
        drawGrid();

        // Draw object
        selectedObj.draw(this);

        // Draw FPS if needed
        if (showFps) { animationTimer.draw(gc); }
    }

    public void actionSetSelection(String value) {
        switch (value) {
            case "Linies":              selectedObj = new GameObjLinies(); break;
            case "Poligons":            selectedObj = new GameObjPoligons(); break;
            case "Poligons emplenats":  selectedObj = new GameObjPoligonsEmplenats(); break;
            case "Quadrats i cercles":  selectedObj = new GameObjQuadratsCercles(); break;
            case "Imatges":             selectedObj = new GameObjImatges(); break;
            case "Gradients lineals":   selectedObj = new GameObjGradientsLineals(); break;
            case "Gradients radials":   selectedObj = new GameObjGradientsRadials(); break;
            case "Transformacions":     selectedObj = new GameObjTransformacions(); break;
            case "Texts":               selectedObj = new GameObjTexts(); break;
            case "Text multilinia":     selectedObj = new GameObjTextMultilinia(); break;
        }
    }

    // Draw grid to know objects position
    public void drawGrid() {

        double cnvHeight = canvas.heightProperty().doubleValue();
        double cnvWidth = canvas.widthProperty().doubleValue();

        gc.setFill(Color.BLACK);
        gc.save();
        gc.setFont(new Font("Arial", 12));
        gc.setLineCap(StrokeLineCap.BUTT);
        for (int x = 0; x < cnvWidth; x = x + 10) {
            if (x % 50 == 0) {
                gc.setStroke(Color.LIGHTGRAY);
                gc.setLineWidth(2);
                gc.strokeLine(x, 0, x, cnvHeight);
                gc.fillText("" + x, x, 10);
            } else {
                gc.setStroke(Color.LIGHTGRAY);
                gc.setLineWidth(1);
                gc.strokeLine(x, 0, x, cnvHeight);
            }
        }

        for (int y = 0; y < cnvHeight; y = y + 10) {
            if (y % 50 == 0) {
                gc.setStroke(Color.LIGHTGRAY);
                gc.setLineWidth(2);
                gc.strokeLine(0, y, cnvWidth, y);
                gc.fillText("" + y, 0, y + 10);
            } else {
                gc.setStroke(Color.LIGHTGRAY);
                gc.setLineWidth(1);
                gc.strokeLine(0, y, cnvWidth, y);
            }
        }
        gc.restore();
    }

    // Draws text
    public void drawText(String codi, double x, double y) {
        gc.save();
        gc.setFont(new Font("Arial", 14));
        gc.setFill(Color.BLACK);
        gc.fillText(codi, x, y);
        gc.restore();
    }
}