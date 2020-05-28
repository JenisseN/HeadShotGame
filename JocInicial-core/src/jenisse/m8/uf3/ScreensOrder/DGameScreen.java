package jenisse.m8.uf3.ScreensOrder;

import java.util.Iterator;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jenisse.m8.uf3.Constants.Constants;
import jenisse.m8.uf3.Enemies.IEnemic;
import jenisse.m8.uf3.Enemies.ZombieZigZag;

public class DGameScreen extends ScreenAdapter {

	private JuegoHSBase juego;
	private OrthographicCamera camera;
	private Viewport viewport;

	// Grafics
	private Texture texturaJugador;
	private Texture texturaEnemigo;
	private Texture texturaDisparo;
	private Texture texturaFondo;
	private Rectangle jugadorR;
	private Array<IEnemic> enemigosZ;
	private Array<Rectangle> disparosJugador;

	// Musica y sonidos
	private Music cityMusic;
	private Music enemiDeadSound;
	
	// Variables del tiempo
	int tiempoUltimoEnemigo = 2;


	public DGameScreen(JuegoHSBase juego) {
		this.juego = juego;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.MON_AMPLE, Constants.MON_ALT);
		viewport = new FitViewport(Constants.MON_AMPLE, Constants.MON_ALT, camera);
		
		jugadorR = new Rectangle(10, 0, Constants.ANCHO_JUGADOR, Constants.ALTO_JUGADOR);
		enemigosZ = new Array<IEnemic>();
		disparosJugador = new Array<Rectangle>();

		//Testuras de los juegadores
		texturaJugador = new Texture(Gdx.files.internal("Characteres/Car_Soldier.png"));
		texturaEnemigo = new Texture(Gdx.files.internal("Characteres/character_zombie.png"));
		texturaDisparo = new Texture(Gdx.files.internal("Characteres/laserGreen.png"));
		texturaFondo = new Texture(Gdx.files.internal("Escenarios/DGame_EscenarioNivel1.jpg"))
				;
		// Musica
		cityMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Zombie_Garden.mp3"));
		enemiDeadSound = Gdx.audio.newMusic(Gdx.files.internal("music/ZombieDead.wav"));
		cityMusic.setLooping(false);
		cityMusic.play();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void render(float delta) {
		//Como el jugador se muev
		keabordJugadorMovements();
		//Calculos de movimientos de los zombies, choques
		enemicsMovements();

		//Dibujar en pantalla
		camera.update();
		juego.batch.setProjectionMatrix(camera.combined);
		juego.batch.begin();
		juego.batch.draw(texturaFondo, 0, 0, Constants.MON_AMPLE, Constants.MON_ALT);
		juego.batch.draw(texturaJugador, jugadorR.x, jugadorR.y, jugadorR.width, jugadorR.height);
		
		for (IEnemic enemic : enemigosZ) {
			enemic.dibujarse(juego.batch);
		}
		for (Rectangle disparo : disparosJugador) {
			juego.batch.draw(texturaDisparo, disparo.x, disparo.y, Constants.ANCHO_CUCHILLO, Constants.ALTO_CUCHILLO);
		}
		juego.batch.end();
	}

	private void keabordJugadorMovements() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			jugadorR.x -= Constants.VELOCIDAD_JUGADOR * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			jugadorR.x += Constants.VELOCIDAD_JUGADOR * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			jugadorR.y += Constants.VELOCIDAD_JUGADOR * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			jugadorR.y -= Constants.VELOCIDAD_JUGADOR * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			float x = jugadorR.x + jugadorR.width / 2;
			float y = jugadorR.y + jugadorR.height;
			disparosJugador.add(new Rectangle(x, y, Constants.ANCHO_CUCHILLO, Constants.ALTO_CUCHILLO));
		}

		int larchScreen = Constants.MON_AMPLE;
		int altScreen = Constants.MON_ALT;

		if (jugadorR.x < 0)
			jugadorR.x = 0;
		if (jugadorR.x > (larchScreen - jugadorR.width))
			jugadorR.x = larchScreen - jugadorR.width;

		if (jugadorR.y < 0)
			jugadorR.y = 0;

		if (jugadorR.y > (altScreen - jugadorR.width))
			jugadorR.y = altScreen - jugadorR.width;
	}

	private void enemicsMovements() {
		float delta = Gdx.graphics.getDeltaTime();
		tiempoUltimoEnemigo += 2 ;

		if (tiempoUltimoEnemigo > Constants.TIEMPO_ENTRE_APARICIONES_ENEMISMOS_S) {
			int x = new Random().nextInt(Constants.MON_AMPLE);
			int y = Constants.MON_ALT;
			//Ahora todos los zombien hacen zigZag
			IEnemic nouEnemic = new ZombieZigZag(x, y, Constants.ALTO_ZOMBIE, Constants.ANCHO_ZOMBIE, texturaEnemigo);
			enemigosZ.add(nouEnemic);
			tiempoUltimoEnemigo = 0;
		}


		for (Rectangle disparo : disparosJugador) {			
			disparo.y += Constants.VELOCIDAD_JUGADOR * delta;
		}

		for (IEnemic ememi : enemigosZ) {
			ememi.actualizarse(delta);
		}

		// Colisiones de enemigos con Player, si chocan vuelve se le quita una vida y sale la pantalla gameOver
		for (IEnemic ememi : enemigosZ) {
			if (ememi.choque(jugadorR)) {
				cityMusic.stop();
				juego.setScreen(new EGameoOver(juego));
			}
		}

		// Colisiones de disparos con zombies y los zombies avanzan
		for (Iterator iterEnemic = enemigosZ.iterator(); iterEnemic.hasNext();) {
			IEnemic enemic = (IEnemic) iterEnemic.next();

			for (Iterator iterDisparo = disparosJugador.iterator(); iterDisparo.hasNext();) {
				Rectangle playerDisp = (Rectangle) iterDisparo.next();
	
				if (enemic.choque(playerDisp)) {
					enemiDeadSound.play();
					iterEnemic.remove();
					iterDisparo.remove();
					break;
				}
			}
		}
		Gdx.app.debug("Enemics", "Hi ha " + enemigosZ.size + " enemics");

		// Enemigos fuera de la pantalla se eliminan
		for (Iterator iterator = enemigosZ.iterator(); iterator.hasNext();) {
			IEnemic enemic = (IEnemic) iterator.next();
			if (enemic.fueradePantalla()) {
				Gdx.app.debug("elimino", "");
				iterator.remove();
			}
		}
	}

	@Override
	public void dispose() {
		texturaJugador.dispose();
		texturaEnemigo.dispose();
		texturaDisparo.dispose();
		cityMusic.dispose();
		texturaFondo.dispose();
		enemiDeadSound.dispose();
	}

}
