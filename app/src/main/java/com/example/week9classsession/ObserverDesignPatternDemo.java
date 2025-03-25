package com.example.week9classsession;

import android.util.Log;

import java.util.ArrayList;

public class ObserverDesignPatternDemo {
    public static void main(String[] args) {
        Topic topic50001 = new Topic();
        Subscriber scott = new Subscriber(topic50001);
        Subscriber roger = new Subscriber(topic50001);

        scott.setName("Scott");
        roger.setName("Roger");

        topic50001.postMessage("quiz tomorrow !!!");

        Subscriber man = new Subscriber(topic50001);
        man.setName("Man");

        topic50001.postMessage("hw due this wed!!");
        topic50001.unregister(man);
        topic50001.postMessage("great! man has gone!");

    }
}

interface Observer {
    void update(String msg);

}

class Subscriber implements Observer {
    private String name;

    private String message; // received message
    private Subject subject;

    public Subscriber(Subject subject){
        this.subject = subject;
        // register itself to the subject
        this.subject.register(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        // get update from Subject
        this.message = msg;
        // do something according to the update
        System.out.println("update: " + message + " is received by " + this.name);
    }
}

interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers();

}

class Topic implements Subject {
    private String message;  // the status

    private ArrayList<Observer> observers;

    public Topic (){
        observers = new ArrayList<Observer>();
    }
    public void notifyObservers() {
        for (Observer o: observers)
            o.update(this.message);
    }
    public void register(Observer o) {
        observers.add(o);
    }
    public void unregister(Observer o) {
        observers.remove(o);
    }

    public void postMessage(String message) {
        this.message = message;
        notifyObservers();
    }

// other features particular to Topic
}
