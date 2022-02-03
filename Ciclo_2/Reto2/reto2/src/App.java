public class App {
    public static void main(String[] args) throws Exception {
       SchoolGradingSystem nuevo= new SchoolGradingSystem();
       nuevo.readData();
       System.out.println(nuevo.question1());
       System.out.println(nuevo.question2());
       System.out.println(nuevo.question3());
       System.out.println(nuevo.question4());
    }
}
