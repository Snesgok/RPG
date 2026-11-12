import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ты готов начать игру? (напиши: ДА или НЕТ)");
        String answer = scanner.nextLine();

        switch (answer.toLowerCase()) {
            case "да":
                System.out.println("Начинаем игру!");
                break;
            case "нет":
                System.out.println("Жаль, приходи ещё!");
                break;
            default:
                System.out.println("Непонятный ответ, но мы все равно начинаем!");
        }

        System.out.println("Выберите уровень сложности (введите число от 1 и выше)");
        int difficulty = scanner.nextInt();
        System.out.println("Выбран уровень сложности " + difficulty);

        int size = 5;
        String[][] board = new String[size][size];
        int step = 1;

        Person person = new Person(size);
        Random r = new Random();
        int castleX = r.nextInt(size) + 1;
        int castleY = 1;
        String castleImage = "\uD83C\uDFF0";

        int monsterCount = (size * size) / 5;
        Monster[] monsters = new Monster[monsterCount];
        for (int i = 0; i < monsterCount; i++) {
            int type = r.nextInt(3);
            if (type == 0) monsters[i] = new Monster(size);
            else if (type == 1) monsters[i] = new BigMonster(size);
            else monsters[i] = new SmallMonster(size);
        }

        boolean game = true;

        while(game) {
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    board[y][x] = "  ";
                }
            }

            for (Monster m : monsters) {
                if (m != null && m.getX() > 0 && m.getY() > 0) {
                    board[m.getY() - 1][m.getX() - 1] = m.getImage();
                }
            }

            board[castleY - 1][castleX - 1] = castleImage;
            board[person.getY() - 1][person.getX()] = person.getImage();

            System.out.println("\nХод: " + step + " | Жизни: " + person.getLive());
            String wall = "+----".repeat(size) + "+";
            for (int i = 0; i < size; i ++) {
                System.out.println(wall);
                for (int j = 0; j < size; j ++) {
                    System.out.print("| " + board[i][j] + "  ");
                }
                System.out.println("|");
            }
            System.out.println(wall);

            if (person.getLive() <= 0) {
                System.out.println("У тебя закончились жизни. Ты проиграл");
                game = false;
            }

            System.out.println("Текущие координаты: X =" + person.getY() + ", Y =" + person.getX());
            System.out.println("Введите новые координаты героя (x и y через пробел):");

            int nextX = scanner.nextInt();
            int nextY = scanner.nextInt();

            if (nextX < 1 || nextX > size || nextY < 1 || nextY > size ) {
                System.out.println("Выход за границы поля!");
                continue;
            }

            if (person.isMoveCorrect(nextX, nextY)) {
                String nextCell = board[nextY - 1][nextX - 1];

                if (nextCell.equals(castleImage)) {
                    System.out.println("Ты добрался до замка");
                    break;
                } else if (!nextCell.equals("  ")) {
                    for (int i = 0; i < monsters.length; i++) {
                        if (monsters[i] != null && monsters[i].getX() == nextX && monsters[i].getY() == nextY ) {
                            boolean success = monsters[i].taskMonster(difficulty);
                            if (success) {
                                monsters[i] = null;
                                person.move(nextX, nextY);
                            } else {
                                person.downLive();
                            }
                            break;
                        }
                    }
                } else {
                    person.move(nextX, nextY);
                }
                step++;
            } else {
                System.out.println("Некорретный ход! Можно ходить только на 1 клетку по вертикали или горизонтали");
            }
        }
    }
}