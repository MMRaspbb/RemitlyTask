public class Main {
    public static void main(String[] args){
        JsonVerifier jsonVerifier = new JsonVerifier();
        String path = "C:/studia/internships/RemitlyTask/RemitlyTask/src/main/resources/example.json";
        System.out.println(jsonVerifier.isLegit(path));
    }
}
