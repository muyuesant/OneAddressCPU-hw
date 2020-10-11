import java.util.HashMap;

/**
 * @author Charles Kann
 * Purpose To parse each assembly language statement
 * 
 * Procedure This class implements an instruction parser.  Each 
 *           instruction has its own parser, which is instantiated
 *           and stored in the static parsers table when the class
 *           is created.  The parsers table is just a HashMap 
 *           that stores the relevant parser with a string 
 *           that is used to find it.  If the instruction is 
 *           found, the parser is returned.  If it is not found
 *           a null value is returned.
 *           
 *           When parsing through the code in the program
 *           SecondPassParser, each line that is a program
 *           instruction (e.g. a line that is not an assembly
 *           directive, comment, or blank line) is parsed to
 *           retrieve the name of the operation.  This instruction
 *           string is used to retrieve the appropriate parser, and
 *           that parser will then parse the rest of the line, and 
 *           generate the correct assembly code.
 *           
 *           This class defines the parsers and the parsers table,
 *           and provides a mechanism for retrieving the parsers.
 *           It provides the helper functions for the SecondPassParser.
 *           This class does not process lines in the assembly input file.
 *           Processing the file and processing each line in the file
 *           is done by the SecondPassParser.
 *           
 * Program History:
 *     7/6/2016    - CWK - Initial release
 */

public class InstructionParser {
	
	// the following is the table of parsers
	static HashMap<String, Parser> parsers = new HashMap<String, Parser>(); 
	
     // Static initializer is used to create a HashMap of Parser to be 
     // returned in the getParser factory method.  
	static {
		parsers.put("add",  new AddParser());	
		parsers.put("addi", new AddiParser());
		parsers.put("sub",  new SubParser());	
		parsers.put("subi", new SubiParser());
		
		//mul, muli, div, and divi, rem, remi, and, andi, and shift
		parsers.put("mul",  new MulParser());	
		parsers.put("muli", new MuliParser());
		
		parsers.put("div",  new DivParser());	
		parsers.put("divi", new DiviParser());
		
		parsers.put("rem",  new RemParser());	
		parsers.put("remi", new RemiParser());
		
		parsers.put("and",  new AndParser());	
		parsers.put("andi", new AndiParser());
		
		parsers.put("shift",  new ShiftParser());	
		
		
		parsers.put("clac", new ClacParser());		
		parsers.put("stor", new StorParser());
		parsers.put("beqz", new BeqzParser());

	}
	
	
	/**
	 * getParser is a factory method to return the correct
	 * parser based on the instruction is string s.
	 * 
	 * @param s the instruction for the parser
	 * @return the parser to use for that instructions.
	 */
	public static Parser getParser(String s) {
		return(parsers.get(s));
	}

	/**
	 * interface Parser defines how to parse the string.  It contains
	 * one method, parse, which parses the string.
	 *
	 */
	static public interface Parser {
		/**
		 * method parse parses the string S and returns the 
		 * machine code for the instructoin.
		 * 
		 * @param s the instruction to process
		 * @param st the symbol table to look up addresses for labels
		 * @param lineNumber if there is an error, line number to print
		 * @return the machine code for this instruction
		 * @throws AssemblerException Exception for errors processing assem
		 */
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException;
	}

	/**
	 * class AddParser parses add instructions.
	 * 
	 * add instructions have the format "add [label/address]:
	 * Op code and ALUopt for add is 20.
	 */
	static private class AddParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			// if first character is a letter, assume a label and convert s to address
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s 
							+ " not found for add - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());

			}

			String branchAddress = shortStringToHexString(s, lineNumber);
			return ("20" + branchAddress);
		}
	}
	
	/**
	 * class AddiParser parses addi instructions.
	 * 
	 * addi instructions have the format "addi value"
	 * Op code and ALUopt for add is 10.
	 */
	static private class AddiParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			String branchAddress = shortStringToHexString(s,lineNumber);
			return ("10" + branchAddress);
		}
	}
	
	/**
	 * class SubParser parses add instructions.
	 * 
	 * add instructions have the format "sub [label/address]"
	 * Op code and ALUopt for add is 21.
	 */	
	static private class SubParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			// if first character is a letter, assume a label and convert s to address
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s 
							+ " not found for add - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());

			}

			String branchAddress = shortStringToHexString(s, lineNumber);
			return ("21" + branchAddress);
		}
	}

	/**
	 * class SubiParser parses subi instructions.
	 * 
	 * subi instructions have the format "subi value"
	 * Op code and ALUopt for add is 11.
	 */
	static private class SubiParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			String branchAddress = shortStringToHexString(s,lineNumber);
			return ("11" + branchAddress);
		}
	}
	
	
	/**
	 * class MulParser parses mul instructions.
	 * 
	 * mul instructions have the format "mul [label/address]:
	 * Op code and ALUopt for mul is 22.
	 */
	static private class MulParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			// if first character is a letter, assume a label and convert s to address
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s 
							+ " not found for mul - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());

			}

			String branchAddress = shortStringToHexString(s, lineNumber);
			return ("22" + branchAddress);
		}
	}
	
	/**
	 * class MuliParser parses muli instructions.
	 * 
	 * muli instructions have the format "muli value"
	 * Op code and ALUopt for muli is 12.
	 */
	static private class MuliParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			String branchAddress = shortStringToHexString(s,lineNumber);
			return ("12" + branchAddress);
		}
	}
	
	/**
	 * class DivParser parses div instructions.
	 * 
	 * div instructions have the format "div [label/address]:
	 * Op code and ALUopt for div is 23.
	 */
	static private class DivParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			// if first character is a letter, assume a label and convert s to address
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s 
							+ " not found for div - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());

			}

			String branchAddress = shortStringToHexString(s, lineNumber);
			return ("23" + branchAddress);
		}
	}
	
	/**
	 * class DiviParser parses divi instructions.
	 * 
	 * divi instructions have the format "divi value"
	 * Op code and ALUopt for divi is 13.
	 */
	static private class DiviParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			String branchAddress = shortStringToHexString(s,lineNumber);
			return ("13" + branchAddress);
		}
	}

	
	
	/**
	 * class RemParser parses rem instructions.
	 * 
	 * rem instructions have the format "rem [label/address]:
	 * Op code and ALUopt for rem is 24.
	 */
	static private class RemParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			// if first character is a letter, assume a label and convert s to address
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s 
							+ " not found for rem - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());

			}

			String branchAddress = shortStringToHexString(s, lineNumber);
			return ("24" + branchAddress);
		}
	}
	
	/**
	 * class RemiParser parses remi instructions.
	 * 
	 * remi instructions have the format "remi value"
	 * Op code and ALUopt for remi is 14.
	 */
	static private class RemiParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			String branchAddress = shortStringToHexString(s,lineNumber);
			return ("14" + branchAddress);
		}
	}

	
	/**
	 * class AndParser parses and instructions.
	 * 
	 * and instructions have the format "and [label/address]:
	 * Op code and ALUopt for and is 25.
	 */
	static private class AndParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			// if first character is a letter, assume a label and convert s to address
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s 
							+ " not found for and - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());

			}

			String branchAddress = shortStringToHexString(s, lineNumber);
			return ("25" + branchAddress);
		}
	}
	
	/**
	 * class AndiParser parses andi instructions.
	 * 
	 * andi instructions have the format "andi value"
	 * Op code and ALUopt for andi is 15.
	 */
	static private class AndiParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			String branchAddress = shortStringToHexString(s,lineNumber);
			return ("15" + branchAddress);
		}
	}
	
	
	/**
	 * class ShiftParser parses shift instructions.
	 * 
	 * shift instructions have the format "shift [label/address]:
	 * Op code and ALUopt for shift is 26.
	 */
	static private class ShiftParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			// if first character is a letter, assume a label and convert s to address
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s 
							+ " not found for shift - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());

			}

			String branchAddress = shortStringToHexString(s, lineNumber);
			return ("26" + branchAddress);
		}
	}
	

	/**
	 * class Clac parses clac instructions.
	 * 
	 * clac instructions have the format "clac"
	 * Op code and ALUopt for add is 30, and the address is 00
	 */
	static private class ClacParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) {
			return "3000";
		}
	}

	/**
	 * class StorParser parses stor instructions.
	 * 
	 * stor instructions have the format "stor [label/address]:
	 * Op code and ALUopt for add is 40.
	 */
	static private class StorParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s + " not found for sub - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());				
			}

			String branchAddress = shortStringToHexString(s, lineNumber);
			return ("40" + branchAddress);
		}
	}

	/**
	 * class BeqzParser parses beqz instruction.
	 * 
	 * beqz instructions have the format "beqz [label/address]"
	 * Op code and ALUopt for add is 50.
	 */
	static private class BeqzParser implements Parser {
		public String parse(String s, HashMap<String, Label>  st, int lineNumber) 
				throws AssemblerException {
			if (Character.isLetter(s.charAt(0))) {
				Label l = st.get(s);
				if (l == null) 
					throw new AssemblerException("label " + s 
							+ " not found for sub - Line Number: " + lineNumber);
				s = Integer.toString(l.getAddress());				
			}
			
			String branchAddress =shortStringToHexString(s, lineNumber);
			return ("50" + branchAddress);
		}
	}

	/**
	 * intStringToHexString takes in an integer and returns a 4 character string 
	 * representing the hex value 
	 * 
	 * @param input The integer value of the string to convert.
	 * @param lineNumber The line number for reporting errors
	 * @return the four digit hex string representing the number
	 * @throws AssemblerException return any parsing errors
	 */
	public  static String intStringToHexString(String input, int lineNumber) 
			throws AssemblerException {
		int number = 0;
		try {
			number = Integer.parseInt(input);
			if (number > 32767 || (number < -32768))
				throw new AssemblerException(" number must be -32768 < n < 32767.  Line Number: " + lineNumber);
		} catch (NumberFormatException nfe) {
			throw new AssemblerException("invalid input " + input + ", valid number must be specified.  Line Number:  " + lineNumber);
		}
		
		String returnString = Integer.toHexString(number);
		
		// Pad out to 4 with 0's
		if (returnString.length() < 4) {
			for (int i = returnString.length(); i < 4; i++) {
				returnString = "0" + returnString;
			}			
		}
		
		// truncate  delete leading f's for negative values
		if (returnString.length() > 4)
			return returnString.substring(returnString.length()-4, 
					returnString.length());
		
		return returnString;
	}
	
	/**
	 * shortStringToHexString takes in an integer and returns a 2 character string 
	 * representing the hex value 
	 * 
	 * @param input The integer value of the string to convert.
	 * @param lineNumber The line number for reporting errors
	 * @return the four digit hex string representing the number
	 * @throws AssemblerException return any parsing errors
	 */
	public  static String shortStringToHexString(String input, int lineNumber) 
			throws AssemblerException {
		int number = 0;
		try {
			number = Integer.parseInt(input);
			if (number > 127 || (number < -128))
				throw new AssemblerException(" number must be -128 < n < 127.  Line Number: " + lineNumber);
		} catch (NumberFormatException nfe) {
			throw new AssemblerException("invalid input " + input + ", valid number must be specified.  Line Number:  " + lineNumber);
		}
		
		String returnString = Integer.toHexString(number);
		
		// Pad out to 4 with 0's
		if (returnString.length() < 2) {
			for (int i = returnString.length(); i < 2; i++) {
				returnString = "0" + returnString;
			}			
		}
		
		// truncate if too long (used for negative values)
		if (returnString.length() > 2)
			return returnString.substring(returnString.length()-4, returnString.length());
		
		return returnString;
	}


}
