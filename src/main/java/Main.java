import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));
        if (arguments.size() != 3) {
            System.out.printf("Incorrect number of arguments: %s" + ". Expected number of arguments: 3", arguments.size());
            return;
        }
        System.out.printf("Operation: %s" + ", Input JSON: %s" +", Output JSON: %s", arguments.get(0), arguments.get(1), arguments.get(2));
    }
}