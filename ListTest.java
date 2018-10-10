/*
Name: Sergey Gasparyan
Cruz ID: sgaspary
Assignment Name: Programming Assignment 3
*/
public class ListTest{
    public static void main(String[] args){
        List A = new List();
        List B = new List();

        for(int a = -10; a <= 10; a++){
            A.append(a);
            B.prepend(a);
        }
        System.out.println(A);
        System.out.println("Length of List A = " + A.length());
        System.out.println("Front of List A = " + A.front());
        System.out.println("Back of List A = " + A.back());
        System.out.println();
        System.out.println(B);
        System.out.println("Length of List B = " + B.length());
        System.out.println("Front of List B = " + B.front());
        System.out.println("Back of List B = " + B.back());
        System.out.println();
        for(A.moveFront(); A.index() != -1; A.moveNext()){

            System.out.print(A.get() + " ");
        }
        System.out.println();
        System.out.println();
        for(B.moveBack(); B.index() != -1; B.movePrev()){

            System.out.print(B.get()+ " ");

        }
        System.out.println();
        System.out.println();
        A.moveFront();
        A.insertBefore(7);
        System.out.println("List with 7 inserted before cursor =\n" + A);
        A.insertAfter(12);
        System.out.println("List with 12 inserted after cursor =\n" + A);
        A.deleteFront();
        System.out.println("List with front deleted =\n" + A);
        System.out.println("Length of List = " + A.length());
        A.deleteBack();
        System.out.println("List with back deleted =\n" + A);
        System.out.println("Length of List = " + A.length());
        A.delete();
        System.out.println("List with cursor deleted =\n" + A);
        System.out.println("Length of List = " + A.length());
        System.out.println(A.equals(B));
        System.out.println(A.equals(A));
        A.clear();
        System.out.println("Length of empty List = " + A.length());
    }
}
        /*
          Correct output of Test:

          -10 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9 10
          Length of List A = 21
          Front of List A = -10
          Back of List A = 10

          10 9 8 7 6 5 4 3 2 1 0 -1 -2 -3 -4 -5 -6 -7 -8 -9 -10
          Length of List B = 21
          Front of List B = 10
          Back of List B = -10

          -10 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9 10

          -10 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9 10

          List with 7 inserted before cursor =
          7 -10 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9 10
          List with 12 inserted after cursor =
          7 -10 12 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9 10
          List with front deleted =
          -10 12 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9 10
          Length of List = 22
          List with back deleted =
          -10 12 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9
          Length of List = 21
          List with cursor deleted =
          12 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9
          Length of List = 20
          false
          true
          Length of empty List = 0
        */
