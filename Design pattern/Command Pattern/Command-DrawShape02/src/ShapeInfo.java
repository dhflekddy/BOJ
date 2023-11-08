import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

class ShapeInfo{
    private int info;
    private Shape shape;

    private Paint paint;
    public ShapeInfo(int info, Shape shape, Paint p){
        this.info=info;
        this.shape=shape;
        this.paint=p;
    }
    public int getInfo(){
        return info;
    }
    public Shape getShape(){
        return shape;
    }
    public Paint getPaint(){
        return paint;
    }
    public void setPaint(Paint p){
        this.paint=p;
    }
}