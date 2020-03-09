import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static class StolenPackageException extends RuntimeException {

    }

    public static class IllegalPackageException extends RuntimeException {

    }

    /*
    Интерфейс: сущность, которую можно отправить по почте.
    У такой сущности можно получить от кого и кому направляется письмо.
    */
    public static interface Sendable {
        String getFrom();

        String getTo();
    }

    /*
    Абстрактный класс,который позволяет абстрагировать логику хранения
    источника и получателя письма в соответствующих полях класса.
    */
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }

    }

    /*
    Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
    */
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    /*
    Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
    */
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }

    /*
    Посылка, содержимое которой можно получить с помощью метода `getContent`
    */
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }

    }

    /*
    Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
    */
    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    /*
    Класс, в котором скрыта логика настоящей почты
    */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {

        private final MailService[] mailServices;
        private static final MailService realMailService = new RealMailService();

        public UntrustworthyMailWorker(final MailService[] mailServices) {
            this.mailServices = mailServices;
        }

        public MailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for (MailService mailService : mailServices) {
                mail = mailService.processMail(mail);
            }

            return realMailService.processMail(mail);
        }
    }

    public static class Spy implements MailService {

        private final Logger LOGGER;

        public Spy(final Logger LOGGER) {
            this.LOGGER = LOGGER;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                String from = mail.getFrom();
                String to = mail.getTo();
                boolean fromAustin = from.equals(AUSTIN_POWERS);
                boolean toAustin = to.equals(AUSTIN_POWERS);
                String direction = "from " + from + " to " + to;
                if (fromAustin || toAustin) {
                    String mailMessage = ((MailMessage) mail).getContent();
                    String message = "Detected target mail correspondence: " + direction + " \"" + mailMessage + "\"";
                    LOGGER.warning(message);
                } else {
                    LOGGER.info("Usual correspondence: " + direction);
                }
            }

            return mail;
        }
    }

    public static class Thief implements MailService {

        private final int minPrice;
        private int stolenValue = 0;

        public Thief(int minPrice) {
            this.minPrice = minPrice;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                Package content = ((MailPackage) mail).getContent();
                int packagePrice = content.getPrice();
                if (packagePrice >= minPrice) {
                    stolenValue += packagePrice;
                    String packageContent = content.getContent();
                    Package newPackage = new Package("stones instead of " + packageContent, 0);
                    mail = new MailPackage(mail.getFrom(), mail.getTo(), newPackage);
                }
            }

            return mail;
        }
    }

    public static class Inspector implements MailService {
        private static final String[] ILLEGAL_CONTENT = {WEAPONS, BANNED_SUBSTANCE};

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                String packageContent = ((MailPackage) mail).getContent().getContent();

                boolean packageWithStones = packageContent.contains("stones");
                if (packageWithStones) {
                    throw new StolenPackageException();
                } else {
                    for (String illegalString : ILLEGAL_CONTENT) {
                        if (packageContent.contains(illegalString)) {
                            throw new IllegalStateException();
                        }
                    }
                }
            }

            return mail;
        }
    }
}