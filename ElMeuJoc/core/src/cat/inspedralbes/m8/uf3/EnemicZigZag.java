package cat.inspedralbes.m8.uf3;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EnemicZigZag implements IEnemic {


	public Rectangle rec;
		Texture texture;
	
		boolean capALaDreta = true;
	
		public EnemicZigZag(float x, float y, float ample, float alt, Texture tex) {
			rec = new Rectangle(x, y, ample, alt);
			texture = tex;
		}
	
		// Actualitzarse vol dir avancar en zig zag
		public void actualitzarse(float delta) {
			rec.y -= delta *Constants.VELOCIDAD_NAVENEMIC;
	
			if (capALaDreta) {
				// moviment
				rec.x += delta * Constants.VELOCITAT_HORIZONTAL_NAU_ENEMICZIGZAG;
	
				// xoc dret
				if ((rec.x + rec.width) > Constants.MON_AMPLE) {
					rec.x = Constants.MON_AMPLE - rec.width;
					capALaDreta = false;
				}
			} else {
				rec.x -= delta * Constants.VELOCITAT_HORIZONTAL_NAU_ENEMICZIGZAG;
	
				// xoc esquerre
				if (rec.x < 0) {
					rec.x = 0;
					capALaDreta = true;
				}
			}
	
		}
	
		public void dibuixarse(SpriteBatch batchObert) {
			batchObert.draw(texture, rec.x, rec.y, rec.width, rec.height);
		}
		
		public boolean solapa(Rectangle player) {
			return this.rec.overlaps(player);
		}
	
		public boolean foraDePantalla() {
	
			return rec.y < -rec.height;
		}
}
