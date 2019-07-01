import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {

    private String hamletData;
    private final Pattern HAMLET_Pattern = Pattern.compile("\\bHamlet\\b");
    private final Pattern HORATIO_Pattern = Pattern.compile("\\bHoratio\\b");
    private final String HAMLET_REPLACEMENT = "Leon";
    private final String HORATIO_REPLACEMENT = "Tariq";


    public HamletParser(){
        this.hamletData = loadFile();
    }

    private String loadFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("hamlet.txt").getFile());
        StringBuilder result = new StringBuilder("");

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }
    private void writeOutFile(String finleName, String output) {
        try(PrintWriter printWriter = new PrintWriter(finleName)) {
            printWriter.println(output);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public String getHamletData(){
        return hamletData;
    }
    public String replaceBoth(String replaceIn) {
        return replaceHamlet(replaceHoratio(replaceIn));
    }

    public int findHamlet(String findIn) {
        return matchPattern(HAMLET_Pattern, findIn);
    }

    public int findHoratio(String findIn) {
        return matchPattern(HORATIO_Pattern, findIn);
    }

    private int matchPattern(Pattern regex, String findIn) {
        Matcher redexMatcher = regex.matcher(findIn);
        return getCountOfMatches(redexMatcher);
    }

    private int getCountOfMatches(Matcher regexMatcher) {
        int count = 0;
        while(regexMatcher.find()) {
            count++;
        }
        return  count;
    }

    public String replaceHamlet(String replaceIn) {
        return replacePattern(HAMLET_Pattern, HAMLET_REPLACEMENT, replaceIn);
    }
    public String replaceHoratio(String replaceIn) {
        return replacePattern(HORATIO_Pattern, HORATIO_REPLACEMENT, replaceIn);
    }

    private String replacePattern(Pattern regex, String replacement, String replaceIn) {
        Matcher regexMatcher = regex.matcher(replaceIn);
        return regexMatcher.replaceAll(replacement);
    }

    public static void main(String[] args) {
        HamletParser hamletParser = new HamletParser();
        hamletParser.writeOutFile("replaced.txt", hamletParser.replaceBoth(hamletParser.getHamletData()));
    }

}
