package game;

import java.util.AbstractMap;
import java.util.Stack;

public class ConicalTower {
    private final int Levels;
    private final Ring[] Rings;
    private final Stack<AbstractMap.SimpleEntry<Integer, Ring>> StackedRings;
    private int FilledToLevel = -1;

    public int getLevels() {
        return Levels;
    }

    public ConicalTower(int levels) {
        Levels = levels;

        StackedRings = new Stack<>();

        Rings = new Ring[levels];

        for (int i = 0; i < Rings.length; i++) {
            Rings[i] = new Ring(i + 1);
        }
    }

    public void AddRing(int level) {
        if (level > Levels) {
            System.out.println("cannot find the specified level");
            return;
        }

        if (StackedRings.size() > 0 && FilledToLevel >= Levels) {
            System.out.println("cannot add more rings");
            return;
        }

        if (FilledToLevel < level) {
            FilledToLevel = level;
        } else {
            FilledToLevel++;
        }

        StackedRings.push(new AbstractMap.SimpleEntry<>(FilledToLevel, Rings[level - 1]));
    }

    public void RemoveLastRing() {
        if (StackedRings.size() > 0) {
            StackedRings.pop();

            FilledToLevel = 0;

            if (StackedRings.size() > 0) {
                FilledToLevel = StackedRings.lastElement().getKey();
            }
        }
    }

    public String Draw() {
        StringBuilder result = new StringBuilder();
        var eleIndex = StackedRings.size() - 1;

        for (int i = Levels - 1; i >= 0; i--) {
            AbstractMap.SimpleEntry<Integer, Ring> ele = null;

            if (eleIndex >= 0 && eleIndex <= StackedRings.size() - 1) {
                ele = StackedRings.elementAt(eleIndex);

                if (ele.getKey() - 1 == i) {
                    result.append(" ".repeat(ele.getValue().getLevel() - 1));
                    result.append("|").append(":".repeat((Levels - ele.getValue().getLevel() + 1) * 2)).append("|\n");
                    eleIndex--;

                    continue;
                }
            }

            result.append(" ".repeat(i + 1));
            result.append("/");
            if (Levels == i + 1) {
                result.append("َ");
            }
            result.append(" ".repeat((Levels - i - 1) * 2));
            result.append("\\\n");
        }

        return result.toString();
    }

}