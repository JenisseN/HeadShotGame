package jenisse.m8.uf3.Enemies;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface IEnemic {
	
	 	boolean fueradePantalla();
	
		boolean choque(Rectangle player);
	
		void dibujarse(SpriteBatch batchAbierto);
	
		void actualizarse(float delta);
}
