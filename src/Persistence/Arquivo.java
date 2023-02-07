package Persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Arquivo {
    private final Path file;

    public Arquivo(Path file) {
        this.file = file;
    }
    public static Stream<String> lerArquivo(Path file) throws IOException {
        return Files.lines(file);
    }
}
