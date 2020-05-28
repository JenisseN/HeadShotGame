package jenisse.m8.uf3.ScreensOrder;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class JuegoHSBase extends Game {

	public OrthographicCamera camera;
	public SpriteBatch batch;
	public BitmapFont fontA;
	public BitmapFont fontB;
	public int numeroJugador=0;


	@Override
	public void create() {
		batch = new SpriteBatch();
		fontA= new BitmapFont(Gdx.files.internal ("fonts/fuente1.fnt"),Gdx.files.internal ("fonts/fuente1.png"), false);
		fontB= new BitmapFont(Gdx.files.internal ("fonts/fuente2.fnt"),Gdx.files.internal ("fonts/fuente2.png"), false);
		setScreen(new AFirstScreen(this));
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 2, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	
	@Override
	public void dispose() {
		batch.dispose();
		fontA.dispose();
	}
}
