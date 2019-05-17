package proj.ecmoji;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import proj.ecmoji.util.EmojiPrinter;
import proj.ecmoji.web.JDParser;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        try {
            EmojiPrinter.main(null);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            assertTrue( false );
        } finally {
            assertTrue( true );
        } 
    }
}
