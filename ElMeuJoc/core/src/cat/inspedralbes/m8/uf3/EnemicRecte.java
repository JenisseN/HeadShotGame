package cat.inspedralbes.m8.uf3;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EnemicRecte implements IEnemic{
	
	public Rectangle rec;
		Texture texture;
	
		public EnemicRecte(float x, float y, float ample, float alt, Texture tex) {
			rec = new Rectangle(x, y, ample, alt);
			texture = tex;
		}
	
		// Actualitzarse vol dir avancar cap avall
		public void actualitzarse(float delta) {
			rec.y -= delta * Constants.VELOCIDAD_NAVENEMIC;
			//rec.y-=20;
		}
	
		// Dibuixar-se vol dir pintar una textura en forma quadrada (la funcionalitat
		// per defecte de draw)
		// a partir de la coordinada x,y
		public void dibuixarse(SpriteBatch batchObert) {
			batchObert.draw(texture, rec.x, rec.y, rec.width, rec.height);
		}
	
		// Dir si xoca es dir si el rectangle intern de lenemic
		// se solapa amb el player
		public boolean solapa(Rectangle player) {
			return this.rec.overlaps(player);
		}
	
		public boolean foraDePantalla() {		
			return rec.y < -rec.height;
		}

}
