package googol.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

// Represents the data of a page, including its title and content.
public final class PageData implements Serializable {

    // Serial version UID for serialization
    private static final long serialVersionUID = 1L;

    // Fields representing the page data
    private final String url;
    private final String title;
    private final String snippet;
    private final Set<String> words;
    private final Set<String> outgoingLinks;

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