package cat.inspedralbes.m8.uf3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class SplashScreen implements Screen {

	ElMeuJoc joc;
	
	// Mantener LOGO o JOC
	float tempsdesdeIniciJoc = 0;
	static int TEMPS_LOGO_SPLASH = 3;
	
	static int ESTADO_LOGO = 0;
	static int ESTADO_JOC = 0;
	// estado inicial
	int estado = ESTADO_LOGO;

	//logo
	private Texture logo;
	
	
	public SplashScreen(ElMeuJoc joc) {
		this.joc = joc;
		// logo pantalla principal
		logo = new Texture(Gdx.files.internal("jOc_general/PRINCIPAL.jpg"));
	}


	@Override
	public void render(float delta) {
		tempsdesdeIniciJoc += Gdx.graphics.getDeltaTime();
		// Si ha pasado un tiempo, cambia a juego
		if (tempsdesdeIniciJoc > TEMPS_LOGO_SPLASH) {
			//creo un objeto y le doy referencia
			joc.setScreen(new JocScreen(joc) );
			
		} else {
			// Dibujo estirando toda la pantalla
			// Dibuxo LOGO
			//joc.camera.update();
			//joc.batch.setProjectionMatrix(camera.combined);
			joc.batch.begin();
			joc.batch.draw(logo, 0, 0, Constants.MON_AMPLE, Constants.MON_ALT);
			joc.batch.end();
		}

	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}



}
