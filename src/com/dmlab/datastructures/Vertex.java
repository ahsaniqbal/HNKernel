package com.dmlab.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex {
	protected String label = "";
	protected List<Vertex> next = new ArrayList<Vertex>();
	//public Vertex(){}
	public Vertex(String _label) {
		label = _label;
	}
	
	public void addNext(Vertex _vertex) {
		next.add(_vertex);
	}
	public Iterator<Vertex> getNext() {
		return next.iterator();
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getNextCount() {
		return next.size();
	}
	
	@Override
	public String toString() {
		return "[Label:"+label+" NumConnections:"+next.size()+"]";
	}
}
class TreeVertex extends Vertex
{
	private int depth;
	public TreeVertex(int _depth, String _label) {
		super(_label);
		depth = _depth;
	}
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int _depth) {
		depth = _depth;
	}
	
	@Override
	public String toString() {
		return "[Label:"+label+" NumConnections:"+next.size()+" Depth:"+depth+"]";
	}
}
class TreeDecompositionVertex extends Vertex
{
	private String label2="";
	public TreeDecompositionVertex(String _label, String _label2) {
		super(_label);
		label2 = _label2;
	}
	
	public String getLabel1() {
		return super.getLabel();
	}
	public String getLabel2() {
		return label2;
	}
	
	@Override
	public String getLabel() {
		return "("+label+","+label2+")";
	}
	@Override
	public String toString() {
		return "[Label:"+getLabel()+" NumConnections:"+next.size()+"]";
	}
}