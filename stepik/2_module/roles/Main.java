public class Main {
    private static String printTextPerRole(String[] roles, String[] textLines) {
        StringBuilder textPerRole = new StringBuilder();

        for (String role : roles) {
            String start = role + ":";
            textPerRole.append(start);
            
            for (int i = 0; i < textLines.length; ++i) {
                String line = textLines[i];
                if (!line.startsWith(start)) {
                    continue;
                }
                
                int startIndex = start.length() + 1;
                String words = line.substring(startIndex);
                textPerRole.append("\n" + (i + 1) + ") " + words);
            }

            textPerRole.append("\n\n");
        }

        return textPerRole.toString();
    }

    public static void main(String[] args) {
        String[] roles= {
            "Городничий","Аммос Федорович",
            "Артемий Филиппович",
            "Лука Лукич"};
        String[] textLines={
            "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
            "Аммос Федорович: Как ревизор?",
            "Артемий Филиппович: Как ревизор?",
            "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
            "Аммос Федорович: Вот те на!",
            "Артемий Филиппович: Вот не было заботы, так подай!",
            "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};

        System.out.println(printTextPerRole(roles, textLines));
    }
}
