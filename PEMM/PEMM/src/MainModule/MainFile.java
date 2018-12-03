package MainModule;

//import java.util.*;
import java.io.*;

import Functionality.ManageElse;
import Functionality.ManageElseIf;
import Functionality.ManageEndIf;
import Functionality.ManageEndWhile;
import Functionality.ManageIf;
import Functionality.ManageVariable;
import Functionality.ManageWhile;

public class MainFile {
	public static void  main(String agrs[]) throws IOException
	{
		FileReader fr=new FileReader("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\file.txt");
		FileWriter fw=new FileWriter("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\output.txt",true);
		BufferedReader br=new BufferedReader(fr);
		String lbl="";
		while((lbl=br.readLine())!=null)
		{
			process(lbl);
		}
		br.close();
		fr.close();
		
		
		
		fw.write("(check-sat)\n");
		fw.write("(get-model)");
		fw.close();
		
		
		FileReader fr2=new FileReader("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\output.txt");
		FileWriter fw2=new FileWriter("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\z3code.txt",true);
		BufferedReader br2=new BufferedReader(fr2);
		String line="";
		String temp="";
		while((line=br2.readLine())!=null) {
			String word[]=line.split(" ");
			for(int i=0;i<word.length;i++)
			{
				if(ManageVariable.posvar.containsKey(word[i]))
				{
					word[i]=ManageVariable.posvar.get(word[i]);
				}
				temp=temp+word[i]+" ";
			}
			fw2.write(temp+"\n");
			temp="";
		}
		
		fw2.close();
		br2.close();
		System.out.println("\ntraversing the tree\n");
		CFG.Traverse.mainfunction();
	}
	
	public static void process(String lbl) throws IOException
	{
		if(lbl.startsWith("VAR"))
		{
			ManageVariable.mainfunction(lbl);
		}
		if(lbl.startsWith("IF"))
		{
			ManageIf.mainfunction(lbl);
		}
		if(lbl.startsWith("ELSE"))
		{
			ManageElse.mainfunction(lbl);
		}
		if(lbl.startsWith("ELSEIF"))
		{
			ManageElseIf.mainfunction(lbl);
		}
		if(lbl.startsWith("ENDIF"))
		{
			ManageEndIf.mainfunction(lbl);	
		}
		if(lbl.startsWith("WHILE"))
		{
			ManageWhile.mainfunction(lbl);
		}
		if(lbl.startsWith("ENDWHILE"))
		{
			ManageEndWhile.mainfunction(lbl);
		}
	}
	
}
