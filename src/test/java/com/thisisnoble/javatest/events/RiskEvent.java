package com.thisisnoble.javatest.events;


import com.thisisnoble.javatest.Event;


public class RiskEvent implements Event {


    private final String id;
    private final String parentId;
    private final double riskValue;
    private final boolean isOutput;


    public RiskEvent(String id, String parentId, double riskValue) {
    	this(id,parentId,riskValue,false);
    }
    
    public RiskEvent(String id, String parentId, double riskValue, boolean isOutput) {
        this.id = id;
        this.parentId = parentId;
        this.riskValue = riskValue;
        this.isOutput = isOutput;
    }


    public String getId() {
        return id;
    }


    public String getParentId() {
        return parentId;
    }


    public double getRiskValue() {
        return riskValue;
    }
    
    public boolean getIsOutput() {
    	return isOutput;
    }
}
