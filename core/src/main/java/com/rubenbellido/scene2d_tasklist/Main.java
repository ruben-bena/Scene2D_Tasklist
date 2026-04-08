package com.rubenbellido.scene2d_tasklist;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;

    @Override
    public void create() {
        // Usar ScreenViewport suele ser más sencillo para UIs de herramientas
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // 1. Creamos la Tabla Principal que contendrá todo
        Table mainTable = new Table();
        mainTable.setFillParent(true); // Hace que la tabla ocupe toda la pantalla
        // mainTable.setDebug(true); // Descomenta esto para ver las líneas de la cuadrícula

        // 2. Creamos la parte IZQUIERDA (Formulario)
        Table leftTable = new Table();
        leftTable.add(new Label("Nueva Tarea", skin)).padBottom(10).row();

        TextArea textArea = new TextArea("", skin);
        // Creamos un fondo gris oscuro para el área del texto
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new Texture(pixmap));

        // Le asignamos el fondo directamente al estilo del TextArea
        textArea.getStyle().background = drawable;

// Ahora lo añadimos a la tabla normalmente (sin el .background)
        leftTable.add(textArea).width(200).height(100).padBottom(10).row();

        TextButton addButton = new TextButton("Añadir Item", skin);
        leftTable.add(addButton).fillX();
        addButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Botó clicat!");
            }
        });

        // 3. Creamos la parte DERECHA (Lista)
        // Usamos un List de Scene2D para mostrar elementos
        List<String> itemList = new List<>(skin);
        itemList.setItems("Tarea 1", "Tarea 2", "Tarea 3", "Tarea 4");

        // Es buena práctica meter las listas en un ScrollPane por si crecen mucho
        ScrollPane scrollPane = new ScrollPane(itemList, skin);

        // 4. Juntamos las dos partes en la Tabla Principal
        mainTable.add(leftTable).expand().fill().pad(20); // Celda izquierda
        mainTable.add(scrollPane).expand().fill().pad(20); // Celda derecha

        // 5. Añadimos la tabla al Stage
        stage.addActor(mainTable);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
