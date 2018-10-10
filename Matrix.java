/*
Name: Sergey Gasparyan
Cruz ID: sgaspary
Assignment Name: Programming Assignment 3
*/
public class Matrix{

	private class Entry{
		int column;
		double value;

		Entry(int column, double value){
			this.column = column;
			this.value = value;
		}
		public boolean equals(Object entry){
			Entry ent = (Entry) entry;
			if(this.column == ent.column && this.value == ent.value){
				return true;
			}else{
				return false;
			}
		}
		public String toString(){
			return ("(" + column + ", " + value + ")");
		}
	}

	//fields for Matrix
	int size;
	int nnz;
	List[] matrix;

	//Constructor for Matrix Class
	Matrix(int n){
		if(n >= 1){
			matrix = new List[n+1];
			size = n;
			nnz = 0;
			int i = 1;
			while(i <= n){
				matrix[i] = new List();
				i++;
			}
		}else{
			throw new RuntimeException("Matrix Error: Matrix(int n) called with n < 1.");
		}
	}

	// returns the size of the Matrix
	int getSize(){
		return size;
	}

	// returns the number of entries in the Matrix
	int getNNZ(){
		return nnz;
	}

	// return true if the two matrices are the same 
	// size and contain the exact same entries, 
	// returns false otherwise.
	public boolean equals(Object x){ 
		Matrix mat = (Matrix) x;
		List r;
		if((nnz != mat.nnz) || (size != mat.size)){
			return false;
		}
		for(int i = 1;i <= size;i++){
			r = matrix[i];
			if((mat.matrix[i].length() != r.length()) || (!(mat.matrix[i].equals(r)))){
				return false;
			}
		}
		return true;
	} 

	// Clears all the entries in the Matrix setting 
	// the Matrix full of zeros.
	void makeZero(){
		if(nnz == 0){
			throw new RuntimeException(
				"Matrix Error: makeZero() called on zero matrix.");
		}
		for(int i =1;i <= size;i++){
			matrix[i].clear();
		}
		nnz = 0;
	}

	// This function makes a deep copy of the Matrix
	// and returns the copy.  
	Matrix copy(){
		Matrix result = new Matrix(size);
		for(int i = 1; i <= size;i++){
			if(matrix[i].length() > 0){
				scalarMultRow(result, matrix[i], 1.0, i);
			}
		}
		return result;
	}

	// This function changes an entry (i, j) in the list 
	// to hold the double x
	// If x = 0 this function deletes the entry 
	void changeEntry(int i, int j, double x){
		if(i >= 1 && i <= size && j >= 1 && j <= size){
			Entry ent = new Entry(j, x);
			List r = matrix[i];
			if(r.length() == 0){
				if(x != 0){
					r.append(ent);
					nnz++;
				}
			}else{
				Entry current;
				Entry back = (Entry) r.back();
				if(back.column < j){
					if(x != 0){
						r.append(ent);
						nnz++;
					}
				}else{
					for(r.moveFront();r.index() != -1;r.moveNext()){
						current = (Entry) r.get();
						if(current.column == j){
							if(x == 0){
								r.delete();
								nnz--;
							}else{
								current.value = x;
							}
							break;
						}else if (current.column > j){
							if(x != 0){
								r.insertBefore(ent);
								nnz++;
							}
							break;
						}
					}
				}
			}
		}else{
			throw new RuntimeException(
				"Matrix Error: changeEntry(int i, int j, double x) called with invalid i and j indices.");
		}
	}
	// function multiplies all the values in the matrix by a double x
	Matrix scalarMult(double x){
		Matrix result = new Matrix(size);
		if(x != 0){
			for(int i =1;i <= size;i++){
				if(matrix[i].length() > 0){
					scalarMultRow(result, matrix[i], x, i);
				}
			}
		}
		return result;
	}
	// This function adds 2 matrices. 
	Matrix add(Matrix M){
		if(size == M.getSize()){
			Matrix result = new Matrix(size);
			List firstMatRow, secondMatRow, resultMatRow;
			if(this.equals(M)){
				return this.scalarMult(2.0);
			}
			for(int i = 1; i <= size; i++){
				firstMatRow = matrix[i];
				secondMatRow = M.matrix[i];
				resultMatRow = result.matrix[i];
				if(firstMatRow.length() == 0 && secondMatRow.length() > 0){
					scalarMultRow(result, secondMatRow, 1.0, i);
				}else if(firstMatRow.length() > 0 && secondMatRow.length() == 0){
					scalarMultRow(result, firstMatRow, 1.0, i);
				}else if(firstMatRow.length() > 0 && secondMatRow.length() > 0){
					addSub(result, firstMatRow, secondMatRow, resultMatRow, 1.0);
				}
			}
			return result;
		}else{
			throw new RuntimeException(
				"Matrix Error: add(Matrix M) called on Matrices of different sizes.");
		}
	}
	Matrix sub(Matrix M){
		if(size == M.getSize()){
			Matrix result = new Matrix(size);
			List firstMatRow, secondMatRow, resultMatRow;
			if(this.equals(M)){
				return result;
			}
			for(int i = 1; i <= size; i++){
				firstMatRow = matrix[i];
				secondMatRow = M.matrix[i];
				resultMatRow = result.matrix[i];
				if(firstMatRow.length() == 0 && secondMatRow.length() > 0){
					scalarMultRow(result, secondMatRow, -1.0, i);
				}else if(firstMatRow.length() > 0 && secondMatRow.length() == 0){
					scalarMultRow(result, firstMatRow, 1.0, i);
				} else if(firstMatRow.length() > 0 && secondMatRow.length() > 0){
					addSub(result, firstMatRow, secondMatRow, resultMatRow, -1.0);
				}
			}
			return result;
		}else{
			throw new RuntimeException(
				"Matrix Error: sub(Matrix M) called on Matrices of different sizes.");
		}
	}
	Matrix transpose(){
		Matrix result = new Matrix(size);
		Entry ent;
		List r;
		for(int i = 1;i <= size;i++){
			r = matrix[i];
			if(r.length() != 0){
				for(r.moveFront();r.index() != -1;r.moveNext()){
					ent = (Entry) r.get();
					result.changeEntry(ent.column, i, ent.value);
				}
			}	
		}
		return result;
	}
	Matrix mult(Matrix M){
		if(size == M.getSize()){
			Matrix result = new Matrix(size);
			Matrix m = M.transpose();
			List r1, r2, r3;
			Entry ent;
			double val = 0;
			for(int i =1; i <= size;i++){
				r1 = matrix[i];
				r3 = result.matrix[i];
				for(int j = 1;j <= size;j++){
					r2 = m.matrix[j];
					val = dot(r1, r2);
					if(val != 0){
						result.changeEntry(i, j, val);
					}
				}
			}
			return result;
		}else{
			throw new RuntimeException(
				"Matrix Error: mult(Matrix M) called on matrices with different sizes.");
		}
	}
	public String toString() {
		Entry ent;
		String result = "";
		for(int i = 1;i <= size;i++){
			if(matrix[i].length() > 0){
				result += i + ":";
				for(matrix[i].moveFront();matrix[i].index() != -1;matrix[i].moveNext()){
					ent = (Entry) matrix[i].get();
					result += " " + ent.toString();
				}
				result += "\n";
			}
		}
		return result;
	}
	private static double dot(List P, List Q){
		double result = 0;
		if(P.length() != 0 && Q.length() != 0){
			Entry ent1;
			Entry ent2;
			P.moveFront();
			Q.moveFront();
			while(P.index() != -1 && Q.index() != -1){
				ent1 = (Entry) P.get();
				ent2 = (Entry) Q.get();
				if(ent1.column == ent2.column){

					result += ent1.value*ent2.value;
					P.moveNext();
					Q.moveNext();
				}else if (ent1.column > ent2.column){
					Q.moveNext();					
				}else{
					P.moveNext();
				}
			}
		}
		return result;
	}
	private void scalarMultRow(Matrix R, List L, double x, int i){
		Entry ent;
		for(L.moveFront(); L.index() != -1;L.moveNext()){
			ent = (Entry) L.get();
			ent = new Entry(ent.column, x*ent.value);
			R.matrix[i].append(ent);
			R.nnz++;
		}
	}
	private void addSub(Matrix result, List r1, List r2, List r3, double op){
		Entry ent1;
		Entry ent2;
		Entry entResult;
		double calc = 0;
		boolean flag = true;
		r1.moveFront();
		r2.moveFront();
		while((r1.index() != -1) && (r2.index() != -1)){
			ent1 = (Entry) r1.get();
			ent2 = (Entry) r2.get();
			if(ent2.column == ent1.column){
				calc = ent1.value + (op * ent2.value);
				if(calc != 0){
					entResult = new Entry(ent1.column, calc);
					r3.append(entResult);
					result.nnz++;
				}
				r1.moveNext();
				r2.moveNext();
			}else if(ent1.column > ent2.column){
				entResult = new Entry(ent2.column, op *ent2.value);
				r3.append(entResult);
				result.nnz++;
				r2.moveNext();
			}else{
				entResult = new Entry(ent1.column, ent1.value);
				r3.append(entResult);
				result.nnz++;
				r1.moveNext();
			}
		}
		for(;r1.index() != -1;r1.moveNext()){
			ent1 = (Entry) r1.get();
			entResult = new Entry(ent1.column, ent1.value);
			r3.append(entResult);
			result.nnz++;
			flag = false;
		}
		if(flag == true){
			for(;r2.index() != -1;r2.moveNext()){
				ent2 = (Entry) r2.get();
				entResult = new Entry(ent2.column, op*ent2.value);
				r3.append(entResult);
				result.nnz++;
			}
		}
	}
}

