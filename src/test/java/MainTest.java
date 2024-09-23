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
        Main.main(new String[]{"-e", "exerciseFile.txt", "-a", "answerFile.txt"});
    }
}
