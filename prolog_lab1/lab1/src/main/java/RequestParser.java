import java.util.Scanner;

public class RequestParser {
    Scanner sc = new Scanner(System.in);
// формат  - Я хочу сыграть парный матч на позиции ?, я ?.
    //  Я хочу сыграть одиночный матч на позиции ?, я ?.
    // уровень 1 - новичок, 2 - любитель, 3- опытный игрок, 4 - ветеран, 5 - профи
    // при неудачном просьба написать в нужном формате

    /*
    если одиночный - то подбор героя + подрбор противника
    я хочу играть одиночный матч на позиции стрелка, я новичок
    если одиночный - поиск персонажей с нужной позицией + рангами -> выдает персонажа, если найдет, если нет сказать, что
    в данный момент нет
    если парный бой
    я хочу играть парный матч, на позиции стрелка, я новичок
     */


    public String[] read() {
        System.out.println("Введите запрос в формате: ");
        System.out.println("Я хочу сыграть одиночный/парный матч на позиции Танк/Стрелок/Поддержка/Маг/Боец/Убийца , я новичок/любитель/профи .");
        String input = sc.nextLine();
        String[] str = input.replaceAll(",", "").replaceAll("\\.", "").replaceAll("/", "").replaceAll("[ ]+", " ").toLowerCase().split(" ");
        if (str.length < 10 || !checkMatch(str[3]) || !checkPosition(str[7]) || !checkRank(str[9])) {
            System.out.println("Пожалуйста проверьте запрос и повторите ввод, данные некорректны. Выбирайте только 1 тип матча, позицию и уровень вашей игры, будьте внимательны!");
            read();
        }
        return str;
    }


    private boolean checkMatch(String input) {
        return input.equals("одиночный") || input.equals("парный");
    }

    private boolean checkRank(String input) {
        return input.equals("новичок") || input.equals("любитель") || input.equals("профи");
    }

    private boolean checkPosition(String input) {
        return input.equals("танк") || input.equals("стрелок") || input.equals("поддержка") || input.equals("маг") || input.equals("боец") || input.equals("убийца");
    }

    public Integer getMatch(String match) {
        if (match.equals("одиночный")) {
            return 1;
        } else {
            return 2;
        }
    }

    public String getPosition(String position) {
        switch (position) {
            case "танк": {
                return "Tank";
            }
            case "стрелок": {
                return "Marksman";

            }
            case "Поддержка": {
                return "Support";

            }
            case "маг": {
                return "Mage";

            }
            case "убийца": {
                return "Assassin";

            }
            default: {
                return "Fighter";
            }

        }
    }

    public Integer getRank(String rank) {
        if (rank.equals("новичок")) {
            return 1;
        }
        if (rank.equals("любитель")) {
            return 2;
        } else return 3;
    }
}
