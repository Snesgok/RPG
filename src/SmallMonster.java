import java.util.Random;
import java.util.Scanner;

public class SmallMonster extends Monster {

    public SmallMonster(int size){
        super(size);
        this.image = "\uD83D\uDC7A";
    }

    @Override
    public boolean taskMonster(int difficulty) {
        Random r = new Random();
        int a = r.nextInt(50);
        int b = r.nextInt(a+1);

        System.out.println("Маленький монстр пищит " + a + " - " + b + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();

        if (a - b == ans) {
            System.out.println("Верно, монстр убит");
            return true;
        } else {
            System.out.println("Даже маленького монстра не смог победить...");
            return false;
        }
    }
}
