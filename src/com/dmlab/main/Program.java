package com.dmlab.main;

import java.io.IOException;

import com.dmlab.datastructures.*;
public class Program {

	
	public static void main(String[] args) throws IllegalStateException, IOException {
		
		
		/*Tree t = new Tree(10, 2);
		t.buildRendomTree();
		
		TreeDecomposition treeDecom = new TreeDecomposition(t);
		treeDecom.calculate();*/
		Tree t = new Tree();
		t.fromFile("tree.txt");
		
		TreeDecomposition treeDecom = new TreeDecomposition(t);
		treeDecom.calculate();
		
		System.out.println(treeDecom);
	}

}
