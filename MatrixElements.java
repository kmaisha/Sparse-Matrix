
public class MatrixElements {
	private int row;
	private int col;
	private int data;
	
	// Constructor 
	MatrixElements(int row, int col, int data){
		this.setRow(row);
		this.setCol(col);
		this.setData(data);
	}
	
	
	/*
	 * Getters and setters for the elements of the matrix 
	 */
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
}

