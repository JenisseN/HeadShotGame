package cat.inspedralbes.m8.uf3;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;;

public class ElMeuJoc extends Game {

	// camera 2D
	public OrthographicCamera camera;
	
	// special draw 2D images
	public SpriteBatch batch;
	//fort text on screen
	public BitmapFont font;


	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		//el juego se pasa a si mismo por referencia
		setScreen(new SplashScreen(this));
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void render() {
		// color of screen dark blue color
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//este render llama al metodo render de la clase padre (Game) y este llama al 
		//render de la screen activa
		//sin este render no se veria nada
		super.render();
	}

	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
