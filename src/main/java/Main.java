
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Starting server...");
            Server server = new Server();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
