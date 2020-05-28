package cat.inspedralbes.m8.uf3.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cat.inspedralbes.m8.uf3.ElMeuJoc;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Nave Game";
	    config.width = 1000;
	    config.height = 800;
	    
		new LwjglApplication(new ElMeuJoc(), config);
	}
}
