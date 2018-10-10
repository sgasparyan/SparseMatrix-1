/*
Name: Sergey Gasparyan
Cruz ID: sgaspary
Assignment Name: Programming Assignment 3
 */
import java.io.*;
import java.util.Scanner;

public class Sparce{
	public static void main(String args[]) throws IOException{
		if(args.length != 2){
			throw new RuntimeException("Usage: Sparce <input file> <output file>");
		}
		String lines = null;
		String[] data = null;
		Scanner input = new Scanner(new File(args[0]));
		PrintWriter output = new PrintWriter(new FileWriter(args[1]));
		
		Matrix A = new Matrix(1);
		Matrix B = new Matrix(1);
		Matrix result = new Matrix(1);

		int count = 0;
		int nnzA = 0;
		int nnzB = 0;
		int row = 0;
		int col = 0;
		double value = 0;

		while(input.hasNextLine()){
			count++;
			lines = input.nextLine() + " ";
			data = lines.split(" ");
			if(count == 1){
				A = new Matrix(Integer.parseInt(data[0]));
				B = new Matrix(Integer.parseInt(data[0]));
				result = new Matrix(Integer.parseInt(data[0]));
				nnzA = Integer.parseInt(data[1]);
				nnzB = Integer.parseInt(data[2]);
			}else{
				if(count < nnzA + 3){
					row = Integer.parseInt(data[0]);
					col = Integer.parseInt(data[1]);
					value = Double.parseDouble(data[2]);
					A.changeEntry(row, col ,value);
				}else if(count > nnzA + 3){
					row = Integer.parseInt(data[0]);
					col = Integer.parseInt(data[1]);
					value = Double.parseDouble(data[2]);
					B.changeEntry(row ,col ,value);
				}
			}
		}
		if(nnzA != A.getNNZ() || nnzB != B.getNNZ()){
			throw new RuntimeException(
				"Sparce Error: Incorrect number of non-zero values");
		}
		output.println("A has " + A.getNNZ() + " non-zero entries:\n" + A);
		output.println("B has " + B.getNNZ() + " non-zero entries:\n" + B);
		result = A.scalarMult(1.5);
		output.println("(1.5)*A =\n" + result);
		result = A.add(B);
		output.println("A+B =\n" + result);
		result = A.add(A);
		output.println("A+A =\n" + result);
		result = B.sub(A);
		output.println("B-A =\n" + result);
		result = A.sub(A);
		output.println("A-A =\n" + result);
		result = A.transpose();
		output.println("Transpose(A) =\n" + result);
		result = A.mult(B);
		output.println("A*B =\n" + result);
		result = B.mult(B);
		output.println("B*B =\n" + result);

		input.close();
		output.close();
	}
}
