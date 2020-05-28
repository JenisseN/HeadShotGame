package cat.inspedralbes.m8.uf3;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface  IEnemic {
	
	 	boolean foraDePantalla();
	
		boolean solapa(Rectangle player);
	
		void dibuixarse(SpriteBatch batchObert);
	
		void actualitzarse(float delta);
}
