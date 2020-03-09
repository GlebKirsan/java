public class Main {
    public static boolean isPalindrome(String text) {
        String regex = "[^a-zA-Z0-9]";
        String clearText = text.replaceAll(regex, "");
        StringBuilder builder = new StringBuilder(clearText);
        String reversed = builder.reverse().toString();
        return clearText.equalsIgnoreCase(reversed);
    }

    public static boolean isPalindromeStream(String text) {
        StringBuilder leftToRight = new StringBuilder();
        text.chars()
                .filter(Character::isLetterOrDigit)
                .map(Character::toLowerCase)
                .forEach(leftToRight::appendCodePoint);

        StringBuilder rightToLeft = new StringBuilder(leftToRight).reverse();
        return rightToLeft.toString().equals(leftToRight.toString());
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Madam, I'm Adam!"));
    }
}
