package Functionality;

import java.io.FileWriter;
import java.io.IOException;

import CFG.CFGNode;
import CFG.CFGTree;
import ParserToZ3.Parser;

public class ManageWhile {
	
	public static void mainfunction(String lbl) throws IOException
	{
		
		String words[]=lbl.split(" ");
		String condition="";
		for(int i=1;i<words.length-1;i++)
		{
			condition=condition+words[i];
		}
		CFGNode node=new CFGNode("WHILE",condition);
		insertWhile(node);
		String parsered="";
		if(node.prevConditions.length()>0)
		parsered=Parser.parse(node.prevConditions+" & "+node.Condition);
		else
		parsered=Parser.parse(node.Condition);	
		FileWriter f=new FileWriter("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\output.txt",true);
		f.write("(assert "+parsered+" )\n");
		f.close();
		
	}
	
	public static void insertWhile(CFGNode n)
	{

		System.out.println("node inserted");
		if(CFGTree.head==null)
			CFGTree.head=n;
		if(CFGTree.nd.isEmpty()) 
		{
			CFGTree.nd.push(n);
			n.prevConditions="";
			if(CFGTree.tmp==null)
			{
				CFGTree.tmp=n;
			}
			else
			{
				CFGTree.tmp.next=n;
			}
		}
		else
		{	n.prev=CFGTree.nd.peek();
			if(n.prev.prevConditions.length()>0)
			n.prevConditions=n.prev.prevConditions+" & "+n.prev.Condition;
			else
			n.prevConditions=n.prev.Condition;
			System.out.println(n.prevConditions);
			CFGTree.nd.peek().nested.add(n);
			CFGTree.nd.push(n);
		}
	}
	
}

