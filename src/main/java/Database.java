import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:sqlite:database/personas.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void createTable() throws SQLException {

        String sql = """
                CREATE TABLE IF NOT EXISTS personas (
                    id INTEGER PRIMARY KEY,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    edad INTEGER NOT NULL
                );
                """;

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        }
    }

    public static void insertPersona(Persona persona) throws SQLException {

        String sql = """
                INSERT INTO personas (nombre, apellido, edad)
                VALUES (?, ?, ?);
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setInt(3, persona.getEdad());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {

                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    persona.setId(id);
                }
            }
        }
    }

    public static List<Persona> getPersonas() throws SQLException {

        String sql = "SELECT * FROM personas";

        List<Persona> personas = new ArrayList<>();

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                int edad = resultSet.getInt("edad");

                Persona persona = new Persona(
                        id,
                        nombre,
                        apellido,
                        edad
                );

                personas.add(persona);
            }
        }

        return personas;
    }

    public static int updatePersona(Persona persona) throws SQLException {

        String sql = """
                UPDATE personas
                SET nombre = ?,
                    apellido = ?,
                    edad = ?
                WHERE id = ?;
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setInt(3, persona.getEdad());
            statement.setInt(4, persona.getId());

            return statement.executeUpdate();
        }
    }

    public static int deletePersona(int id) throws SQLException {

        String sql = """
                DELETE FROM personas
                WHERE id = ?;
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate();
        }
    }
}
