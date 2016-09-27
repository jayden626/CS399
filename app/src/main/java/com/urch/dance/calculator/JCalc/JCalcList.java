// JCalc - Standard & Scientific Calculator 
//
// Class JCalcList: Implement a list of number (1234.56) and operations (+, -, /, *)
//
// methods:
//	JCalcList()			: constructor
//	addToBuffer(char)		: append a character (1,...,9,.) to buffer
//	addToBuffer(String)		: append a String to buffer
//	addBufferToList()		: append buffer to list and clear buffer
//	addStringToList(String s)	: append a String to list (testing purpose)
//	addPlus()			: append buffer to list and append "+" to list
//	addMinus()			: append buffer to list and append "-" to list
// 	addMultiply()			: append buffer to list and append "*" to list
//	addDivide()			: append buffer to list and append "/" to list
// 	Double calculate()		: perform operations in list, clear list and return result
//	printList()			: print the content of the list
package com.urch.dance.calculator.JCalc;
import java.io.*;
import java.util.*;
import java.lang.*;

public class JCalcList extends JCalcBuffer
{
        public static JCalcBuffer buffer;             // temporary buffer
        private static boolean debug = false;          // debug mode
	private static boolean debugCalculate = false; // debug mode for calculation operations
	private static ArrayList OpList;              // list of numbers and operations (before equal appear)

        public JCalcList()
        {
           buffer = new JCalcBuffer();
		   OpList = new ArrayList();
		   if (debug)
		   {
				System.out.println("JCalc initialisation...");
			buffer.print();
		   }
        }
	       
	public void addToBuffer(char c)  // Add a new char c in "buffer"
	{
	    buffer.add(c);
	    if (debug)
	    {
		System.out.println("Ajout de "+c);
		buffer.print();
            }
        }

	public void addToBuffer(String s)  // Add a new string s in "buffer" (testing only)
	{
	    buffer.add(s);
	    if (debug)
	    {
		System.out.println("Ajout de "+s);
		buffer.print();
            }
        }

	public void addBufferToList() // Add the current number (buffer) into the list OpList
	{
		OpList.add(buffer.toString());
		buffer.clear();
	}

	public void addStringToList(String s) // For string s into the list OpList
	{
		OpList.add(s);
	}

	public void addPlus()
	{
		if (buffer.isANumber())
		{
			this.addBufferToList();	// copy current number into list
			this.addStringToList("+"); // Add Operator "+" into list
		}
	}	
	
	public void addMinus()
	{
		if (buffer.isANumber())
		{
			this.addBufferToList();	// copy current number into list
			this.addStringToList("-"); // Add Operator "+" into list
		}
	}	

	public void addMultiply()
	{
		if (buffer.isANumber())
		{
		   	this.addBufferToList();	// copy current number into list
			this.addStringToList("*"); // Add Operator "+" into list
		}
	}	

	public void addDivide()
	{
		if (buffer.isANumber())
		{
			System.out.println("Divide:"+buffer+"XXX");
			this.addBufferToList();	// copy current number into list
			this.addStringToList("/"); // Add Operator "+" into list
		}
		
	}	

	public String printList() // print all element in the list
	{
        String expr = "";
        int size = OpList.size();
        int i;
        for (i=0; i < size; i++)
		{
			expr += OpList.get(i);
			
		}
        return expr;
	}        

	public Double calculate() // calculate the list & print the result (for the moment)
	{
		int i;
	       	int l = OpList.size();
		Double b, result;
		double aa, bb, cc;
	       	String s;
		boolean theFirst = true;
		char Operator;
	
		result = new Double("0");
		b = new Double("0");

		for(i=0; i < l; i++)
		{
			s = OpList.get(i).toString();
			if (debugCalculate) System.out.println("Calculate Element number "+i+":"+s); 
			if (theFirst)
			{
				theFirst = false;
				result = new Double(s);
			}
			if (s.equals("+"))
			{
				s = OpList.get(i+1).toString();
				b = new Double(s);
				aa = result.doubleValue();
				bb = b.doubleValue();
				cc = aa + bb;
				result = new Double(cc);
				if (debugCalculate) System.out.println("Calculate Plus. AA = "+aa+" BB = "+bb+" Result = "+result);	
			}
			if (s.equals("-"))
			{
				s = OpList.get(i+1).toString();
				b = new Double(s);
				aa = result.doubleValue();
				bb = b.doubleValue();
				cc = aa - bb;
				result = new Double(cc);
				if (debugCalculate) System.out.println("Calculate Minus. AA = "+aa+" BB = "+bb+" Result = "+result);	
			}
			if (s.equals("*"))
			{
				s = OpList.get(i+1).toString();
				b = new Double(s);
				aa = result.doubleValue();
				bb = b.doubleValue();
				cc = aa * bb;
				result = new Double(cc);
				if (debugCalculate) System.out.println("Calculate Divide. AA = "+aa+" BB = "+bb+" Result = "+result);	
			}
			if (s.equals("/"))
			{
				s = OpList.get(i+1).toString();
				b = new Double(s);
				aa = result.doubleValue();
				bb = b.doubleValue();
				cc = aa / bb;
				result = new Double(cc);
				if (debugCalculate) System.out.println("Calculate Divide. AA = "+aa+" BB = "+bb+" Result = "+result);	
			}
		}
		if (debugCalculate) System.out.println("End of Calculation...Result = "+result);

		OpList.clear();
		buffer.clear();
		addToBuffer(result.toString());
		return(result);
	}
						
				
	public static void main(String argv[])
	{
               Double a;
	       System.out.println("JCalcList Main...");
               JCalcList L = new JCalcList();

	       L.buffer.add('0');

	       L.addToBuffer("11.2");	       
	       L.addPlus();
	       L.addToBuffer("14.3");
	       L.addMinus();
	       L.addToBuffer(" ");
	       L.addMultiply();
	       L.addToBuffer("3");
	       L.addDivide();
	       L.addStringToList("5");
	       L.printList();	
	       a = L.calculate();
	       System.out.println("Fin de JCalcListMain...result is:"+a);

        }
}