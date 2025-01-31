package UAW.entities.abilities;

import UAW.graphics.UAWFxDynamic;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.abilities.Ability;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Unit;
import mindustry.graphics.*;

import static mindustry.Vars.tilesize;

public class RazorRotorAbility extends Ability {
	public float damage = 35f;
	public float range = 4f * tilesize;
	public float chance = 0.4f;
	public BulletType bullet;

	RazorRotorAbility() {
	}

	public RazorRotorAbility(float damage, float chance, float range) {
		this.damage = damage;
		this.chance = chance;
		this.range = range;
		this.bullet = new BulletType(damage, range) {{
			layer = Layer.flyingUnitLow - 1;
			despawnEffect = Fx.none;
			lifetime = 10;
			hitSize = range;
			hitEffect = UAWFxDynamic.circleSplash(range, lifetime, Color.white, Pal.gray, Pal.gray);
			speed = 0f;
			absorbable = hittable = false;
			pierceBuilding = pierce = true;
			buildingDamageMultiplier = 0.2f;
		}};
	}

	@Override
	public void update(Unit unit) {
		if (Mathf.chance(Time.delta * chance)) {
			bullet.create(unit, unit.team, unit.x, unit.y, 0);
		}
	}
}
