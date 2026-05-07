package com.rubenbellido.scene2d_tasklist;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    public FitViewport viewport;

    // Elementos UI
    TextField textField;
    TextButton button;
    Label label;
    Array<String> tasks;
    List list;
    ScrollPane scrollPane;
    @Override
    public void create() {
        // Crear un Stage i un Skin
        viewport = new FitViewport(800,500);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("uiskin.json")); // Carregar un Skin per defecte
        float escala = viewport.getWorldHeight() / Gdx.graphics.getHeight();

        // Creem label (TextView)
        label = new Label("Hola, això és un TextView", skin);
        label.setPosition(100, 100); // Posició del Label

        // Crear un Textfield
        textField = new TextField("prueba", skin);
        textField.setPosition(100, 300);

        // Crear un Button
        button = new TextButton("Clica el botonet!", skin );
        button.setPosition(100, 200); // Posició del Button
        button.setTransform(true);
        button.setScale( 2*escala );
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addTask(textField.getText());
            }
        });

        // Crear Array para guardar tareas
        tasks = new Array<>();
        tasks.add("uno","dos","tres");

        // Crear una List
        list = new List<>(skin);
        list.setItems(tasks);

        // Crear un ScrollPane
        scrollPane = new ScrollPane(list, skin);
        scrollPane.setPosition(400, 0);
        scrollPane.setSize(400, 500);

        // Afegir els actors al Stage
        stage.addActor(label);
        stage.addActor(button);
        stage.addActor(textField);
        stage.addActor(scrollPane);

        // Configurar l'Stage com a gestor d'entrada
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        logic();
        draw();
    }

    public void draw() {
        // Netejar la pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualitzar i dibuixar l'Stage
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void logic() {

    }

    public void addTask(String task) {
        // Si el usuario no pone nada en el TextField, que no añada un string vacío
        if (task.isEmpty()) {
            return;
        }

        // Actualizar lista con nueva tarea
        tasks.add(task);
        list.setItems(tasks);
    }

    @Override
    public void dispose() {
        // Alliberar recursos
        stage.dispose();
        skin.dispose();
    }
}
