/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Matrix;

/**
 *
 * @author mathieu
 */

public class Matrix {

	private int nrows;
	private int ncols;
	private double[][] table;

	public Matrix(double[][] dat) {
		this.table = dat;
		this.nrows = dat.length;
		this.ncols = dat[0].length;
	}

	public Matrix(int nrow, int ncol) {
		this.nrows = nrow;
		this.ncols = ncol;
		table = new double[nrow][ncol];
	}

	public int getNrows() {
		return nrows;
	}

	public void setNrows(int nrows) {
		this.nrows = nrows;
	}

	public int getNcols() {
		return ncols;
	}

	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	public double[][] getValues() {
		return table;
	}

	public void setValues(double[][] values) {
		this.table = values;
	}

	public void setValueAt(int row, int col, double value) {
		table[row][col] = value;
	}

	public double getValueAt(int row, int col) {
		return table[row][col];
	}

	public boolean isSquare() {
		return nrows == ncols;
	}

	public int size() {
		if (isSquare())
			return nrows;
		return -1;
	}

	public Matrix multiplyByNumber(double constant) {
		Matrix mat = new Matrix(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				mat.setValueAt(i, j, table[i][j] * constant);
			}
		}
		return mat;
	}

}