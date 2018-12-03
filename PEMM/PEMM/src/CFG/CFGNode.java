package CFG;

import java.util.*;
public class CFGNode {
	
	public String ConditionType;
	public String Condition;
	public String prevConditions;
	public ArrayList<CFGNode> nested;
	public CFGNode next;
	public CFGNode fal;
	public CFGNode prev;
	
	public CFGNode(String ct,String c)
	{
		ConditionType=ct;
		Condition=c;
		nested=new ArrayList<CFGNode>();
		next=null;
		fal=null;
		prev=null;
		prevConditions="";
	}
	
	
}
