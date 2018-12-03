package Functionality;

import java.io.FileWriter;
import java.io.IOException;

import CFG.CFGNode;
import CFG.CFGTree;
import ParserToZ3.Parser;

public class ManageElseIf {
	public static void mainfunction(String lbl) throws IOException
	{
		String words[]=lbl.split(" ");
		String condition="";
		for(int i=1;i<words.length-1;i++)
		{
			condition=condition+words[i];
		}
		
		CFGNode n=new CFGNode("ELSEIF",condition);
		insertElseIf(n);
		String parsered="";
		if(n.prevConditions.length()>0)
		parsered=Parser.parse(n.prevConditions+" & "+n.Condition);
		else
		parsered=Parser.parse(n.Condition);	
		System.out.println("else if conditon and previous condition "+n.Condition+" "+n.prevConditions);
		FileWriter f=new FileWriter("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\output.txt",true);
		f.write("(assert "+parsered+" )\n");
		f.close();
		
		
	}
	
	public static void insertElseIf(CFGNode n)
	{
	
		n.prev=CFGTree.nd.peek();
		n.prevConditions="(!( "+n.prev.Condition+" ))";
		if(n.prev.prevConditions.length()>0)
		n.prevConditions=n.prevConditions+" & "+n.prev.prevConditions;
		
		CFGTree.nd.peek().fal=n;
		CFGTree.nd.push(n);
		System.out.println("else if node inserteds");
	}
}
