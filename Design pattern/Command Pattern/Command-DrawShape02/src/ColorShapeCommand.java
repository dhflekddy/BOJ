import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class ColorShapeCommand implements Command{
    private final Shape shape;
    private final Pane pane;

    private Paint lastColor;
    private Paint currentColor;

    public ColorShapeCommand(Shape shape,Paint lastColor, Pane centerpane){
        this.shape=shape;
        this.lastColor =lastColor;
        this.currentColor=shape.getFill();
        this.pane=centerpane;
    }

    /////////////////////////////////////////////////////////
    private ShapeInfo shapeForUndo = null;



    @Override
    public void execute(){
        shapeForUndo = new ShapeInfo(0, shape, shape.getFill());
        shape.setFill(shape.getFill());
    }
    //undo하면 화면에서 해당 이미지는 삭제 되지만 화면에서 삭제 되었다고 하여
    // 그 이미지를 소유하고 있는 명령객체가 삭제되는 것은 아니다

    @Override
    public void undo(){
        if(shapeForUndo!=null){
            Paint temp=lastColor;
            lastColor=currentColor;
            currentColor=temp;
            shape.setFill(currentColor);
        }
    }
    @Override
    public void redo(){
        if(shapeForUndo!=null){//cur이 색이 없음 last는 색있음
            Paint temp=lastColor;
            lastColor=currentColor;
            currentColor=temp;
            shape.setFill(currentColor);
        }
    }

    //		private void undo() {
//			if(!undoStack.isEmpty()){
//				ShapeInfo shape= undoStack.pop();//색깔 O
//				redoStack.push(shape);//색깔 O//여기까지는 client에서처리

    //Command객체 생성시 각각의 명령이 구분되므로 아래 처럼 분기문 사용할 필요없음
    //즉, 각각의 명령 클래스를 더 많이 생성해주어 코드의 복잡도는 낮추게 되었다.
//				if(shape.getInfo()==0)
//					centerPane.getChildren().remove(shape.getShape());
//				else{
//					ShapeInfo shape2= undoStack.pop();//색깍 X
//					redoStack.push(shape2);//색깍 X
//					Paint p2=shape2.getPaint();
//
//					shape.getShape().setFill(p2);
//				}
//			}
//		}
}
