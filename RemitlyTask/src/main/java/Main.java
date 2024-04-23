import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class Main {

    public static void main(String[] args){
        JsonVerifier jsonVerifier = new JsonVerifier();
        System.out.println(jsonVerifier.isLegit(args[0]));
    }
}
