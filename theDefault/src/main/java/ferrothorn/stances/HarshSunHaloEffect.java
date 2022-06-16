package ferrothorn.stances;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class HarshSunHaloEffect extends AbstractGameEffect {
    private float effectDuration;
    private float x;
    private float y;
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private Texture img;

    public HarshSunHaloEffect() {
        this.img = ImageMaster.ORB_LIGHTNING;
        this.effectDuration = 0.6F;
        this.duration = this.effectDuration;
        this.startingDuration = this.effectDuration;
        this.x = 950;
        this.y = AbstractDungeon.floorY + 600;
        this.x *= Settings.scale;
        this.y *= Settings.scale;
        this.sX = this.x;
        this.sY = this.y;
        this.tX = x;
        this.tY = y;
        this.color = Color.ORANGE.cpy();
        color.a = 0.5f;
        this.scale = 10.0f * Settings.scale;
        this.renderBehind = true;
    }

    public void update() {
        this.x = Interpolation.swingOut.apply(this.tX, this.sX, this.duration);
        this.y = Interpolation.swingOut.apply(this.tY, this.sY, this.duration);
        super.update();
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        rotation += 1.5;
        sb.setBlendFunction(770, 1);
        sb.draw(img, this.x - (float) this.img.getWidth() / 2.0F, this.y - (float) this.img.getWidth() / 2.0F, (float) this.img.getWidth() / 2.0F, (float) this.img.getHeight() / 2.0F, (float) this.img.getHeight(), (float) this.img.getHeight(), this.scale, this.scale, this.rotation,0,0,96,96,false,false);
        sb.draw(img, this.x - (float) this.img.getWidth() / 2.0F, this.y - (float) this.img.getWidth() / 2.0F, (float) this.img.getWidth() / 2.0F, (float) this.img.getHeight() / 2.0F, (float) this.img.getHeight(), (float) this.img.getHeight(), this.scale, this.scale, -this.rotation,0,0,96,96,false,false);
        duration = 1.0f;
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
