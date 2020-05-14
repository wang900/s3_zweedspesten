package eventhandlers;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject implements ISubject {

    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void subscribeObserver(IObserver observer) {
        if (observer == null) {
            throw new NullPointerException();
        } else {
            if (!this.observers.contains(observer)) {
                this.observers.add(observer);
            }
        }
    }

    @Override
    public void unsubscribeObserver(IObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(ISubject subject){
        for( IObserver o : observers){
            o.Update(subject);
        }
    }
}
