import java.util.Objects;
import java.util.Random;
import java.util.Stack;

/**
 * @author chocoh
 */
public class ProblemGenerator {
    private static final char[] OPERATORS = {'+', '-', '×', '÷'};
    private static final Random RANDOM = new Random();

    /**
     * 生成题目
     */
    public static Problem generateProblem(int r) {
        String exercises = generateExercises(r);
        String answer = calculateAnswer(exercises);
        // 循环直到题目符合要求
        while (answer == null) {
            exercises = generateExercises(r);
            answer = calculateAnswer(exercises);
        }
        return new Problem(exercises, answer);
    }

    /**
     * 判定答案
     */
    public static boolean judgeProblem(String exercises, String answer) {
        return Objects.equals(calculateAnswer(exercises), answer);
    }

    /**
     * 生成表达式
     */
    public static String generateExercises(int r) {
        StringBuilder sb = new StringBuilder();
        sb.append(generateNumber(r));
        for (int i = 0; i < RANDOM.nextInt(3) + 1; i++) {
            sb.append(" ").append(generateOperator()).append(" ").append(generateNumber(r));
        }
        return sb.toString();
    }

    /**
     * 生成运算符
     */
    private static String generateOperator() {
        return String.valueOf(OPERATORS[new Random().nextInt(OPERATORS.length)]);
    }

    /**
     * 判断运算符
     */
    private static boolean isOperator(char c) {
        for (char operator : OPERATORS) {
            if (operator == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成数字
     */
    private static String generateNumber(int r) {
        return new Fraction(
                RANDOM.nextInt(r - 1) + 1,
                RANDOM.nextInt(r - 1) + 1
        ).toString();
    }

    /**
     * 计算答案
     */
    private static String calculateAnswer(String exercises) {
        Stack<Fraction> numStack = new Stack<>();
        Stack<Character> operator = new Stack<>();
        // 加入操作栈
        for (String str : exercises.split(" ")) {
            if (str.length() == 1 && isOperator(str.charAt(0))) {
                operator.push(str.charAt(0));
            } else {
                // 转为分数计算
                numStack.push(getFraction(str));
            }
        }
        while (!operator.isEmpty()) {
            char op = operator.pop();
            Fraction num2 = numStack.pop();
            Fraction num1 = numStack.pop();
            if (op == '+' || op == '-') {
                // 优先计算乘除
                if (!operator.isEmpty() && (operator.peek() == '×' || operator.peek() == '÷')) {
                    Fraction num3 = numStack.pop();
                    Character op1 = operator.pop();
                    if (op1 == '×') {
                        num1 = num1.multiply(num3);
                    } else if (op1 == '÷') {
                        if (num3.getNumerator() == 0) {
                            return null;
                        }
                        num1 = num1.divide(num3);
                    }
                }
                if (op == '+') {
                    numStack.push(num1.add(num2));
                } else {
                    // 如果存在形如e1− e2的子表达式，那么e1≥e2
                    if (num1.compareTo(num2) >= 0) {
                        numStack.push(num1.subtract(num2));
                    } else {
                        return null;
                    }
                }
            } else if (op == '×') {
                numStack.push(num1.multiply(num2));
            } else if (op == '÷') {
                if (num2.getNumerator() == 0) {
                    return null;
                }
                numStack.push(num1.divide(num2));
            }
        }
        return numStack.size() == 1 ? numStack.pop().toString() : null;
    }

    /**
     * 转换为分数
     */
    private static Fraction getFraction(String str) {
        if (str.contains("'")) {
            String[] parts = str.split("[/']");
            int intPart = Integer.parseInt(parts[0]);
            int numerator = Integer.parseInt(parts[1]);
            int denominator = Integer.parseInt(parts[2]);
            numerator += intPart * denominator;
            return new Fraction(numerator, denominator);
        }
        if (str.contains("/")) {
            String[] parts = str.split("/");
            return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
        return new Fraction(Integer.parseInt(str), 1);
    }
}
