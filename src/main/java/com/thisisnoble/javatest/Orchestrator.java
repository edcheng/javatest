package com.thisisnoble.javatest;


public interface Orchestrator {


    void register(Processor processor);


    void receive(Event event);


    void setup(Publisher publisher);
}
