public class SpamAnalyzer
        extends KeywordAnalyzer
        implements TextAnalyzer {

    private final String[] keywords;
    private final Label label = Label.SPAM;

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    protected Label getLabel() {
        return label;
    }

    @Override
    protected String[] getKeywords() {
        return keywords;
    }
}
