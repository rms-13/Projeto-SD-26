package googol.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Dados imutáveis de uma página, enviados pelo Downloader aos Barrels por RMI.
 * Guarda o URL, o título, um excerto, as palavras e as ligações de saída.
 * As palavras e os URLs devem ser normalizados antes da construção; esta classe
 * apenas transporta os dados, sem os extrair ou normalizar.
 * Os conjuntos são copiados e não podem ser modificados através dos getters.
 */
public final class PageData implements Serializable {

    // Serial version UID for serialization
    private static final long serialVersionUID = 1L;

    // Fields representing the page data
    private final String url;
    private final String title;
    private final String snippet;
    private final Set<String> words;
    private final Set<String> outgoingLinks;

    /**
     * Cria uma página com cópias não modificáveis dos conjuntos fornecidos.
     * Alterar os conjuntos originais posteriormente não altera esta página.
     * @param url URL normalizado da página, não nulo
     * @param title título extraído do HTML, não nulo
     * @param snippet excerto curto do texto da página, não nulo
     * @param words palavras normalizadas, num conjunto não nulo e sem elementos nulos
     * @param outgoingLinks URLs normalizados das ligações de saída, num conjunto
     *                      não nulo e sem elementos nulos
     * @throws NullPointerException se um argumento ou um elemento dos conjuntos for nulo
     */
    public PageData(
                    String url,
                    String title,
                    String snippet,
                    Set<String> words,
                    Set<String> outgoingLinks) {

        this.url = Objects.requireNonNull(url, "url");
        this.title = Objects.requireNonNull(title, "title");
        this.snippet = Objects.requireNonNull(snippet, "snippet");
        this.words = Set.copyOf(words);
        this.outgoingLinks = Set.copyOf(outgoingLinks);
    }

    // Getters for the fields
    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSnippet() {
        return snippet;
    }

    public Set<String> getWords() {
        return words;
    }

    public Set<String> getOutgoingLinks() {
        return outgoingLinks;
    }
}
