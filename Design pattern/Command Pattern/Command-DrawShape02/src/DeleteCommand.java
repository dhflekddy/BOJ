import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class DeleteCommand implements Command {

    private final Shape shape;
    private final Pane pane;
    public DeleteCommand(Shape shape, Pane centerpane){
        this.shape=shape;
        this.pane=centerpane;
    }
    @Override
    public void execute(){
        pane.getChildren().remove(pane);
    }

    @Override
    public void undo(){
        if(shape!=null){
            pane.getChildren().add(shape);
        }
    }
    @Override
    public void redo(){
        if(shape!=null){
            pane.getChildren().remove(shape);
        }
    }

}
