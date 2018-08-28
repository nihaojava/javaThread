package chapter05;

import java.util.LinkedList;


/**
 * Created by zhangzhao on 2018/8/23.
 */
public class EventQueue {
    private final int max;
    static class Event{

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private final static int DEFAULT_MAX_EVENT = 10;
    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }
    public EventQueue(int max){
        this.max = max;
    }

    public void offer(Event event){
        synchronized (eventQueue){
            while(eventQueue.size()>=max){
                console("the queue is full");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            while(eventQueue.isEmpty()){
                console("the queue is empty");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console("the event +" + event + "is handled");
            return event;
        }
    }

    private void console(String message){
        System.out.printf("%s:%s\n",Thread.currentThread().getName(),message);
    }
}
