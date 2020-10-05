package hotciv.variants;

import hotciv.common.ModifierStrategy;

public class randomModifierStrategy implements ModifierStrategy {
    public int getModifier() {
        double randomNumber = Math.random() * 5 + 1;
        return (int) randomNumber;
    }
}
