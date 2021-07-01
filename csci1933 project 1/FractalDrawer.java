//turnq070
import java.util.Scanner;
import java.lang.Math;
// FractalDrawer class draws a fractal of a shape indicated by user input
import java.awt.Color;

public class FractalDrawer {
    private double totalArea=0;  // member variable for tracking the total area

    public FractalDrawer() {}  // contructor



    public double drawFractal(String type) {
    //TODO:
    // drawFractal creates a new Canvas object
    // and determines which shapes to draw a fractal by calling appropriate helper function
    // drawFractal returns the area of the fractal        
        Canvas drawing = new Canvas(800, 800);
        drawing.setBackground(Color.BLACK);
        if (type.equals("circle")) {
            drawCircleFractal(300, 400, 400, Color.CYAN, drawing, 1);
        }
        if (type.equals("triangle")) {
            drawTriangleFractal(300,  259.808, 250, 400, Color.CYAN, drawing, 1);
        }
        if (type.equals("rectangle")) {
            drawRectangleFractal(200, 200, 300, 300, Color.CYAN, drawing, 1);
        }
        return totalArea;
    }



    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level){
    //TODO:
    // drawTriangleFractal draws a triangle fractal using recursive techniques
    Triangle triangulus = new Triangle(x, y, width, height);
    totalArea += triangulus.calculateArea();
    triangulus.setColor(c);
    can.drawShape(triangulus);
    if (level < 7 && level % 2 == 0) {
        drawTriangleFractal(width/2, height/2, x + (width*0.75),y - height/2, Color.CYAN, can, level+1);
        drawTriangleFractal(width/2, height/2, x - width/2/2, y - height/2, Color.CYAN, can, level+1);
        drawTriangleFractal(width/2, height/2, x + width/2/2, y + height/2, Color.CYAN, can, level+1);
    } else if (level < 7 && level % 2 != 0) {
        drawTriangleFractal(width/2, height/2, x + (width*0.75),y - height / 2, Color.MAGENTA, can, level+1);
        drawTriangleFractal(width/2, height/2, x - width/2/2, y - height/2, Color.MAGENTA, can, level+1);
        drawTriangleFractal(width/2, height/2, x + width/2/2, y + height/2, Color.MAGENTA, can, level+1);
    }
    }


    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
    // TODO:
    // drawCircleFractal draws a circle fractal using recursive techniques   
    
    Circle kuklos = new Circle(x, y, radius);
    totalArea += kuklos.calculateArea();
    kuklos.setColor(c);
    can.drawShape(kuklos);
    if (level < 7 && level % 2 == 0) {
        drawCircleFractal(radius/1.9, x + radius/2 * Math.sqrt(2.0) * 0.5, y + radius/2 * Math.sqrt(2.0) * 0.5, Color.CYAN, can, level + 1);
        drawCircleFractal(radius/1.9, x, y - radius/2, Color.CYAN, can, level + 1);
        drawCircleFractal(radius/1.9, x - radius/2 * Math.sqrt(2.0) * 0.5, y + radius/2 * Math.sqrt(2.0) * 0.5, Color.CYAN, can, level + 1);
        //drawCircleFractal(radius/2, x - radius/2, y, Color.CYAN, can, level + 1);

    } else if (level < 7 && level % 2 != 0) {
        drawCircleFractal(radius/1.9, x + radius/2 * Math.sqrt(2.0) * 0.5, y + radius/2 * Math.sqrt(2.0) * 0.5, Color.MAGENTA, can, level + 1);   
        drawCircleFractal(radius/1.9, x, y - radius/2, Color.MAGENTA, can, level + 1);   
        drawCircleFractal(radius/1.9, x - radius/2 * Math.sqrt(2.0) * 0.5, y + radius/2 * Math.sqrt(2.0) * 0.5, Color.MAGENTA, can, level + 1);   
        //drawCircleFractal(radius/2, x - radius/2, y, Color.MAGENTA, can, level + 1);   

    }
    }



    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
    //TODO:
    // drawRectangleFractal draws a rectangle fractal using recursive techniques
    
    //I have a lot of code commented out here which make the fractal more symetrical, but I think I'm running into a shape limit or something.

    Rectangle rectangulus = new Rectangle(x, y, width, height);
    totalArea += rectangulus.calculateArea();
    rectangulus.setColor(c);
    can.drawShape(rectangulus);
    if (level < 7 && level % 2 == 0) {
        drawRectangleFractal(width/2, height/2, x + width, y + height, Color.CYAN, can, level + 1);
        //drawRectangleFractal(width/2, height/2, x + width, y - height/2, Color.CYAN, can, level + 1);
        drawRectangleFractal(width/2, height/2, x - width/2, y - height/2, Color.CYAN, can, level + 1);
        //drawRectangleFractal(width/2, height/2, x - width/2, y + height, Color.CYAN, can, level + 1);


    } else if (level < 7 && level % 2 != 0) {
        //drawRectangleFractal(width/2, height/2, x + width, y + height, Color.MAGENTA, can, level + 1);
        drawRectangleFractal(width/2, height/2, x + width, y - height/2, Color.MAGENTA, can, level + 1);
        //drawRectangleFractal(width/2, height/2, x - width/2, y - height/2, Color.MAGENTA, can, level + 1);
        drawRectangleFractal(width/2, height/2, x - width/2, y + height, Color.MAGENTA, can, level + 1);

    }
    }


    public static void main(String[] args){
    //TODO:
    // main should ask user for shape input, and then draw the corresponding fractal.
    // should print area of fractal
    System.out.println("Type of one the following shapes to select: circle - triangle - rectangle");
    Scanner scan = new Scanner(System.in);
    String inp = scan.nextLine();


    FractalDrawer fracDrawer = new FractalDrawer();
    System.out.println(fracDrawer.drawFractal(inp));
    }
}
