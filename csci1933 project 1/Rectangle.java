//turnq070
import java.awt.Color;

public class Rectangle {
    double xPosition;
    double yPosition;
    double width;
    double height;
    Color color;
    public Rectangle(double initXPosition, double initYPosition, double initWidth, double initHeight){
        this.xPosition = initXPosition;
        this.yPosition = initYPosition;
        this.width = initWidth;
        this.height = initHeight;
    }
    public double calculatePerimeter(){
        return this.width * 2 + this.height * 2;
    }
    public double calculateArea(){
        return this.width * this.height;
    }
    public void setColor(Color initColor){
        this.color = initColor;
    }
    public void setPos(double newXPos, double newYPos){
        this.xPosition = newXPos;
        this.yPosition = newYPos;
    }    
    public void setHeight(double newHeight){
        this.height = newHeight;
    }
    public void setWidth(double newWidth){
        this.width = newWidth;
    }
    public Color getColor(){
        return this.color;
    }
    public double getXPos(){
        return this.xPosition;
    }
    public double getYPos(){
        return this.yPosition;
    }    
    public double getHeight(){
        return this.height;
    }        
    public double getWidth(){
        return this.width;
    }

}
