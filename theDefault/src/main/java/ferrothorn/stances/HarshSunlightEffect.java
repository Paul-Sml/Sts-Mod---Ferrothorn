package ferrothorn.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class HarshSunlightEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private static final float DUR = 2.0F;
    private static TextureAtlas.AtlasRegion img;
    private boolean playedSfx = false;
    float RotationMod;
    //private Color color2;
    public HarshSunlightEffect() {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/laserThick");
        }
        RotationMod = MathUtils.random(-165,-55);
        this.x = 950;
        this.y = AbstractDungeon.floorY + 600;
        this.x *= Settings.scale;
        this.y *= Settings.scale;
        color = new Color(CardHelper.getColor(MathUtils.random(230,255),MathUtils.random(187,195),MathUtils.random(12,20))).cpy();
        //color2 = new Color(CardHelper.getColor(MathUtils.random(240,255),MathUtils.random(207,215),MathUtils.random(80,105))).cpy();
        color.a = 0.0f;
        this.duration = 5.0f;
        this.startingDuration = 5.0F;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = Interpolation.pow2In.apply(0.3F, 0.0F, (duration - (startingDuration / 2.0f)) / (startingDuration / 2.0F));
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.0F, 0.3F, (duration / (startingDuration / 2.0F)));
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        //sb.setBlendFunction(770, 1);
        sb.setColor(color);
        sb.draw(img, this.x, this.y - (float)(img.packedHeight / 2), 0.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, this.scale * 2.0F + MathUtils.random(-0.05F, 0.05F),
                this.scale * 5.5F + MathUtils.random(-0.1F, 0.1F), RotationMod);
        sb.draw(img, this.x, this.y - (float)(img.packedHeight / 2), 0.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, this.scale * 2.0F + MathUtils.random(-0.05F, 0.05F),
                this.scale * 5.5F + MathUtils.random(-0.1F, 0.1F), RotationMod);
        //sb.setColor(this.color2);
        sb.draw(img, this.x, this.y - (float)(img.packedHeight / 2), 0.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, this.scale * 2.0F, this.scale * 4.0F, RotationMod);
        sb.draw(img, this.x, this.y - (float)(img.packedHeight / 2), 0.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, this.scale * 2.0F, this.scale * 4.0F, RotationMod);
        RotationMod += 0.2;
        //sb.setBlendFunction(770, 771);
        this.color.a = MathUtils.random(0.1F, 0.2F);
    }

    public void dispose() {
    }
}