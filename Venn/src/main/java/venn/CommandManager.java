package venn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CommandManager {
	public static class QueueStack<T> {

	    private List<T> dataCollection;

	    QueueStack() {
	        dataCollection = new LinkedList<>();
	    }

	    void push(T item) {
	    	dataCollection.add(item);
	    }

	    Optional<T> pop() {
	        if(dataCollection.size() > 0)
	            return Optional.of(dataCollection.remove(dataCollection.size() - 1));
	        else
	            return Optional.empty();
	    }

	    void clear() {
	        dataCollection.clear();
	    }


	}

	private static CommandManager instance = null;
	private QueueStack<List<Action>> queueStackNormal;
    private QueueStack<List<Action>> queueStackReverse;

    private List<String> actionHistory;

    static CommandManager getInstance(){
        if(instance != null)
            return instance;
        return new CommandManager();
    }

    private CommandManager() {
        queueStackNormal = new QueueStack<>();
        queueStackReverse = new QueueStack<>();
        actionHistory = new ArrayList<>();
    }
    
    void execute(List<Action> actionList){
        actionList.forEach(Action::execute);
        queueStackNormal.push(actionList);
        actionList.forEach(a -> actionHistory.add(a.getName()));
    }

    void undo() {
        Optional<List<Action>> optionalActions = queueStackNormal.pop();
        optionalActions.ifPresent(aList -> {
            aList.forEach(Action::undo);
            queueStackReverse.push(aList);
            aList.forEach(a -> actionHistory.add(a.getName() + " - undo"));
        });
    }

    void redo() {
        Optional<List<Action>> optionalActions = queueStackReverse.pop();
        optionalActions.ifPresent(aList -> {
            aList.forEach(Action::execute);
            queueStackNormal.push(aList);
            aList.forEach(a -> actionHistory.add(a.getName() + " - redo"));
        });
    }
    void clearNormal() {
        queueStackNormal.clear();
    }

    void clearReverse() {
        queueStackReverse.clear();
    }


}

