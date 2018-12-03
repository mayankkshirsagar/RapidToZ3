package Functionality;

import CFG.CFGTree;

public class ManageEndWhile {

	public static void mainfunction(String lbl)
	{
		while(!CFGTree.nd.peek().ConditionType.equals("WHILE"))
		{
			CFGTree.nd.pop();
		}
		if(CFGTree.nd.peek().ConditionType.equals("WHILE"))
		{
			CFGTree.tmp=CFGTree.nd.pop();
		}
		System.out.println("node popped");
	}
	

}
