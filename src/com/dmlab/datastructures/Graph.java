package com.dmlab.datastructures;

import java.util.ArrayList;
import java.util.List;

class GraphVertex {
	private String label;
	
	public String getLabel() {
		return label;
	}
	
	public GraphVertex(String _label) {
		label = _label;
	}
	
	@Override
	public String toString() {
		return label;
	}
	
}
class GraphEdge {
	private GraphVertex v1;
	private GraphVertex v2;
	
	public GraphVertex getVertex1() {
		return v1;
	}
	public GraphVertex getVertex2() {
		return v2;
	}
	
	String label;
	
	public GraphEdge(GraphVertex _v1, GraphVertex _v2, String _label) {
		v1 = _v1;
		v2 = _v2;
		
		label = _label;
	}
	
	@Override
	public String toString() {
		return "[" + (label.equals("") ? "" : ( "Label:" + label )) + "(" + v1 + "," + v2 + ")]";
	}
	
}

public class Graph {
	
	List<GraphVertex> vertices = new ArrayList<GraphVertex>();
	List<GraphEdge> edges = new ArrayList<GraphEdge>();
	
	private int numVertices = 0;
	
	public Graph(int _numVertices) {
		numVertices = _numVertices;
	} 
	
	public void buildRandom(double connectionProb) {
		for(int i=0; i<numVertices; i++) {
			vertices.add(new GraphVertex("R"+i));
		}
		for(int i=0; i<numVertices; i++) {
			for(int j=0; j<numVertices; j++) {
				if(i==j) {
					continue;
				}
				if(Math.random() < connectionProb) {
					edges.add(new GraphEdge(vertices.get(i), vertices.get(j), ""));
				}
			}
		}
	}
	
	
	
	public int calculateHomomorphisms(Tree patternGraph) {
		
		TreeDecomposition treeDecomp = new TreeDecomposition(patternGraph);
		treeDecomp.calculate();
		
		return 0;
	}
}
