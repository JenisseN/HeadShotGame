package cat.inspedralbes.m8.uf3;

import java.util.Iterator;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class JocScreen extends ScreenAdapter {
	// Intancia del juego
	ElMeuJoc joc;
	
	// camera 2D
	private OrthographicCamera camera;
	public Viewport viewport;

	// Grafics
	private Texture playerImg;
	private Texture enemiImg;
	private Texture disparoImg;
	private Texture fonsTexture;
	private Rectangle playerR;
	private Array<IEnemic> enemicList;
	private Array<Rectangle> disparsList;

	// Musica y sonidos
	private Sound splatSound;
	private Music rainMusic;

	// Variables del tiempo
	int tempsUltimEnemic = 2;


	public JocScreen(ElMeuJoc joc) {
		this.joc = joc;

		// Configura la resolucion de nuestro mundo
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.MON_AMPLE, Constants.MON_ALT);
		viewport = new FitViewport(Constants.MON_AMPLE, Constants.MON_ALT, camera);
		
		// positions y medida of the player and enemics
		playerR = new Rectangle(10, 0, Constants.AMPLE_PLAYER, Constants.ALT_PLAYER);
		
		// Inicializacion de lista de enemigos y disparos
		enemicList = new Array<IEnemic>();
		disparsList = new Array<Rectangle>();

		// Imagenes de los graficos del juego
		playerImg = new Texture(Gdx.files.internal("naves/player_red.png"));
		enemiImg = new Texture(Gdx.files.internal("naves/enemyBlack.png"));
		disparoImg = new Texture(Gdx.files.internal("naves/laserGreen.png"));
		fonsTexture = new Texture(Gdx.files.internal("joc_general/UNIVERSO.jpg"));
		// Musica
		splatSound = Gdx.audio.newSound(Gdx.files.internal("music/shot.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("music/wild_electronic.mp3"));
		rainMusic.setLooping(true);
		rainMusic.play();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void render(float delta) {
		// 1. Gestion de input keyboard
		gestionKeyboardJoc();
		// 2. Calculos de movimientos para enemigos ,Coliciones y disparos
		actualizaEnemics();

		// 3.Dibujar --> muy importante siempre se ha de dibujar entre un begin y un end
		// Las dos siguientes lineas son muy importantes porque la clase que dibuja y la
		// camara han de tenr la misma idea de la geografia de las cosas del juego.
		camera.update();
		joc.batch.setProjectionMatrix(camera.combined);
		joc.batch.begin();
		// Primero se deibuja el background
		joc.batch.draw(fonsTexture, 0, 0, Constants.MON_AMPLE, Constants.MON_ALT);
		joc.batch.draw(playerImg, playerR.x, playerR.y, playerR.width, playerR.height);
		
		for (IEnemic enemic : enemicList) {
			enemic.dibuixarse(joc.batch);
		}
		for (Rectangle disparo : disparsList) {
			joc.batch.draw(disparoImg, disparo.x, disparo.y, Constants.ANCHO_LASER, Constants.ALTO_LASER);
		}
		joc.batch.end();
	}

	private void gestionKeyboardJoc() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			playerR.x -= Constants.VELOCIDAD_NAVEJUGADOR * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			playerR.x += Constants.VELOCIDAD_NAVEJUGADOR * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			playerR.y += Constants.VELOCIDAD_NAVEJUGADOR * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			playerR.y -= Constants.VELOCIDAD_NAVEJUGADOR * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			float x = playerR.x + playerR.width / 2;
			float y = playerR.y + playerR.height;
			disparsList.add(new Rectangle(x, y, Constants.ANCHO_LASER, Constants.ALTO_LASER));
		}

		int larchScreen = Constants.MON_AMPLE;
		int altScreen = Constants.MON_ALT;

		if (playerR.x < 0)
			playerR.x = 0;
		if (playerR.x > (larchScreen - playerR.width))
			playerR.x = larchScreen - playerR.width;

		if (playerR.y < 0)
			playerR.y = 0;

		if (playerR.y > (altScreen - playerR.width))
			playerR.y = altScreen - playerR.width;
	}

	private void actualizaEnemics() {
		float delta = Gdx.graphics.getDeltaTime();
		tempsUltimEnemic += delta ;

		// Nuevos enemigos
		// if(tempsUltimEnemic > Constants.tiempo_entre_AparicionEnemics) {
		if (tempsUltimEnemic > Constants.TIEMPO_ENTRE_APARICIONES_ENEMISMOS_S) {
			int x = new Random().nextInt(Constants.MON_AMPLE);
			int y = Constants.MON_ALT;
			//Ahora todos los enemigos hacen zigZag
			IEnemic nouEnemic = new EnemicZigZag(x, y, Constants.AMPLE_ENEMIC, Constants.ALT_ENEMIC, enemiImg);
			enemicList.add(nouEnemic);
			tempsUltimEnemic = 0;
		}

		// Disparos Avanzan
		for (Rectangle disparo : disparsList) {
			disparo.y += Constants.VELOCIDAD_NAVEJUGADOR * delta;
		}

		for (IEnemic ememi : enemicList) {
			ememi.actualitzarse(delta);
		}

		// Colisiones de enemigos con Player, si chocan vuelve a la pantalla de inicio
		for (IEnemic ememi : enemicList) {
			if (ememi.solapa(playerR)) {
				joc.setScreen(new SplashScreen(joc));
			}
		}

		// Colisiones de disparos con enemigos, enemigos avanzan
		for (Iterator iterEnemic = enemicList.iterator(); iterEnemic.hasNext();) {
			IEnemic enemic = (IEnemic) iterEnemic.next();

			for (Iterator iterDisparo = disparsList.iterator(); iterDisparo.hasNext();) {
				Rectangle playerDisp = (Rectangle) iterDisparo.next();
				if (enemic.solapa(playerDisp)) {
					splatSound.play();
					iterEnemic.remove();
					iterDisparo.remove();
					break;
				}
			}
		}
		Gdx.app.debug("Enemics", "Hi ha " + enemicList.size + " enemics");

		// Enemigos fuera de la pantalla se eliminan
		for (Iterator iterator = enemicList.iterator(); iterator.hasNext();) {
			IEnemic enemic = (IEnemic) iterator.next();
			if (enemic.foraDePantalla()) {
				Gdx.app.debug("elimino", "");
				iterator.remove();
			}
		}
	}

	@Override
	public void dispose() {
		playerImg.dispose();
		enemiImg.dispose();
		disparoImg.dispose();
		rainMusic.dispose();
		splatSound.dispose();
		fonsTexture.dispose();
	}

}
