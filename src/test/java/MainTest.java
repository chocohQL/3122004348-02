import org.junit.Test;

/**
 * @author chocoh
 */
public class MainTest {
    @Test
    public void test1() {
        Main.main(new String[]{});
    }

    @Test
    public void test2() {
        Main.main(new String[]{"-n", "10", "-r", "10"});
    }

    @Test
    public void test3() {
        Main.main(new String[]{"-n", "-1", "-r", "10"});
    }

    @Test
    public void test4() {
        Main.main(new String[]{"-n", "10005", "-r", "10"});
    }

    @Test
    public void test5() {
        Main.main(new String[]{"-e", "e.txt", "-a", "a.txt"});
    }

    @Test
    public void test6() {
        Main.main(new String[]{"-e", "e", "-a", "a"});
    }

    @Test
    public void test7() {
        Main.main(new String[]{"-e", "Exercises.txt", "-a", "Answers.txt"});
    }

    @Test
    public void test8() {
        Main.main(new String[]{"-e", "Exercises.txt", "-a", "ErrorAnswers.txt"});
    }

    @Test
    public void test9() {
        Main.main(new String[]{"-e", "Exercises.txt", "-b", "ErrorAnswers.txt"});
    }

    @Test
    public void test10() {
        Main.main(new String[]{"-n", "10", "-f", "10"});
    }
}
