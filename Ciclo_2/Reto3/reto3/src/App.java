public class App {
    public static void main(String[] args) throws Exception {
        SchoolGradingSystem nota1=new SchoolGradingSystem();
        nota1.loadData();
        System.out.println(nota1.stat1());
        System.out.println(nota1.stat2());
        System.out.println(nota1.stat3());
        System.out.println(nota1.stat4());
    }
}
