package jenisse.m8.uf3.ScreensOrder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import jenisse.m8.uf3.Constants.Constants;

public class EGameoOver implements Screen {
	
	private JuegoHSBase juego;
	private Texture imgGameOver;
	float tempsdesdeIniciJoc = 0;
	static int TEMPS = 2;
	
	public EGameoOver(JuegoHSBase joc) {
		this.juego = joc;
		imgGameOver = new Texture(Gdx.files.internal("Escenarios/EYouDie.jpg"));
	}
	

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		tempsdesdeIniciJoc += Gdx.graphics.getDeltaTime();
		if (tempsdesdeIniciJoc > TEMPS) {
			this.juego.setScreen(new AFirstScreen(this.juego) );
		} else {
			juego.batch.begin();
			juego.batch.draw(imgGameOver, 0, 0, Constants.MON_AMPLE, Constants.MON_ALT);
			juego.batch.end();
		}

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		juego.batch.dispose();
		imgGameOver.dispose();
	}

}
