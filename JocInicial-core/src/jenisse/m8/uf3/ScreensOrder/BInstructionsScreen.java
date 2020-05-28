package jenisse.m8.uf3.ScreensOrder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import jenisse.m8.uf3.Constants.Constants;

public class BInstructionsScreen implements Screen {

	private JuegoHSBase juego;
	private Texture background;
	public BitmapFont fontB;
	float tempsdesdeIniciJoc = 0;
	static int TEMPS = 2;


	public BInstructionsScreen(JuegoHSBase joc) {
		this.juego = joc;
		this.background = new Texture(Gdx.files.internal("Escenarios/BBackgrund.jpg"));
		this.fontB = new BitmapFont(Gdx.files.internal("fonts/fuente4.fnt"), Gdx.files.internal("fonts/fuente4.png"),
				false);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		tempsdesdeIniciJoc += Gdx.graphics.getDeltaTime();
		if (tempsdesdeIniciJoc > TEMPS) {
			this.juego.setScreen(new CPlayersScreen(this.juego));
		} else {
			juego.batch.begin();
			juego.batch.draw(background, 0, 0, Constants.MON_AMPLE, Constants.MON_ALT);
			this.fontB.draw(juego.batch, "**Instrucciones del Juego: ", 310, 700);
			this.fontB.draw(juego.batch, "*No dejes que te muerdan!!", 50, 480);
			this.fontB.draw(juego.batch, "Tienes 3 vidas", 80, 450);
			this.fontB.draw(juego.batch, "*Aumenta tus puntos!!", 50, 390);
			this.fontB.draw(juego.batch, "Consigue alimentos, agua y medicinas", 80, 360);
			this.fontB.draw(juego.batch, "*Apunta y dispara!!", 50, 300);// ancho largo
			this.fontB.draw(juego.batch, "Consigue armas,cuchillos y municiones", 80, 270);
			this.fontB.draw(juego.batch, "Sobrevivir esta en tus manos, tira a la cabeza...", 500, 40);
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
		juego.dispose();
		background.dispose();
		fontB.dispose();
		juego.batch.dispose();
	}

}
