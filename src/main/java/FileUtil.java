import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具
 *
 * @author chocoh
 */
public class FileUtil {
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("无法加载文件");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败");
        }
        return lines;
    }

    public static void writeFile(String filePath, List<String> lines) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("文件写入失败");
        }
    }
}
