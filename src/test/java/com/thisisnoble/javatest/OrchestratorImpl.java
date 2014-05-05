package com.thisisnoble.javatest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.thisisnoble.javatest.Event;
import com.thisisnoble.javatest.Orchestrator;
import com.thisisnoble.javatest.Processor;
import com.thisisnoble.javatest.Publisher;
import com.thisisnoble.javatest.events.MarginEvent;
import com.thisisnoble.javatest.events.RiskEvent;
import com.thisisnoble.javatest.events.ShippingEvent;
import com.thisisnoble.javatest.impl.CompositeEvent;
import com.thisisnoble.javatest.util.TestIdGenerator;

public class OrchestratorImpl implements Orchestrator {

	private List<Processor> processors;
	private ConcurrentMap<String,Event> procEvtMap = new ConcurrentHashMap<String,Event>();
	private Publisher publisher;

	public OrchestratorImpl() {
		processors = new ArrayList<Processor>();
	}
	
    public void register(Processor processor) {
    	processors.add(processor);
    }


    public void receive(Event event) {
		int cnt =0;
		CompositeEvent ce = null;
		synchronized(this){

			for (Processor p : processors) {
				if (p.interestedIn(event)) {
					p.process(event);
					cnt++;
				}
			}
			if (cnt > 0) {

				if (!procEvtMap.containsKey(getParentId(event.getId()))) {
					ce = new CompositeEvent(TestIdGenerator.compositeEventId(event.getId()),event);
				} else {
					ce = getCompositeEventbyParent(getParentId(event.getId()));
				}
				ce.addProcsCnt(cnt);
				procEvtMap.put(getParentId(event.getId()), ce);
			}
		}

    	if (event.getIsOutput()) {
			synchronized(this){
				if (event instanceof RiskEvent) {
					RiskEvent output = (RiskEvent) event;
					ce = getCompositeEventbyParent(getParentId(output.getParentId()));
					ce.addChild(output);
				} else if (event instanceof MarginEvent) {
					MarginEvent output = (MarginEvent)event;
					ce = getCompositeEventbyParent(getParentId(output.getParentId()));
					ce.addChild(output);
				} else if (event instanceof ShippingEvent) {
					ShippingEvent output = (ShippingEvent) event;
					ce = getCompositeEventbyParent(getParentId(output.getParentId()));
					ce.addChild(output);
				}

				if (ce.getProcsCnt() == ce.size()) {
					publisher.publish(ce);
					procEvtMap.remove(getParentId(ce.getParent().getId()));

				}
			}
		}
    }


    public String getParentId(String id) {
    	String delimitor = "-";
    	if (!id.contains(delimitor))
    		return id;
    	return id.split(delimitor)[0];
    }

    public CompositeEvent getCompositeEventbyParent(String id) {
    		return (CompositeEvent)procEvtMap.get(id);
    }
    
    public void setup(Publisher publisher) {
    	this.publisher = publisher;
    }
}
