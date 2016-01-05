package com.dmlab.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TreeDecomposition {
	Tree tree;
	//TreeDecomVertex root;
	List<TreeDecompositionVertex> rootVertices = new ArrayList<TreeDecompositionVertex>();
	
	public TreeDecomposition(Tree _tree) {
		this.tree = _tree;
	}
	
	private TreeDecompositionVertex calculate(Vertex parent, Vertex rootTree) {
		if(rootTree.getNextCount() == 0) {
			return new TreeDecompositionVertex(parent.getLabel(), rootTree.getLabel());
		} 
		
		TreeDecompositionVertex subRoot = new TreeDecompositionVertex(parent.getLabel(), rootTree.getLabel());
		Iterator<Vertex> children = rootTree.getNext();
		while(children.hasNext()) {
			subRoot.addNext(calculate(rootTree, children.next()));
		}
		return subRoot;
	}
	
	public void calculate() {
		Vertex treeRoot = tree.getRoot();
		Iterator<Vertex> children = treeRoot.getNext();
		
		while(children.hasNext()) {
			rootVertices.add(calculate(treeRoot, children.next()));
		}
	}
	
	@Override
	public String toString() {
		/*StringBuilder sb = new StringBuilder();
		
		Queue<TreeDecomVertex> queue = new LinkedList<TreeDecomVertex>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeDecomVertex node = queue.poll();
		
			if(node.getNextVerticesCount() > 0) {
				sb.append(node.toString()+":");
				Iterator<TreeDecomVertex> nextVertices = node.getNextVertices();
				
				TreeDecomVertex nextVertex = nextVertices.next();
				queue.add(nextVertex);
				
				sb.append(nextVertex);
				
				while(nextVertices.hasNext()) {
					sb.append(",");
					
					nextVertex = nextVertices.next();
					queue.add(nextVertex);
					
					sb.append(nextVertex);
				}
				sb.append("\n");
			}
		}				
		return sb.toString();*/
		return "";
	}
}
