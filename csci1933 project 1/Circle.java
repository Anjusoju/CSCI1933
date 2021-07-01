//turnq070
import java.lang.Math;

import java.awt.Color;

public class Circle {
    double xPosition;
    double yPosition;
    double radius;
    Color color;
    
    public Circle(double initXPosition, double initYPosition, double initRadius){
        this.xPosition = initXPosition;
        this.yPosition = initYPosition;
        this.radius = initRadius;
    }
    public  double calculatePerimeter(){
        return 2 * Math.PI * radius;
    }
    public  double calculateArea(){
        return Math.PI * (radius * radius);
    }
    public void setColor(Color initColor){
         this.color = initColor;
     }
    public void setPos(double xPos,    double yPos){
        this.xPosition = xPos;
        this.yPosition = yPos;
    }
    public void setRadius(double newRadius){
        this.radius = newRadius;
    }
    public Color getColor(){
        return this.color;
    }
    public  double getXPos(){
        return this.xPosition;
    }
    public  double getYPos(){
        return this.yPosition;
    }
    public  double getRadius(){
        return this.radius;
    }
    // public static void main(String[] args){
    //     Circle kuklos = new Circle(0.0, 0.0, 5.0);
    //     System.out.println(kuklos.radius);
    //      double peri = kuklos.calculatePerimeter();
    //     System.out.println(peri);
    // }
}   