package ferrothorn.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class PowerAboveCreatureEffect  extends AbstractGameEffect {
    private static final float TEXT_DURATION = 1.5F;
    private static final float STARTING_OFFSET_Y;
    private static final float TARGET_OFFSET_Y;
    private static final float LERP_RATE = 5.0F;
    private static final int W = 128;
    private float x;
    private float y;
    private float offsetY;
    private AbstractPower power;
    private Color outlineColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private Color shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);

    public PowerAboveCreatureEffect(float x, float y, AbstractPower power) {
        this.duration = 1.5F;
        this.startingDuration = 1.5F;
        this.power = power;
        this.x = x;
        this.y = y;
        this.color = Color.WHITE.cpy();
        this.offsetY = STARTING_OFFSET_Y;
        this.scale = Settings.scale;
    }

    public void update() {
        if (this.duration > 1.0F) {
            this.color.a = Interpolation.exp5In.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
        }

        super.update();
        if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
            this.offsetY = MathUtils.lerp(this.offsetY, TARGET_OFFSET_Y + 80.0F * Settings.scale, Gdx.graphics.getDeltaTime() * 5.0F);
        } else {
            this.offsetY = MathUtils.lerp(this.offsetY, TARGET_OFFSET_Y, Gdx.graphics.getDeltaTime() * 5.0F);
        }

        this.y += Gdx.graphics.getDeltaTime() * 12.0F * Settings.scale;
    }

    public void render(SpriteBatch sb) {
        this.outlineColor.a = this.color.a / 2.0F;
        sb.setColor(this.color);
        sb.draw(this.power.region128.getTexture(), this.x - 64.0F, this.y - 64.0F + this.offsetY, 64.0F, 64.0F, 84.0F, 84.0F, this.scale * (2.5F - this.duration), this.scale * (2.5F - this.duration), this.rotation, 0, 0, 84, 84, false, false);
        sb.setBlendFunction(770, 1);
        this.shineColor.a = this.color.a / 4.0F;
        sb.setColor(this.shineColor);
        sb.draw(this.power.region128.getTexture(), this.x - 64.0F, this.y - 64.0F + this.offsetY, 64.0F, 64.0F, 84.0F, 84.0F, this.scale * (2.7F - this.duration), this.scale * (2.7F - this.duration), this.rotation, 0, 0, 84, 84, false, false);
        sb.draw(this.power.region128.getTexture(), this.x - 64.0F, this.y - 64.0F + this.offsetY, 64.0F, 64.0F, 84.0F, 84.0F, this.scale * (3.0F - this.duration), this.scale * (3.0F - this.duration), this.rotation, 0, 0, 84, 84, false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }

    static {
        STARTING_OFFSET_Y = 0.0F * Settings.scale;
        TARGET_OFFSET_Y = 60.0F * Settings.scale;
    }
}
