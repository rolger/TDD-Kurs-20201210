package calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {

	public BigDecimal add(String numbers) {
		if (numbers == null || numbers.isEmpty())
			throw new IllegalArgumentException();

		String[] split = numbers.split(",");
		BigDecimal result;
		try {
			result = new BigDecimal(split[0].trim());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		for (int i = 1; i < split.length; i++) {
			BigDecimal operand;
			try {
				operand = new BigDecimal(split[i].trim());
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
			result = result.add(operand);
		}

		return result;
	}

	public BigDecimal subtract(String numbers) {
		if (numbers == null || numbers.isEmpty())
			throw new IllegalArgumentException();

		String[] split = numbers.split(",");
		BigDecimal result;
		try {
			result = new BigDecimal(split[0].trim());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		for (int i = 1; i < split.length; i++) {
			BigDecimal operand;
			try {
				operand = new BigDecimal(split[i].trim());
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
			result = result.subtract(operand);
		}

		return result;
	}

	public BigDecimal subtractR(String numbers) {
		if (numbers == null || numbers.isEmpty())
			throw new IllegalArgumentException();

		String[] split = numbers.split(",");
		BigDecimal result;
		try {
			result = new BigDecimal(split[split.length - 1].trim());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		for (int i = split.length - 2; i > -1; i--) {
			BigDecimal operand;
			try {
				operand = new BigDecimal(split[i].trim());
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
			result = result.subtract(operand);
		}

		return result;
	}

	public BigDecimal multiply(String numbers) {
		if (numbers == null || numbers.isEmpty())
			throw new IllegalArgumentException();

		String[] split = numbers.split(",");
		BigDecimal result;
		try {
			result = new BigDecimal(split[0].trim());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		for (int i = 1; i < split.length; i++) {
			BigDecimal operand;
			try {
				operand = new BigDecimal(split[i].trim());
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
			result = result.multiply(operand);
		}

		return result;
	}

	public BigDecimal divide(String numbers) {
		if (numbers == null || numbers.isEmpty())
			throw new IllegalArgumentException();

		String[] split = numbers.split(",");
		BigDecimal result;
		try {
			result = new BigDecimal(split[0].trim());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		for (int i = 1; i < split.length; i++) {
			BigDecimal operand;
			try {
				operand = new BigDecimal(split[i].trim());
				if (operand.compareTo(BigDecimal.ZERO) == 0) {
					operand = BigDecimal.ONE;
				}
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
			result = result.divide(operand);
		}

		return result;
	}

	public BigDecimal divideR(String numbers) {
		if (numbers == null || numbers.isEmpty())
			throw new IllegalArgumentException();

		String[] split = numbers.split(",");
		BigDecimal result;
		try {
			result = new BigDecimal(split[split.length - 1].trim());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		for (int i = split.length - 2; i > -1; i--) {
			BigDecimal operand;
			try {
				operand = new BigDecimal(split[i].trim());
				if (operand.compareTo(BigDecimal.ZERO) == 0) {
					operand = BigDecimal.ONE;
				}
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
			result = result.divide(operand);
		}

		return result;
	}

	public String[] getNumbers(String numbers) {
		return Arrays.stream(numbers.split(" ")).map(String::trim).toArray(String[]::new);
	}

	public void printNumbers(String operator, String numbers) {
		List<String> numArray = new ArrayList<>(Arrays.asList(getNumbers(numbers)));

		String formated = addBrackets(operator, numArray);
		System.out.println(formated);
	}

	private String addBrackets(String operator, List<String> numArray) {
		if (numArray.isEmpty()) {
			return "";
		} else if (numArray.size() == 1) {
			return numArray.get(0);
		} else {
			String last = numArray.get(numArray.size() - 1);
			numArray.remove(numArray.size() - 1);
			return "(" + addBrackets(operator, numArray) + " " + operator + " " + last + ")";
		}
	}

}
