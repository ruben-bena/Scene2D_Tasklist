package com.rubenbellido.scene2d_tasklist;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    public FitViewport viewport;
    @Override
    public void create() {
        // Crear un Stage i un Skin
        viewport = new FitViewport(8,5);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("uiskin.json")); // Carregar un Skin per defecte
        float escala = viewport.getWorldHeight() / Gdx.graphics.getHeight();

        // Ajustem paràmetres per a la font
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(); // Font per defecte
        labelStyle.font.setUseIntegerPositions(false);
        labelStyle.font.getData().setScale( escala );

        // Creem label (TextView)
        Label label = new Label("Hola, això és un TextView", labelStyle);
        label.setPosition(1, 1); // Posició del Label

        // Crear un Button
        TextButton button = new TextButton("Clica el botonet!", skin );
        button.setPosition(2, 2); // Posició del Button
        button.setTransform(true);
        button.setScale( 2*escala );
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Botó clicat!");
            }
        });

        // Afegir els actors al Stage
        stage.addActor(label);
        stage.addActor(button);

        // Configurar l'Stage com a gestor d'entrada
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        // Netejar la pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualitzar i dibuixar l'Stage
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        // Alliberar recursos
        stage.dispose();
        skin.dispose();
    }
}
