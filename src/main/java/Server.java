import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void start() throws IOException {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080),
                0
        );

        server.createContext("/", exchange -> {

            if (exchange.getRequestMethod().equals("GET")) {
                serveFile(
                        exchange,
                        "index.html",
                        "text/html; charset=UTF-8"
                );
            } else {

                String response = "Método HTTP no permitido.";

                byte[] responseBytes = response.getBytes(
                        StandardCharsets.UTF_8
                );

                exchange.getResponseHeaders().set(
                        "Content-Type",
                        "text/plain; charset=UTF-8"
                );

                exchange.sendResponseHeaders(
                        405,
                        responseBytes.length
                );

                try (OutputStream output = exchange.getResponseBody()) {
                    output.write(responseBytes);
                }
            }
        });

        server.createContext("/style.css", exchange -> {

            if (exchange.getRequestMethod().equals("GET")) {
                serveFile(
                        exchange,
                        "style.css",
                        "text/css; charset=UTF-8"
                );
            }
        });

        server.createContext("/script.js", exchange -> {

            if (exchange.getRequestMethod().equals("GET")) {
                serveFile(
                        exchange,
                        "script.js",
                        "application/javascript; charset=UTF-8"
                );
            }
        });

        server.createContext("/personas", exchange -> {

            try {

                String method = exchange.getRequestMethod();

                // GET /personas
                if (method.equals("GET")) {

                    List<Persona> personas = Database.getPersonas();

                    String response = mapper.writeValueAsString(personas);

                    byte[] responseBytes = response.getBytes(
                            StandardCharsets.UTF_8
                    );

                    exchange.getResponseHeaders().set(
                            "Content-Type",
                            "application/json; charset=UTF-8"
                    );

                    exchange.sendResponseHeaders(
                            200,
                            responseBytes.length
                    );

                    try (OutputStream output = exchange.getResponseBody()) {
                        output.write(responseBytes);
                    }

                // POST /personas
                } else if (method.equals("POST")) {

                    Persona persona = mapper.readValue(
                            exchange.getRequestBody(),
                            Persona.class
                    );

                    Database.insertPersona(persona);

                    String response = mapper.writeValueAsString(persona);

                    byte[] responseBytes = response.getBytes(
                            StandardCharsets.UTF_8
                    );

                    exchange.getResponseHeaders().set(
                            "Content-Type",
                            "application/json; charset=UTF-8"
                    );

                    exchange.sendResponseHeaders(
                            201,
                            responseBytes.length
                    );

                    try (OutputStream output = exchange.getResponseBody()) {
                        output.write(responseBytes);
                    }
                }
                // PUT /personas/{id}
                else if (method.equals("PUT")) {

                    String path = exchange.getRequestURI().getPath();

                    String[] parts = path.split("/");

                    int id = Integer.parseInt(parts[2]);

                    Persona persona = mapper.readValue(
                            exchange.getRequestBody(),
                            Persona.class
                    );

                    persona.setId(id);

                    int updatedRows = Database.updatePersona(persona);

                    if (updatedRows == 0) {

                        String response = "Persona no encontrada.";

                        byte[] responseBytes = response.getBytes(
                                StandardCharsets.UTF_8
                        );

                        exchange.getResponseHeaders().set(
                                "Content-Type",
                                "text/plain; charset=UTF-8"
                        );

                        exchange.sendResponseHeaders(
                                404,
                                responseBytes.length
                        );

                        try (OutputStream output = exchange.getResponseBody()) {
                            output.write(responseBytes);
                        }

                        return;
                    }

                    String response = mapper.writeValueAsString(persona);

                    byte[] responseBytes = response.getBytes(
                            StandardCharsets.UTF_8
                    );

                    exchange.getResponseHeaders().set(
                            "Content-Type",
                            "application/json; charset=UTF-8"
                    );

                    exchange.sendResponseHeaders(
                            200,
                            responseBytes.length
                    );

                    try (OutputStream output = exchange.getResponseBody()) {
                        output.write(responseBytes);
                    }
                }
                // DELETE /personas/{id}
                else if (method.equals("DELETE")) {

                    String path = exchange.getRequestURI().getPath();

                    String[] parts = path.split("/");

                    int id = Integer.parseInt(parts[2]);

                    int deletedRows = Database.deletePersona(id);

                    if (deletedRows == 0) {

                        String response = "Persona no encontrada.";

                        byte[] responseBytes = response.getBytes(
                                StandardCharsets.UTF_8
                        );

                        exchange.getResponseHeaders().set(
                                "Content-Type",
                                "text/plain; charset=UTF-8"
                        );

                        exchange.sendResponseHeaders(
                                404,
                                responseBytes.length
                        );

                        try (OutputStream output = exchange.getResponseBody()) {
                            output.write(responseBytes);
                        }

                        return;
                    }

                    String response = "Persona eliminada correctamente.";

                    byte[] responseBytes = response.getBytes(
                            StandardCharsets.UTF_8
                    );

                    exchange.getResponseHeaders().set(
                            "Content-Type",
                            "text/plain; charset=UTF-8"
                    );

                    exchange.sendResponseHeaders(
                            200,
                            responseBytes.length
                    );

                    try (OutputStream output = exchange.getResponseBody()) {
                        output.write(responseBytes);
                    }
                }else {

                    String response = "Método HTTP no permitido.";

                    byte[] responseBytes = response.getBytes(
                            StandardCharsets.UTF_8
                    );

                    exchange.getResponseHeaders().set(
                            "Content-Type",
                            "text/plain; charset=UTF-8"
                    );

                    exchange.sendResponseHeaders(
                            405,
                            responseBytes.length
                    );

                    try (OutputStream output = exchange.getResponseBody()) {
                        output.write(responseBytes);
                    }
                }

            } catch (Exception e) {

                e.printStackTrace();

                String response = "Error en el servidor.";

                byte[] responseBytes = response.getBytes(
                        StandardCharsets.UTF_8
                );

                exchange.getResponseHeaders().set(
                        "Content-Type",
                        "text/plain; charset=UTF-8"
                );

                exchange.sendResponseHeaders(
                        500,
                        responseBytes.length
                );

                try (OutputStream output = exchange.getResponseBody()) {
                    output.write(responseBytes);
                }
            }
        });

        server.start();

        System.out.println(
                "Servidor iniciado en http://localhost:8080"
        );
    }

    private static void serveFile(
            com.sun.net.httpserver.HttpExchange exchange,
            String fileName,
            String contentType
    ) throws IOException {

        Path path = Paths.get("web", fileName);

        if (!Files.exists(path)) {

            String response = "Archivo no encontrado.";

            byte[] responseBytes = response.getBytes(
                    StandardCharsets.UTF_8
            );

            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/plain; charset=UTF-8"
            );

            exchange.sendResponseHeaders(
                    404,
                    responseBytes.length
            );

            try (OutputStream output = exchange.getResponseBody()) {
                output.write(responseBytes);
            }

            return;
        }

        byte[] fileBytes = Files.readAllBytes(path);

        exchange.getResponseHeaders().set(
                "Content-Type",
                contentType
        );

        exchange.sendResponseHeaders(
                200,
                fileBytes.length
        );

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(fileBytes);
        }
    }
}
