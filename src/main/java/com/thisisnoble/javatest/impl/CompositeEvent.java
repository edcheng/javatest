package com.thisisnoble.javatest.impl;


import com.thisisnoble.javatest.Event;


import java.util.HashMap;
import java.util.Map;


public class CompositeEvent implements Event {


    private final String id;
    private final Event parent;
    private final Map<String, Event> children = new HashMap<>();
    private int procsCnt;


    public CompositeEvent(String id, Event parent) {
        this.id = id;
        this.parent = parent;
        procsCnt=0;
    }


    public String getId() {
        return id;
    }


    public Event getParent() {
        return parent;
    }


    public CompositeEvent addChild(Event child) {
        children.put(child.getId(), child);
        return this;
    }


    @SuppressWarnings("unchecked")
    public <E extends Event> E getChildById(String id) {
        return (E) children.get(id);
    }


    public int size() {
        return children.size();
    }
    
    public boolean getIsOutput() {
    	return true;
    }
    
    public int getProcsCnt() {
    	return procsCnt;
    }
    
    public void addProcsCnt(int cnt) {
    	procsCnt += cnt;
    }
}
