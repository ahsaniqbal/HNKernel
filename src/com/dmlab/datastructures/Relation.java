package com.dmlab.datastructures;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;



public class Relation {
	String v1Label;
	String v2Label;
	
	Hashtable<String, Integer> start = new Hashtable<String, Integer>();
	Hashtable<String, Integer> end = new Hashtable<String, Integer>();

	Hashtable<GraphEdge, Integer> table = new Hashtable<GraphEdge, Integer>();
	
	public Relation(String _v1Label, String _v2Label, List<GraphEdge> edges) {
		Iterator<GraphEdge> edgeIterator = edges.iterator();
		while(edgeIterator.hasNext()) {
			GraphEdge edge = edgeIterator.next();
			if(!table.containsKey(edge)) {
				table.put(edge, 1);
			}
			else {
				throw new IllegalStateException("Edge appears twice");
			}
		}
		updateStartEnd();
	}
	
	private void updateStartEnd() {
		
		start.clear();
		end.clear();
		
		Iterator<Entry<GraphEdge, Integer>> it =  table.entrySet().iterator();
		while (it.hasNext()) {
			Entry<GraphEdge, Integer> entry = it.next();
			GraphEdge edge = entry.getKey();
			
			if(start.containsKey(edge.getVertex1().getLabel())) {
				start.put(edge.getVertex1().getLabel(), 
						start.get(edge.getVertex1().getLabel()) + entry.getValue());
			}
			else {
				start.put(edge.getVertex1().getLabel(), entry.getValue());
			}
			
			if(end.containsKey(edge.getVertex2().getLabel())) {
				end.put(edge.getVertex2().getLabel(), 
						end.get(edge.getVertex2().getLabel()) + entry.getValue());
			}
			else {
				end.put(edge.getVertex2().getLabel(), entry.getValue());
			}
		}
	}
	
	private boolean isMatch(GraphEdge edge1, GraphEdge edge2, int matchType) {
		switch (matchType) {
		case 0:
		{
			if(edge1.getVertex1().getLabel().equals(edge2.getVertex1().getLabel())) {
				return true;
			}
		}
			break;
		case 1:
		{
			if(edge1.getVertex1().getLabel().equals(edge2.getVertex2().getLabel())) {
				return true;
			}
		}
			break;
		case 2:
		{
			if(edge1.getVertex2().getLabel().equals(edge2.getVertex1().getLabel())) {
				return true;
			}
		}
			break;
		default:
			if(edge1.getVertex2().getLabel().equals(edge2.getVertex2().getLabel())) {
				return true;
			}
			break;
		}
		return false;
	}
	
	private int getMatchType(Relation r2) {
		if(v1Label.equals(r2.v1Label)) {
			return 0;
		}
		else if(v1Label.equals(r2.v2Label)) {
			return 1;
		}
		else if(v2Label.equals(r2.v1Label)) {
			return 2;
		}
		else if(v2Label.equals(r2.v2Label)) {
			return 3;
		}
		else {
			throw new IllegalArgumentException("Can't join");
		}
	}
	
	private void semiJoinQuadretic(Relation r2, int matchType) {
		Iterator<Entry<GraphEdge, Integer>> it = table.entrySet().iterator();
		while (it.hasNext()) {
			
			Entry<GraphEdge, Integer> thisEntry = it.next();
			GraphEdge thisEdge = thisEntry.getKey();
			
			Iterator<Entry<GraphEdge, Integer>> it1 = r2.table.entrySet().iterator();
			int matches = 0;
			while(it1.hasNext()) {
				
				Entry<GraphEdge, Integer> entry = it1.next();
				GraphEdge edge = entry.getKey();
				
				matches += isMatch(thisEdge, edge, matchType) ? 1 : 0;
			}
			
			table.put(thisEdge, table.get(thisEdge) * matches);
		}
	}
	
	public void semiJoinQuadretic(Relation r2) {
		semiJoinQuadretic(r2, getMatchType(r2));
	}
	
	
	public void semiJoin(Relation r2) {
		int matchType = getMatchType(r2);
		Iterator<Entry<GraphEdge, Integer>> it = table.entrySet().iterator();
		while (it.hasNext()) {
			
			Entry<GraphEdge, Integer> thisEntry = it.next();
			GraphEdge thisEdge = thisEntry.getKey();
			
			switch (matchType) {
			case 0:
				table.put(thisEdge, table.get(thisEdge) * r2.start.get(thisEdge.getVertex1().getLabel()));
				break;
			case 1:
				table.put(thisEdge, table.get(thisEdge) * r2.end.get(thisEdge.getVertex1().getLabel()));
				break;
			case 2:
				table.put(thisEdge, table.get(thisEdge) * r2.start.get(thisEdge.getVertex2().getLabel()));
				break;
			default:
				table.put(thisEdge, table.get(thisEdge) * r2.end.get(thisEdge.getVertex2().getLabel()));
				break;
			}
		}
		updateStartEnd();
	}
}
