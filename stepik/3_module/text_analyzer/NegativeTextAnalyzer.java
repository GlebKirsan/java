public class NegativeTextAnalyzer
        extends KeywordAnalyzer
        implements TextAnalyzer {

    private final String[] keywords = {":(", "=(", ":|"};
    private final Label label = Label.NEGATIVE_TEXT;

    public NegativeTextAnalyzer() {
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
