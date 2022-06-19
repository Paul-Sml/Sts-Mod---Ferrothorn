package ferrothorn.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import ferrothorn.FerrothornMod;
import ferrothorn.util.TextureLoader;

import static ferrothorn.FerrothornMod.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class StockpilePower extends AbstractPower implements CloneablePowerInterface, OnLoseBlockPower {
    public AbstractCreature source;

    public static final String POWER_ID = FerrothornMod.makeID("StockpilePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Stockpile84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Stockpile32.png"));

    public StockpilePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new StockpilePower(owner, source, amount);
    }

    @Override
    public int onLoseBlock(DamageInfo info, int damageAmount) {
        int z = this.owner.currentBlock;
        if (damageAmount <= z)
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new Stamina(this.owner, this.owner, damageAmount * this.amount), damageAmount * this.amount));
        else
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new Stamina(this.owner, this.owner, z * this.amount), z * this.amount));
        return damageAmount;
    }
}

