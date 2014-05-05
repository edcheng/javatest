package com.thisisnoble.javatest.events;


import com.thisisnoble.javatest.Event;


public class TradeEvent implements Event {


    private final String id;
    private final double notional;
    private final boolean isOutput;

    public TradeEvent(String id, double notional) {
    	this(id,notional,false);
    }

    public TradeEvent(String id, double notional,boolean isOutput) {
        this.id = id;
        this.notional = notional;
        this.isOutput = isOutput;
    }


    public String getId() {
        return id;
    }


    public double getNotional() {
        return notional;
    }
    
    public boolean getIsOutput() {
    	return isOutput;
    }
}
