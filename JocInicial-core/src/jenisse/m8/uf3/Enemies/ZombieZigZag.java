package jenisse.m8.uf3.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import jenisse.m8.uf3.Constants.Constants;

public class ZombieZigZag implements IEnemic {

	public Rectangle zombie;
		Texture textureZombie;
		boolean haciaLaDerecha = true;
	
		public ZombieZigZag(float x, float y, float ample, float alt, Texture tex) {
			zombie = new Rectangle(x, y, ample, alt);
			textureZombie = tex;
		}
	
		public void actualizarse(float delta) {
			zombie.y -= delta *Constants.VELOCIDAD_ZOMBIE;
			if (haciaLaDerecha) {
				zombie.x += delta * Constants.VELOCIDAD_ZOMBIEZIGZAG;
	
				if ((zombie.x + zombie.width) > Constants.MON_AMPLE) {
					zombie.x = Constants.MON_AMPLE - zombie.width;
					haciaLaDerecha = false;
				}
			} else {
				zombie.x -= delta * Constants.VELOCIDAD_ZOMBIEZIGZAG;
	

				if (zombie.x < 0) {
					zombie.x = 0;
					haciaLaDerecha = true;
				}
			}
		}
	
		public void dibujarse(SpriteBatch batchObert) {
			batchObert.draw(textureZombie, zombie.x, zombie.y, zombie.width, zombie.height);
		}
		
		public boolean choque(Rectangle player) {
			return this.zombie.overlaps(player);
		}
	
		public boolean fueradePantalla() {
			return zombie.y < -zombie.height;
		}
}
