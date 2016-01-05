package com.dmlab.datastructures;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import com.dmlab.utils.FileUtils;

public class Tree {
	
	public static int vertexCount = 0;
		
	private int maxDepth;
	private int maxArity;
	private TreeVertex root;
	
	public Tree(int _maxDepth, int _maxArity) {
		maxDepth = _maxDepth;
		
		maxArity = _maxArity;
		root = new TreeVertex(0, vertexCount+"");
		vertexCount++;
	}
	public Tree() {
		
	}
	
	public Vertex getRoot() {
		return root;
	}
	
	public void buildRendomTree() {
		Queue<TreeVertex> queue = new LinkedList<TreeVertex>();
		queue.add(root);
		Random rand = new Random();
		boolean isRoot = true;
		while(!queue.isEmpty()) {
			TreeVertex v  = queue.poll();
			if(v.getDepth()+1<maxDepth) {
				int childCount = rand.nextInt(maxArity + 1);
				childCount = isRoot && childCount == 0 ? 1 : childCount;
				isRoot = false;
				for(int i=0; i<childCount; i++) {
					TreeVertex child = new TreeVertex(v.getDepth()+1, vertexCount + "");
					queue.add(child);
					v.addNext(child);
					vertexCount++;
				}
			}
		}
	}
	public void fromFile(String treePath) throws IllegalStateException, IOException {
		List<String> lines = FileUtils.readAllLines(treePath);
		
		if(lines.size() <= 0)
			throw new IllegalStateException("Tree must have a root");
		
		Hashtable<String, TreeVertex> hashtable = new Hashtable<String, TreeVertex>();
		root = new TreeVertex(0, lines.get(0));
		hashtable.put(root.getLabel(), root);
		
		for(int i=1; i<lines.size(); i++) {
			String []parentChildren = lines.get(i).split(":");
			
			if(parentChildren.length == 0) {
				throw new IllegalStateException("Parent child relationship is not defined");
			}
			
			if(!hashtable.containsKey(parentChildren[0])) {
				throw new IllegalStateException("Node "+ parentChildren[0]+ " is not defined yet");
			}
			
			TreeVertex parent = hashtable.get(parentChildren[0]);
			String []children = parentChildren[1].split(",");
			for(int j=0; j<children.length; j++) {
				TreeVertex child = new TreeVertex(parent.getDepth() + 1, children[j]);
				hashtable.put(children[j], child);
				parent.addNext(child);
			}
		}
	}

	
	@Override
	public String toString() {
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(root);
		
		int currentDepth = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append(root.getLabel() + "\n");
		
		while(!queue.isEmpty()) {
			
			Vertex v = queue.poll();
			Iterator<Vertex> vertices = v.getNext();
			if(((TreeVertex)v).getDepth() > currentDepth) {
				currentDepth = ((TreeVertex)v).getDepth();
				sb.append("\n");
			}
			while(vertices.hasNext()) {
				Vertex child = vertices.next();  
				sb.append(child.getLabel() + ",");
				queue.add(child);
			}
			sb.append("\t");
		
		}
		return sb.toString();
	}
	
}
