package com.thisisnoble.javatest.events;


import com.thisisnoble.javatest.Event;


public class ShippingEvent implements Event {


    private final String id;
    private final String parentId;
    private final double shippingCost;
    private final boolean isOutput;

    public ShippingEvent(String id, double shippingCost) {
        this(id, null, shippingCost);
    }


    public ShippingEvent(String id, String parentId, double shippingCost) {
    	this(id,parentId,shippingCost,false);
    }

    public ShippingEvent(String id, String parentId, double shippingCost, boolean isOutput) {
        this.id = id;
        this.parentId = parentId;
        this.shippingCost = shippingCost;
        this.isOutput = isOutput;
    }


    public String getId() {
        return id;
    }


    public String getParentId() {
        return parentId;
    }


    public double getShippingCost() {
        return shippingCost;
    }
    
    public boolean getIsOutput() {
    	return isOutput;
    }
}
