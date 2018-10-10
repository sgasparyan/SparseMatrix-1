/*
Name: Sergey Gasparyan
Cruz ID: sgaspary
Assignment Name: Programming Assignment 3
*/
class MatrixTest {

	public static void main(String[] args){
		Matrix A = new Matrix(100000);
		Matrix B = new Matrix(100000);

		A.changeEntry(1,1,16);
		A.changeEntry(1,2,98);
		A.changeEntry(1,3,-4);
		A.changeEntry(1,4,3.2);
		A.changeEntry(2,1,1);
		A.changeEntry(2,2,5);
		A.changeEntry(2,3,9);
		A.changeEntry(2,4,0);
		A.changeEntry(3,1,3);
		A.changeEntry(3,2,6);
		A.changeEntry(3,3,11);
		A.changeEntry(3,4,-2);
		A.changeEntry(4,1,.6);
		A.changeEntry(4,2,1);
		A.changeEntry(4,3,92);
		A.changeEntry(4,4,-10);

		B.changeEntry(1,1,12);
		B.changeEntry(1,2,-27);
		B.changeEntry(1,3,3);
		B.changeEntry(1,4,0);
		B.changeEntry(2,1,5);
		B.changeEntry(2,2,3);
		B.changeEntry(2,3,1);
		B.changeEntry(2,4,2.2);
		B.changeEntry(3,1,-7);
		B.changeEntry(3,2,4);
		B.changeEntry(3,3,14);
		B.changeEntry(3,4,-6);
		B.changeEntry(4,1,.2);
		B.changeEntry(4,2,18);
		B.changeEntry(4,3,29);
		B.changeEntry(4,4,64);

		System.out.println("Number of none-zero values in A: " + A.getNNZ());
		System.out.println("Size of A: " + A.getSize());
		System.out.println(A);
		System.out.println("Number of none-zero values in B: " + B.getNNZ());
		System.out.println("Size of B: " + B.getSize());
		System.out.println(B);

	    Matrix C = A.scalarMult(-6.2);
        System.out.println("Number of none-zero values in A*(-6.2): " + C.getNNZ());
        System.out.println("A*(-6.2) =\n" + C);

        C = A.add(A);
        System.out.println("Number of none-zero values in A + A: " + C.getNNZ());
        System.out.println("A + A =\n" + C);

        C = A.sub(B);
        System.out.println("Number of none-zero values in A - B: " + C.getNNZ());
        System.out.println("A - B =\n" + C);

        C = A.sub(A);
        System.out.println("Number of none-zero values in A - A: " + C.getNNZ());
        System.out.println("A - A =\n" + C);

        C = B.transpose();
        System.out.println("Number of none-zero values in Transpose(B): " + C.getNNZ());
        System.out.println("Transpose(B) =\n" + C);

        C = A.mult(B);
        System.out.println("Number of none-zero values in A*B: " + C.getNNZ());
        System.out.println("A*B =\n" + C);

        C = A.copy();
        System.out.println("Copy of A =\n" + C);

        System.out.println(A.equals(C));
        System.out.println(A.equals(B));
        System.out.println(A.equals(A));

        A.makeZero();
        System.out.println(A.getNNZ());
        System.out.println(A);
    }
}
        /*
        	Correct Output of Test:

			Number of none-zero values in A: 15
			Size of A: 100000
			1: (1, 16.0) (2, 98.0) (3, -4.0) (4, 3.2)
			2: (1, 1.0) (2, 5.0) (3, 9.0)
			3: (1, 3.0) (2, 6.0) (3, 11.0) (4, -2.0)
			4: (1, 0.6) (2, 1.0) (3, 92.0) (4, -10.0)

			Number of none-zero values in B: 15
			Size of B: 100000
			1: (1, 12.0) (2, -27.0) (3, 3.0)
			2: (1, 5.0) (2, 3.0) (3, 1.0) (4, 2.2)
			3: (1, -7.0) (2, 4.0) (3, 14.0) (4, -6.0)
			4: (1, 0.2) (2, 18.0) (3, 29.0) (4, 64.0)

			Number of none-zero values in A*(-6.2): 15
			A*(-6.2) =
			1: (1, -99.2) (2, -607.6) (3, 24.8) (4, -19.840000000000003)
			2: (1, -6.2) (2, -31.0) (3, -55.800000000000004)
			3: (1, -18.6) (2, -37.2) (3, -68.2) (4, 12.4)
			4: (1, -3.7199999999999998) (2, -6.2) (3, -570.4) (4, 62.0)

			Number of none-zero values in A + A: 15
			A + A =
			1: (1, 32.0) (2, 196.0) (3, -8.0) (4, 6.4)
			2: (1, 2.0) (2, 10.0) (3, 18.0)
			3: (1, 6.0) (2, 12.0) (3, 22.0) (4, -4.0)
			4: (1, 1.2) (2, 2.0) (3, 184.0) (4, -20.0)

			Number of none-zero values in A - B: 16
			A - B =
			1: (1, 4.0) (2, 125.0) (3, -7.0) (4, 3.2)
			2: (1, -4.0) (2, 2.0) (3, 8.0) (4, -2.2)
			3: (1, 10.0) (2, 2.0) (3, -3.0) (4, 4.0)
			4: (1, 0.39999999999999997) (2, -17.0) (3, 63.0) (4, -74.0)

			Number of none-zero values in A - A: 0
			A - A =

			Number of none-zero values in Transpose(B): 15
			Transpose(B) =
			1: (1, 12.0) (2, 5.0) (3, -7.0) (4, 0.2)
			2: (1, -27.0) (2, 3.0) (3, 4.0) (4, 18.0)
			3: (1, 3.0) (2, 1.0) (3, 14.0) (4, 29.0)
			4: (2, 2.2) (3, -6.0) (4, 64.0)

			Number of none-zero values in A*B: 16
			A*B =
			1: (1, 710.64) (2, -96.4) (3, 182.8) (4, 444.40000000000003)
			2: (1, -26.0) (2, 24.0) (3, 134.0) (4, -43.0)
			3: (1, -11.4) (2, -55.0) (3, 111.0) (4, -180.8)
			4: (1, -633.8) (2, 174.8) (3, 1000.8) (4, -1189.8)

			Copy of A =
			1: (1, 16.0) (2, 98.0) (3, -4.0) (4, 3.2)
			2: (1, 1.0) (2, 5.0) (3, 9.0)
			3: (1, 3.0) (2, 6.0) (3, 11.0) (4, -2.0)
			4: (1, 0.6) (2, 1.0) (3, 92.0) (4, -10.0)

			true
			false
			true
			0
        */
