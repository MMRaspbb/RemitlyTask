public class Main {
    public static void main(String[] args){
        JsonVerifier jsonVerifier = new JsonVerifier();
        System.out.println(jsonVerifier.isLegit(args[0]));
    }
}
