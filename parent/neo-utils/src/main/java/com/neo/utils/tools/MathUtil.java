package com.neo.utils.tools;
public class MathUtil {

	/**
	 * 求给定双精度数组中值的最大值
	 * 
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果,如果输入值不合法，返回为-1
	 */
	public static double getMax(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double max = inputData[0];
		for (int i = 0; i < len; i++) {
			if (max < inputData[i])
				max = inputData[i];
		}
		return max;
	}

	/**
	 * 求求给定双精度数组中值的最小值
	 * 
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果,如果输入值不合法，返回为-1
	 */
	public static double getMin(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double min = inputData[0];
		for (int i = 0; i < len; i++) {
			if (min > inputData[i])
				min = inputData[i];
		}
		return min;
	}

	/**
	 * 求给定双精度数组中值的和
	 * 
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double getSum(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double sum = 0;
		for (int i = 0; i < len; i++) {
			sum = sum + inputData[i];
		}

		return sum;

	}
	public static double getSum(Integer[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double sum = 0;
		for (int i = 0; i < len; i++) {
			sum = sum + inputData[i];
		}

		return sum;

	}

	/**
	 * 求给定双精度数组中值的数目
	 * 
	 * @param input
	 *            Data 输入数据数组
	 * @return 运算结果
	 */
	public static int getCount(double[] inputData) {
		if (inputData == null)
			return -1;

		return inputData.length;
	}
	public static int getCount(Integer[] inputData) {
		if (inputData == null)
			return -1;

		return inputData.length;
	}

	/**
	 * 求给定双精度数组中值的平均值
	 * 
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double getAverage(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double result;
		result = getSum(inputData) / len;

		return result;
	}
	public static double getAverage(Integer[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double result;
		result = getSum(inputData) / len;

		return result;
	}
	/**
	 * 求给定双精度数组中值的平方和
	 * 
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double getSquareSum(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double sqrsum = 0.0;
		for (int i = 0; i < len; i++) {
			sqrsum = sqrsum + inputData[i] * inputData[i];
		}

		return sqrsum;
	}

	/**
	 * 求给定双精度数组中值的方差
	 * 
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double getVariance(double[] inputData) {
		int count = getCount(inputData);
		double average = getAverage(inputData);
		double sum = 0d;
		for(int i = 0 ;i<count ;i++){
			sum+=(inputData[i]-average)*(inputData[i]-average);
		}
		double result;
		result = sum / count;

		return result;
	}
	public static double getVariance(Integer[] inputData) {
		int count = getCount(inputData);
		double average = getAverage(inputData);
		double sum = 0d;
		for(int i = 0 ;i<count ;i++){
			sum+=(inputData[i]-average)*(inputData[i]-average);
		}
		double result;
		result = sum / count;

		return result;
	}
	/**
	 * 求给定双精度数组中值的标准差
	 * 
	 * @param inputData
	 *            输入数据数组
	 * @return 运算结果
	 */
	public static double getStandardDiviation(double[] inputData) {
		double result;
		// 绝对值化很重要
		result = Math.sqrt(Math.abs(getVariance(inputData)));

		return result;
	}
	public static double getStandardDiviation(Integer[] inputData) {
		double result;
		// 绝对值化很重要
		result = Math.sqrt(Math.abs(getVariance(inputData)));

		return result;
	}

}
