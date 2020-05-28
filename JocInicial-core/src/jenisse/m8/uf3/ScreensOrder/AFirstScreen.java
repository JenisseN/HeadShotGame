package jenisse.m8.uf3.ScreensOrder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import jenisse.m8.uf3.Constants.Constants;

public class AFirstScreen implements Screen {

	private JuegoHSBase juego;
	private Texture imgBackground;
	float tempsdesdeIniciJoc = 0;
	static int TEMPS = 2;

	
	public AFirstScreen(JuegoHSBase joc) {
		this.juego = joc;
		this.imgBackground = new Texture(Gdx.files.internal("Escenarios/ABackground.jpg"));
	}

	@Override
	public void render(float delta) {
		tempsdesdeIniciJoc += Gdx.graphics.getDeltaTime();
		if (tempsdesdeIniciJoc > TEMPS) {
			this.juego.setScreen(new BInstructionsScreen(this.juego) );
		} else {
			juego.batch.begin();
			juego.batch.draw(imgBackground, 0, 0, Constants.MON_AMPLE, Constants.MON_ALT);
			juego.fontA.draw(juego.batch, "Bienvenido a HeadShot!!! ", 150, 700);
			juego.fontA.draw(juego.batch, "Ataque Zombie! ", 250, 80);
			juego.batch.end();
	
		}
	}

	@Override
	public void show() {
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
		juego.fontA.dispose();
		imgBackground.dispose();
		juego.batch.dispose();
	}
	@Override
	public void resize(int width, int height) {
	}
}
