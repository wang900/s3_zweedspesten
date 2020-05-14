package eventhandlers;

public interface ISubject {
    public void subscribeObserver(IObserver observer);
    public void unsubscribeObserver(IObserver observer);
}
