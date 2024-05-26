public class Area {
    private int xValue;
    private int yValue;
    private int width;
    private int height;


public Area(int xValue, int yValue, int height, int width) {
    createAreaCoordinates(xValue, yValue);
    createAreaDimensions(width, height);
}


public int getXValue(){
    return xValue;
}

public int getYValue(){
    return yValue;
}

public int getWidth(){
    return width;
}

public int getHeight(){
    return height;
}

public void createAreaCoordinates(int xValue, int yValue) {
    this.xValue = xValue;
    this.yValue = yValue;
}

public void createAreaDimensions(int width, int height){
    this.width = width;
    this.height = height;
}
}
