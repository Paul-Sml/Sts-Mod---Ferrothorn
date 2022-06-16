package ferrothorn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ferrothorn.FerrothornMod;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "gainEnergy"
)

public class CheckEnergyPatch {
    @SpirePostfixPatch
    public static void postfix(AbstractPlayer __instance, int e) {
        if (!AbstractDungeon.actionManager.turnHasEnded) {
            FerrothornMod.checkEnergy += e;
            AbstractDungeon.player.hand.refreshHandLayout();
        }
    }
}

