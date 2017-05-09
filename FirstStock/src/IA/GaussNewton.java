package IA;

import java.util.Arrays;
import java.util.stream.IntStream;

import Matrix.*;

public abstract class GaussNewton {

	private static final double alpha = 1e-6;

	public double[] optimise(double[][] x, double[] y, int numberOfParameters) throws NoSquareException {
		double[] b = new double[numberOfParameters];
		IntStream.range(0, b.length).forEach(i -> b[i] = 1.);
		return optimise(x, y, b);
	}
	
	public double[] optimise(double[][] x, double[] y, double[] b) throws NoSquareException {
		int maxIteration = 1000;
		double oldError = 100;
		double precision = 1e-6;
		double[] b2 = b.clone();
		double gamma = .01;
		for (int i = 0; i < maxIteration; i++) {
			double[][] res = calculateResiduals(x, y, b2);
			double error = calculateError(res);
			System.out.println("Iteration : " + i + ", Error-diff: " + (Math.abs(oldError - error)) + ", b = "+ Arrays.toString(b2));
			if (Math.abs(oldError - error) <= precision) {
				break;
			} 
			oldError = error;
			double[][] jacobs = jacob(b2, x, y.length);
			double[][] values = transjacob(jacobs, res);
			IntStream.range(0, values.length).forEach(j -> b2[j] = b2[j] - gamma * values[j][0]);
		}
		return b2;

	}


	public double calculateError(double[][] res) {
		double sum = 0;
		for (int i = 0; i < res.length; i++) {
			sum += (res[i][0] * res[i][0]);
		}
		return Math.sqrt(sum);
	}

	public double[][] calculateResiduals(double[][] x, double[] y, double[] b) {
		double[][] res = new double[y.length][1];

		for (int i = 0; i < res.length; i++) {
			res[i][0] = findY(x[i][0], b) - y[i];
		}
		return res;
	}


	public double[][] transjacob(double[][] JArray, double[][] res) throws NoSquareException {
		Matrix r = new Matrix(res); // r
		Matrix J = new Matrix(JArray); // J
		Matrix JT = MatrixMathematics.transpose(J); // JT
		Matrix JTJ = MatrixMathematics.multiply(JT, J); // JT * J
		Matrix JTJ_1 = MatrixMathematics.inverse(JTJ); // (JT * J)^-1
		Matrix JTJ_1JT = MatrixMathematics.multiply(JTJ_1, JT); // (JT * J)^-1 * JT
		Matrix JTJ_1JTr = MatrixMathematics.multiply(JTJ_1JT, r); // (JT * J)^-1 * JT * r
		return JTJ_1JTr.getValues();
	}

	public double[][] jacob(double[] b, double[][] x, int numberOfObservations) {
		int numberOfVariables = b.length;
		double[][] jc = new double[numberOfObservations][numberOfVariables];

		for (int i = 0; i < numberOfObservations; i++) {
			for (int j = 0; j < numberOfVariables; j++) {
				jc[i][j] = derivative(x[i][0], b, j);
			}
		}
		return jc;
	}


	public double derivative(double x, double[] b, int bIndex) {
		double[] bCopy = b.clone();
		bCopy[bIndex] += alpha;
		double y1 = findY(x, bCopy);
		bCopy = b.clone();
		bCopy[bIndex] -= alpha;
		double y2 = findY(x, bCopy);
		return (y1 - y2) / (2 * alpha);
	}

	public abstract double findY(double x, double[] b);
	
	
}