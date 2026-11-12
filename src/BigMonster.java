import java.util.Random;
import java.util.Scanner;

public class BigMonster extends Monster {

    public BigMonster(int size){
        super(size);
        this.image = "\uD83D\uDC79";
    }

    @Override
    public boolean taskMonster(int difficulty) {
        if (difficulty == 1) {
            return super.taskMonster(difficulty);
        }

        Random r = new Random();
        int max = difficulty * 10;
        int a = r.nextInt(max);
        int b = r.nextInt(max);
        int c = r.nextInt(max);

        System.out.println("Большой монстр спрашивает " + a + " + " + b + " * " + c + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();

        if (a + b * c == ans) {
            System.out.println("Ты одолел большого монстра!");
            return true;
        } else {
            System.out.println("Большой монстр нанес тебе урон");
            return false;
        }
    }
}
