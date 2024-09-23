/**
 * 题目
 *
 * @author chocoh
 */
public class Problem {
    private String exercises;
    private String answers;

    public static Problem generateProblem(int r) {
        return new Problem();
    }

    public static boolean checkDuplicate(Problem p1, Problem p2) {
        return false;
    }

    public static boolean judge(String exercises, String answers) {
        return false;
    }

    public Problem() {
    }

    public Problem(String exercises, String answers) {
        this.exercises = exercises;
        this.answers = answers;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
