package proj.ecmoji.util;

import java.io.File;
import java.util.Scanner;

public class TXTtokenLoader {
    public static String loadToken(String path) throws Exception {
        try(Scanner scanner = new Scanner(new File(path))) {
            return scanner.next();
        } catch(Exception e) {throw(e);}
    }
}