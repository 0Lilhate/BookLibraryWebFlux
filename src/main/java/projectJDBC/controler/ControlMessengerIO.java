package projectJDBC.controler;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


@Controller
public class ControlMessengerIO implements ControlMessenger{
    private final PrintStream out;
    private final BufferedReader reader;

    public ControlMessengerIO() {
        this.out = System.out;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public String readLine() {
        String read;
        try {
            read = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return read;
    }


    @Override
    public void greetingString(String str) {
        out.print(str);
    }
}
