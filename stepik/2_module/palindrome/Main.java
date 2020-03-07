public class Main {
    public static boolean isPalindrome(String text){
        String regex = "[^a-zA-Z0-9]";
        String clearText = text.replaceAll(regex, "");
        StringBuilder builder = new StringBuilder(clearText);
        String reversed = builder.reverse().toString();
        return clearText.equalsIgnoreCase(reversed);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Madam, I'm Adam!"));
    }
}
