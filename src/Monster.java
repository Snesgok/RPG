import java.util.Random;
import java.util.Scanner;

public class Monster {
    protected int x,y;
    protected String image = "\uD83E\uDDDF";

    public Monster(int size){
        Random r = new Random();
        this.x = r.nextInt(size) + 1;
        this.y = r.nextInt(size) + 1;
    }

    public boolean taskMonster(int difficulty) {
        Random r = new Random();
        int a = r.nextInt(100 * difficulty);
        int b = r.nextInt(100 * difficulty);
        int trueAnswer = a + b;

        System.out.println("Монстр задает вопрос: " + a + " + " + b + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();

        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра!");
            return true;
        } else {
            System.out.println("Ты проиграл эту битву!");
            return false;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getImage() {return image;}


    }

