package jenisse.m8.uf3.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import jenisse.m8.uf3.Constants.Constants;

public class EnemicRecte implements IEnemic{
	
	public Rectangle recEnemic;
		Texture textureEnemigo;
	
		public EnemicRecte(float x, float y, float ample, float alt, Texture tex) {
			recEnemic = new Rectangle(x, y, ample, alt);
			textureEnemigo = tex;
		}
	
		public void actualizarse(float delta) {
			recEnemic.y -= delta * Constants.VELOCIDAD_ZOMBIE;
		}
	
		public void dibujarse(SpriteBatch batchAbierto) {
			batchAbierto.draw(textureEnemigo, recEnemic.x, recEnemic.y, recEnemic.width, recEnemic.height);
		}

		public boolean choque(Rectangle jugador) {
			return this.recEnemic.overlaps(jugador);
		}
	
		public boolean fueradePantalla() {		
			return recEnemic.y < -recEnemic.height;
		}

}
