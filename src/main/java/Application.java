import java.util.*;

/**
 * 程序
 *
 * @author chocoh
 */
public class Application {
    /**
     * 启动程序
     */
    public static void run(String[] args) {
        Map<String, String> params = parseParams(args);
        try {
            if (params.get("-r") != null && params.get("-n") != null) {
                generateProblems(params.get("-n"), params.get("-r"));
            } else if (params.get("-e") != null && params.get("-a") != null) {
                judgeProblems(params.get("-e"), params.get("-a"));
            } else {
                throw new RuntimeException("请检查参数 [-r][-n] | [-e][-a]");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 解析参数
     */
    public static Map<String, String> parseParams(String[] args) {
        if (args.length % 2 == 1) {
            throw new RuntimeException("请检查参数");
        }
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            params.put(args[i], args[i + 1]);
        }
        return params;
    }

    /**
     * 生成问题
     */
    public static void generateProblems(String n, String r) {
        int num = Integer.parseInt(n);
        int range = Integer.parseInt(r);
        if (num < 0 || range < 0) {
            throw new RuntimeException("参数必须为自然数");
        }
        num = Math.min(10000, num);
        List<Problem> problems = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 生成题目
            problems.add(ProblemGenerator.generateProblem(range));
        }
        // 输出结果
        List<String> exercises = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            exercises.add(i + ". " + problems.get(i).getExercises());
            answers.add(i + ". " + problems.get(i).getAnswers());
        }
        FileUtil.writeFile("Exercises.txt", exercises);
        FileUtil.writeFile("Answers.txt", answers);
    }

    /**
     * 判定答案
     */
    public static void judgeProblems(String e, String a) {
        List<String> exerciseFile = FileUtil.readFile(e);
        List<String> answerFile = FileUtil.readFile(a);
        int correct = 0;
        int wrong = 0;
        List<String> correctIndex = new ArrayList<>();
        List<String> gradeIndex = new ArrayList<>();
        for (int i = 0; i < exerciseFile.size(); i++) {
            try {
                String[] exercise = exerciseFile.get(i).split("\\. ");
                String[] answer = answerFile.get(i).split("\\. ");
                // 判定答案
                if (ProblemGenerator.judgeProblem(exercise[1], answer[1])) {
                    correctIndex.add(exercise[0]);
                    correct++;
                } else {
                    gradeIndex.add(exercise[0]);
                    wrong++;
                }
            } catch (Exception ex) {
                throw new RuntimeException("无法解析表达式");
            }
        }
        // 输出统计结果
        List<String> grade = new ArrayList<>();
        grade.add("Correct:" + correct + " (" + String.join(", ", correctIndex) + ")");
        grade.add("Wrong:" + wrong + " (" + String.join(", ", gradeIndex) + ")");
        FileUtil.writeFile("Grade.txt", grade);
    }
}
