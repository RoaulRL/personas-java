public class Main {

    public static void main(String[] args) {

        try {
            Database.createTable();

            Server.start();

        } catch (Exception e) {
            System.out.println("Error al iniciar la aplicación.");
            e.printStackTrace();
        }
    }
}
