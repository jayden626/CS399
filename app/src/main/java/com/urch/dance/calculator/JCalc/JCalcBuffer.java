// JCalc - Standard & Scientific Calculator 
//
// Class JCalcBuffer: Implement a buffer to edit numbers (e.g. 1234.56) 
// 
// methods:
//     JCalcBuffer()     : constructor
//     add0()		 : add '0' at the end of buffer
//     add1()		 : add '1' at the end of buffer
//     add2()		 : add '2' at the end of buffer
//     add3()		 : add '3' at the end of buffer
//     add4()		 : add '4' at the end of buffer
//     add5()		 : add '5' at the end of buffer
//     add6()		 : add '6' at the end of buffer
//     add7()		 : add '7' at the end of buffer
//     add8()		 : add '8' at the end of buffer
//     add9()		 : add '9' at the end of buffer
//     addDot()		 : add '.' at the end of buffer (if '.' not already exist)
//     add(char)         : add a char at the end of buffer
//     add(String)       : add a String at the end of buffer
//     del()		 : delete latest char at the end of buffer
//     toString()	 : return content of buffer in string format
//     print()		 : print the content of buffer
//     clear()		 : clear the content of buffer	
//     boolean isANumber() // Check that buffer is a double
//

package com.urch.dance.calculator.JCalc;

import java.io.*;
import java.util.*;
import java.lang.*;

public class JCalcBuffer
{
        private static StringBuffer buffer;  // the buffer
        private static boolean debug = false; // debug mode
	
        JCalcBuffer()
        {
           buffer = new StringBuffer();
	   if (debug)
	   {
    		System.out.println("Class JCalcBuffer initialisation...");
		this.print();
	   }	    
        }
       
	public void add(char c)  // Add a new char c in "buffer" 
	{
	    buffer.append(c);
	    if (debug)
	    {
		System.out.println("Ajout de "+c);
		this.print();
            }
        }

	public void add(String s)  // Add a string s in "buffer"
	{
	    buffer.append(s);
	    if (debug)
	    {
		System.out.println("Ajout de "+s);
		this.print();
            }
        }

	public void clear()
	{
	     int l = buffer.length();
	     buffer.delete(0,l);
	     if (debug)
	     {
		System.out.println("Clear buffer");
		this.print();
	     }
	}

        public void add0()	{ this.add('0'); }
	public void add1()	{ this.add('1'); }
        public void add2()	{ this.add('2'); }
        public void add3()	{ this.add('3'); }
        public void add4()	{ this.add('4'); }
	public void add5()	{ this.add('5'); }
	public void add6()	{ this.add('6'); }
	public void add7()	{ this.add('7'); }
        public void add8()	{ this.add('8'); }
	public void add9()	{ this.add('9'); }
	public void addDot()	
	{ 
		boolean dotNotExist = true;
		int i;
		char c;
		int l = buffer.length();

		for (i=0; i < l; i++) // check if buffer already contain '.'
		{
			c = buffer.charAt(i);
			if (c == '.') { dotNotExist = false; }
		}
			
		if (dotNotExist) { this.add('.'); }
	}
	
	public void del()
	{
		int l = buffer.length();
		if (l >= 1) { buffer.deleteCharAt(l-1); }
		if (debug)
		{
			System.out.println("Delete last character of buffer");
			this.print();
	     	}
	}
	
        public void print()  // print the content of "buffer"
        {
            System.out.println("Le buffer est:"+buffer.toString());
        }


	public String toString()
	{
		return(buffer.toString());
	}

	public boolean isANumber() // Check that buffer is a double
	{
		Double d;
		String s;

		s = buffer.toString();

		if (debug) System.out.println("Checking buffer isANumber: "+buffer);

		if (s.length() == 0) return(false);
		if (s.equals(" ")) return(false);
		if (s.equals("NaN")) return(false);

		d = new Double(s);

		if (d.isNaN()) return(false);
		
		return(true);
	}

	public static void main(String argv[])
	{
               System.out.println("JCalcBuffer Main...");
               JCalcBuffer B = new JCalcBuffer();

	       B.add0();
	       B.add1();
	       B.add2();
	       B.add3();
	       B.add4();
	       B.add5();
	       B.addDot();
	       B.add6();
	       B.addDot();
               B.add7();
               B.add8();
               B.add9();
	       B.del();
 	       B.clear();
	       B.add0();
	       B.add9();
	       System.out.println("Test de toString:"+B.toString());
        }
}