import game.ConicalTower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConicalTower tower = new ConicalTower(6);

        System.out.println(tower.Draw());

        while (true) {
            System.out.printf("Enter level number from large to small (1-%d), `r` to remove last item, or `q` to exit: ", tower.getLevels());
            Scanner scanner = new Scanner(System.in);
            String a = scanner.nextLine();

            switch (a) {
                case "q" -> {
                    return;
                }
                case "r" -> tower.RemoveLastRing();
                default -> {
                    try {
                        tower.AddRing(Integer.parseInt(a));
                    } catch (NumberFormatException ex) {
                        System.out.println("Input is incorrect");
                    }
                }
            }

            System.out.println(tower.Draw());
        }
    }
}