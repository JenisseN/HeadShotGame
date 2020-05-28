package jenisse.m8.uf3.ScreensOrder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import jenisse.m8.uf3.Constants.Constants;

public class CPlayersScreen implements Screen {

	JuegoHSBase juego;
	public BitmapFont fontC;
	private Texture background;
	private Texture frame;
	private Texture imgAventurero;
	private Texture imgFemale;
	private Texture imgMale;
	private Texture imgSoldier;
	float tempsdesdeIniciJoc = 0;
	static int TEMPS = 2;

	public CPlayersScreen(JuegoHSBase joc) {
		this.juego = joc;
		this.background = new Texture(Gdx.files.internal("Escenarios/CBackground.jpg"));
		this.frame = new Texture(Gdx.files.internal("Escenarios/Cframe.png"));
		this.imgAventurero = new Texture(Gdx.files.internal("Characteres/Car_Aventurero.png"));
		this.imgFemale = new Texture(Gdx.files.internal("Characteres/Car_Female.png"));
		this.imgMale = new Texture(Gdx.files.internal("Characteres/Car_Male.png"));
		this.imgSoldier = new Texture(Gdx.files.internal("Characteres/Car_Soldier.png"));
		this.fontC = new BitmapFont(Gdx.files.internal("fonts/fuente3.fnt"), Gdx.files.internal("fonts/fuente3.png"),
				false);
	}

	@Override
	public void render(float delta) {
		tempsdesdeIniciJoc += Gdx.graphics.getDeltaTime();
		if (tempsdesdeIniciJoc > TEMPS) {
			juego.setScreen(new DGameScreen(juego));
		} else {
			juego.batch.begin();
			juego.batch.draw(background, 0, 0, Constants.MON_AMPLE, Constants.MON_ALT);
			juego.batch.draw(frame, 50, 20, 900, 750);
			this.fontC.draw(juego.batch, "Deber sobrevivir a los zombies con un personaje, elige uno: ", 200, 630);
			juego.batch.draw(imgMale, 200, 450, 150, 150);
			juego.batch.draw(imgSoldier, 350, 450, 150, 150);
			juego.batch.draw(imgAventurero, 500, 450, 150, 150);
			juego.batch.draw(imgFemale, 650, 450, 150, 150);
			juego.batch.end();
		}

	}

	@Override
	public void show() {
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
		background.dispose();
		frame.dispose();
		imgAventurero.dispose();
		imgFemale.dispose();
		imgMale.dispose();
		imgSoldier.dispose();
		fontC.dispose();
		juego.dispose();
	}

}
