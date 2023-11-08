public class NoCommand implements Command {
    public static final NoCommand unique = new NoCommand();
    private NoCommand() {}
    @Override
    public void execute() {}
    @Override
    public void undo() {}

    @Override
    public void redo(){}
}

