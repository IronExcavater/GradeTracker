package iron.gradetracker.model.action;

import java.util.*;

public class CompositeAction implements Action {
    private final List<Action> actions = new ArrayList<>();

    public CompositeAction(Action... actions) { this.actions.addAll(List.of(actions)); }

    public void addAction(Action action) { actions.add(action); }

    @Override
    public void execute() { actions.forEach(Action::execute); }

    @Override
    public void retract() { actions.reversed().forEach(Action::retract); }
}