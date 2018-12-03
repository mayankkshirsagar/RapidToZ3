package CFG;

public class Traverse {

	public static void mainfunction()
	{
		System.out.println("main function called");
		perform(CFGTree.head);
		
	}
	
	public static void traverse(CFGNode Y)
	{	
		if(Y!=null) {
		System.out.println(Y.Condition+" is condition");
		System.out.println(Y.ConditionType+" is condition type");
		for(int i=0;i<Y.nested.size();i++)
		{
			System.out.println("nested conditions");
			perform(Y.nested.get(i));
			
		}
		}
		//traverse(CFGTree.head.fal);	
	}
	
	
	public static void perform(CFGNode n)
	{
		while(n!=null)
		{
			traverse(n);
			traverse(n.fal);
			n=n.next;
		}
	}
}
