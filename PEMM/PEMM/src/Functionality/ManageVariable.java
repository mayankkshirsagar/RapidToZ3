package Functionality;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class ManageVariable {
	
	public static HashMap<String,String> posvar= new HashMap<>();
	
	
	public static void mainfunction(String lbl) throws IOException
	{
		
		//f.write("hi");
		if(lbl.contains("num")||lbl.contains("byte")||lbl.contains("dnum"))
		{
			numVariable(lbl);
		}
		if(lbl.contains("string"))
		{
			stringVariable(lbl);
		}
		if(lbl.contains("bool"))
		{
			boolVariable(lbl);
		}
		if(lbl.contains("pos"))
		{
			posVariable(lbl);
		}
		
	}
	
	public static void numVariable(String lbl) throws IOException
	{
		String words[]=lbl.split(":=|;");
		if(lbl.contains(".")) // insert real 
		{
			String word[]=words[0].split(" ");
			String temp="";
			for(int i=2;i<word.length;i++)
			{
				temp=temp+word[i];
			}
			String var[]=temp.split(",");
			FileWriter f=new FileWriter("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\output.txt",true);
			for(int i=0;i<var.length;i++)
			{
				f.write("(declare-const "+var[i]+" Real)\n");
			}
			f.close();
			
		}
		else // insert number
		{
			String word[]=words[0].split(" ");
			String temp="";
			for(int i=2;i<word.length;i++)
			{
				temp=temp+word[i];
			}
			String var[]=temp.split(",| ");
			FileWriter f=new FileWriter("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\output.txt",true);
			for(int i=0;i<var.length;i++)
			{
				f.write("(declare-const "+var[i]+" Int)\n");
			}
			f.close();
		}
		
	}
	public static void stringVariable(String lbl)
	{
		
	}
	
	public static void boolVariable(String lbl) throws IOException
	{
		String words[]=lbl.split(":=|;");
		String word[]=words[0].split(" ");
		String temp="";
		for(int i=2;i<word.length;i++)
		{
			temp=temp+word[i];
		}
		String var[]=temp.split(",| ");
		FileWriter f=new FileWriter("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\output.txt",true);
		for(int i=0;i<var.length;i++)
		{
			f.write("(declare-const "+var[i]+" Bool)\n");
		}
		f.close();
		
	}
	
	public static void posVariable(String lbl) throws IOException
	{
		String words[]=lbl.split(":=|;");
		String word[]=words[0].split(" ");
		String temp="";
		for(int i=2;i<word.length;i++)
		{
			temp=temp+word[i];
		}
		String var[]=temp.split(",| ");
		FileWriter f=new FileWriter("C:\\Users\\Mayank\\git\\PEMM\\PEMM\\src\\output.txt",true);
		for(int i=0;i<var.length;i++)
		{
			f.write("(declare-const "+var[i]+"_x Int)\n");
			f.write("(declare-const "+var[i]+"_y Int)\n");
			f.write("(declare-const "+var[i]+"_z Int)\n");
			posvar.put(var[i]+".x", var[i]+"_x");
			posvar.put(var[i]+".y", var[i]+"_y");
			posvar.put(var[i]+".z", var[i]+"_z");
		}
		f.close();
		
		
		
	}
	
	
	
}
