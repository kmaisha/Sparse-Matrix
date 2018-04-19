import java.util.*;
import java.util.LinkedList;

public class SparseMatrix implements SparseInterface {
	
	public static void main(String [] args) {
		
	}
	//Creates a matrix using linked list 
	LinkedList<MatrixElements> SM = new LinkedList<>();
	private int Size;
	private int numRows;
	private int numCols;
	
	//Constructor sets the matrix size to 3 x 3
	public SparseMatrix(){
		this.Size = 3;
	}
	//Sets all of the entries in the matrix to 0
	public void clear(){
		SM.clear();
	}
	
	//This method clears everything first and sets the size by number rows and columns 
	public void setSize(int numRows, int numCols) {
		clear();
		this.numRows = numRows;
		this.numCols = numCols;		
	}
    
	//This method returns the number of rows 
    public int getNumRows() {
    	return this.numRows;
    }
    //This method returns the number of columns 
    public int getNumCols() {
		return this.numCols;    	
    }

	@Override
	//This method is to input elements in the matrix 
	public void addElement(int row, int col, int data) {
		
		//Removes any the pre-existing elements from the matrix
		removeElement(row, col);
		//Condition to check the bounds for size and data (especially 0s)
		if(row <  0 || col < 0 || row > Size || col > Size){
			System.out.print("Error Message: Invalid entry point.");
			return;
		}
		if (data == 0) {
			System.out.println("Error Message: Invalid entry point.");
			return;
		}
		
		//Makes a new node in list to add the elements 
		MatrixElements elements = new MatrixElements (row, col, data);
		SM.add(elements);
	}

	@Override
	//This method is to remove elements in the matrix 
	public void removeElement(int row, int col) {
		//Condition checks the bounds of the matrix
		if (row > Size || col > Size || row < 0 || col < 0){
			System.out.println("Error Message: Row/Column combination is out of bounds.");
			return;
	}
	
		//For Loop traverses the list to remove a specific data value at designated row and column 
		for (int i = 0; i < SM.size(); i++){
			MatrixElements values = SM.get(i);
			if (values.getRow() == row && values.getCol() == col){
				SM.remove(values);
				break;
			}
		}	
	}

	@Override
	//This method returns a specific data value at given row and column
	public int getElement(int row, int col) {
		//Checks the bound
		if (row > Size || col > Size || row < 0 || col < 0){
			System.out.println ("Error Message: Row/Column combination is out of bounds.");
			return -1;
    	}
    		//Traverse the list to return a specific data value at specific position
    		for (int i = 0; i < SM.size(); i++){
    			MatrixElements values = SM.get(i);
    			if (values.getRow() == row && values.getCol() == col){
    				return values.getData();
    			}
    		}
    	return 0;
	}
	//This method returns the matrix in form of "row, column, data"
	public String toString() {
		String print = "";
		//Goes through the rows 
		for (int r = 0; r < Size; r++){
			//Goes through the columns 
			for (int c = 0; c < Size; c++){
				//Goes through the linked list
				for (int d = 0; d < SM.size(); d++){
					//Checks if the element is there. If it is, return "row, column, data"
					MatrixElements extract = SM.get(d);
						if (extract.getRow() == r && extract.getCol() == c){
							print += extract.getRow() + " " + extract.getCol() + " " + extract.getData() + "\n";
					}
				}
			}
		}
		return print;
	}
	@Override
	public SparseInterface addMatrices(SparseInterface matrixToAdd) {
		//Condition to check the matrix bound
		if (matrixToAdd.getNumRows() != this.numRows && matrixToAdd.getNumCols() != this.numCols) {
			return null;
		}
		SparseInterface resultMatrix = new SparseMatrix();
		resultMatrix.setSize(numRows, numCols);
		//nested for loop to traverse through the matrix and add the matrices if the size are equal 
		for (int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				resultMatrix.addElement(i, j, matrixToAdd.getElement(i, j) + this.getElement(i, j));
			}
		}
		
		
		return resultMatrix;
	}

	@Override
	public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {
		//Condition to check the matrix bound
		if (matrixToMultiply.getNumRows() != this.numRows && matrixToMultiply.getNumCols() != this.numCols) {
			return null;
		}
		
		SparseInterface resultMatrix = new SparseMatrix();
		resultMatrix.setSize(numRows, numCols);
		//nested for loop to traverse through the matrix and multiply the matrices if the size are equal 
		for (int i = 0; i < resultMatrix.getNumRows(); i++) {
			for(int j = 0; j < resultMatrix.getNumCols(); j++) {
				int sum = 0;
				for(int k = 0; k < resultMatrix.getNumRows(); k++) {
					sum += getElement(i,k) * matrixToMultiply.getElement(k,j);
				//resultMatrix.addElement(i, j, matrixToMultiply.getElement(i, k) * this.getElement(k, j));
				}
				resultMatrix.addElement(i, j, sum);
			}
		}
		return resultMatrix;
	}
    
    
	
}

