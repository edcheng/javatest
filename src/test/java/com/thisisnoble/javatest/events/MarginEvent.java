package com.thisisnoble.javatest.events;


import com.thisisnoble.javatest.Event;


public class MarginEvent implements Event {


    private final String id;
    private final String parentId;
    private final double margin;
    private final boolean isOutput;


    public MarginEvent(String id, String parentId, double margin) {
        this(id,parentId,margin,false);
    }

    public MarginEvent(String id, String parentId, double margin, boolean isOutput) {
        this.id = id;
        this.parentId = parentId;
        this.margin = margin;
        this.isOutput = isOutput;
    }


    public String getId() {
        return id;
    }


    public String getParentId() {
        return parentId;
    }


    public double getMargin() {
        return margin;
    }
    
    public boolean getIsOutput() {
    	return isOutput;
    }
}
