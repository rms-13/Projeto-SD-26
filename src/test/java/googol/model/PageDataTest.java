package googol.model;

import java.util.HashSet;
import java.util.Set;

public class PageDataTest {

    public static void main(String[] args) {
        Set<String> words = new HashSet<>();
        words.add("java");
        words.add("rmi");

        PageData page = new PageData(

                // Example data for the PageData object
                "https://exemplo.pt",
                "Página de teste",
                "Uma página sobre Java e RMI.",
                words,
                Set.of("https://exemplo.pt/contactos")
        );

        check(page.getUrl().equals("https://exemplo.pt"), "URL");
        check(page.getTitle().equals("Página de teste"), "Título");
        check(page.getSnippet().equals("Uma página sobre Java e RMI."), "Excerto");
        check(page.getWords().equals(Set.of("java", "rmi")), "Palavras");
        check(
                page.getOutgoingLinks().equals(
                        Set.of("https://exemplo.pt/contactos")),
                "Links"
        );

        words.add("nova");
        check(!page.getWords().contains("nova"), "Cópia independente");

        boolean modificationBlocked = false;
        try {
            page.getWords().add("outra");
        } catch (UnsupportedOperationException e) {
            modificationBlocked = true;
        }
        check(modificationBlocked, "Conjunto não modificável");

        System.out.println("Todos os testes passaram.");
    }

    private static void check(boolean condition, String name) {
        if (!condition) {
            throw new AssertionError("Falhou: " + name);
        }
        System.out.println("OK: " + name);
    }
}