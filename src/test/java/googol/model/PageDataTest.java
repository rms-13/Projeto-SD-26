package googol.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PageDataTest {

    private PageData createPage() {
        return new PageData(
                "https://exemplo.pt",
                "Página de teste",
                "Uma página sobre Java e RMI.",
                Set.of("java", "rmi"),
                Set.of("https://exemplo.pt/contactos")
        );
    }

    @Test
    void guardaOsDadosFornecidos() {
        PageData page = createPage();

        assertAll(
                () -> assertEquals("https://exemplo.pt", page.getUrl()),
                () -> assertEquals("Página de teste", page.getTitle()),
                () -> assertEquals(
                        "Uma página sobre Java e RMI.",
                        page.getSnippet()
                ),
                () -> assertEquals(
                        Set.of("java", "rmi"),
                        page.getWords()
                ),
                () -> assertEquals(
                        Set.of("https://exemplo.pt/contactos"),
                        page.getOutgoingLinks()
                )
        );
    }

    @Test
    void copiaOsConjuntosOriginais() {
        Set<String> words = new HashSet<>(Set.of("java"));
        Set<String> links = new HashSet<>(
                Set.of("https://exemplo.pt/contactos")
        );

        PageData page = new PageData(
                "https://exemplo.pt",
                "Título",
                "Excerto",
                words,
                links
        );

        words.clear();
        links.clear();

        assertAll(
                () -> assertEquals(Set.of("java"), page.getWords()),
                () -> assertEquals(
                        Set.of("https://exemplo.pt/contactos"),
                        page.getOutgoingLinks()
                )
        );
    }

    @Test
    void impedeAlteracoesPelosGetters() {
        PageData page = createPage();

        assertAll(
                () -> assertThrows(
                        UnsupportedOperationException.class,
                        () -> page.getWords().add("nova")
                ),
                () -> assertThrows(
                        UnsupportedOperationException.class,
                        () -> page.getOutgoingLinks().clear()
                )
        );
    }

    @Test
    void rejeitaArgumentosNulos() {
        assertAll(
                () -> assertThrows(
                        NullPointerException.class,
                        () -> new PageData(
                                null, "Título", "Excerto", Set.of(), Set.of()
                        )
                ),
                () -> assertThrows(
                        NullPointerException.class,
                        () -> new PageData(
                                "https://exemplo.pt", null, "Excerto",
                                Set.of(), Set.of()
                        )
                ),
                () -> assertThrows(
                        NullPointerException.class,
                        () -> new PageData(
                                "https://exemplo.pt", "Título", null,
                                Set.of(), Set.of()
                        )
                ),
                () -> assertThrows(
                        NullPointerException.class,
                        () -> new PageData(
                                "https://exemplo.pt", "Título", "Excerto",
                                null, Set.of()
                        )
                ),
                () -> assertThrows(
                        NullPointerException.class,
                        () -> new PageData(
                                "https://exemplo.pt", "Título", "Excerto",
                                Set.of(), null
                        )
                )
        );
    }

    @Test
    void rejeitaElementosNulosNosConjuntos() {
        Set<String> withNull = new HashSet<>();
        withNull.add(null);

        assertAll(
                () -> assertThrows(
                        NullPointerException.class,
                        () -> new PageData(
                                "https://exemplo.pt", "Título", "Excerto",
                                withNull, Set.of()
                        )
                ),
                () -> assertThrows(
                        NullPointerException.class,
                        () -> new PageData(
                                "https://exemplo.pt", "Título", "Excerto",
                                Set.of(), withNull
                        )
                )
        );
    }

    @Test
    void preservaDadosAposSerializacao() throws Exception {
        PageData original = createPage();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            output.writeObject(original);
        }

        PageData copy;

        try (ObjectInputStream input = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray()))) {
            copy = (PageData) input.readObject();
        }

        assertNotSame(original, copy);

        assertAll(
                () -> assertEquals(original.getUrl(), copy.getUrl()),
                () -> assertEquals(original.getTitle(), copy.getTitle()),
                () -> assertEquals(original.getSnippet(), copy.getSnippet()),
                () -> assertEquals(original.getWords(), copy.getWords()),
                () -> assertEquals(
                        original.getOutgoingLinks(),
                        copy.getOutgoingLinks()
                )
        );
    }
}