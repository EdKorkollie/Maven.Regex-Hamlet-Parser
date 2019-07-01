import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HamletParserTest {
    private String hamletText;
    private HamletParser hamletParser;

    @Before
    public void setUp() {
        this.hamletParser = new HamletParser();
        this.hamletText = hamletParser.getHamletData();
    }


    @Test
    public void testChangeHamletToLeon() {
        //Given
        String expected = "Hey Leon Leon Leon";
        String input = "Hey Hamlet Hamlet Leon";
        //When
        String actual = hamletParser.replaceHamlet(input);
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testChangeHamletToLeon2(){
        //Given
        String expected = "Hey Leon";
        String input = "Hey Hamlet";
        //When
        String actual = hamletParser.replaceHamlet(input);
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testChangeHamletToLeonNone(){
        //Given
        String expected = "Hey HamletHamlet Leon";
        String input = "Hey HamletHamlet Leon";
        //When
        String actual = hamletParser.replaceHamlet(input);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeHoratioToTariq() {
        //Given
        String expected = "Hey Tariq";
        String input = "Hey Horatio";
        //When
        String actual = hamletParser.replaceHoratio(input);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHoratio() {
        //Given
        int expected = 1;
        String input = "Hey Horatio";
        //When
        int actual = hamletParser.findHoratio(input);
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testFindHoratioMultiple() {
        //Given
        int expected = 4;
        String input = "Horatio! How are you doing Horatio. my friend \n" + "Horatio-things: Horatio and Horatin";
        //When
        int actual = hamletParser.findHoratio(input);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindHamlet() {
        //Given

        //When

        //Then

    }
    @Test
   public void testFindHamletFalse(){
        //Given
        int expected = 0;
        String input = "Hey Hammmlet";
        //When
        int actual = hamletParser.findHamlet(input);
        //Then
    }
    @Test
    public void testFindHamletinText(){
        //Given
        int expected = 84;
        String input = hamletText;
        //When
        int actual = hamletParser.findHamlet(input);
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testReplaceHamletInText(){
        //Given
        int expected = 0;
        //When
        String noHamlet = hamletParser.replaceHamlet(hamletText);
        int actual = hamletParser.findHamlet(noHamlet);
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testFindHoratioInText(){
        //Given
        int expected = 31;
        String input = hamletText;
        //When
        int actual = hamletParser.findHoratio(input);
        //Then
    }
    @Test
    public void testReplaceHoratioInText(){
        //Given
        int expected = 0;
        //When
        String noHoratio = hamletParser.replaceHoratio(hamletText);
        int actual = hamletParser.findHoratio(noHoratio);
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testReplaceBothSample(){
        //Given
        String expected = "Hey Leon, Hey Tariq";
        String input = "Hey Hamlet, Hey Horatio";
        //When
        String actual = hamletParser.replaceBoth(input);
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testReplaceBothInText(){
        //Given
        int expected = 0;
        //When
        String replace = hamletParser.replaceBoth(hamletText);
        int actual = hamletParser.findHoratio(replace) + hamletParser.findHamlet(replace);
        //Then
        Assert.assertEquals(expected, actual);
    }
}