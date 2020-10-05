package hotciv.stubs;

import hotciv.common.ModifierStrategy;

public class fixedModifierStrategy implements ModifierStrategy {
    public int getModifier() {
        return 1;
    }
}
