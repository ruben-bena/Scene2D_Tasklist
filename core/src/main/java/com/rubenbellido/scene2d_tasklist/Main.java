package com.rubenbellido.scene2d_tasklist;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private TextField textField;
    private Button button;
    private ScrollPane scrollPane;
    private Table tasklistTable;

    @Override
    public void create() {
        // Stage
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // TextField
        textField = new TextField("", skin);
        textField.setAlignment(Align.center);

        // Button
        button = new TextButton("Add to Tasklist", skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addItem(textField.getText());
                textField.setText("");
            }
        });

        // Table
        tasklistTable = new Table();
        tasklistTable.top();

        // ScrollPane
        scrollPane = new ScrollPane(tasklistTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.getStyle().background = skin.getDrawable("textfield");

        // Añadir objetos al Stage
        stage.addActor(textField);
        stage.addActor(button);
        stage.addActor(scrollPane);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        // Resize and position TextField
        textField.setSize(250, 40);
        textField.setPosition(width / 4f - textField.getWidth() / 2f, height - 200);

        // Resize and position Button
        button.setSize(250, 50);
        button.setPosition(textField.getX(), textField.getY() - 60);

        // Resize and position ScrollPane
        scrollPane.setSize(width / 2f, height);
        scrollPane.setPosition(width / 2f, 0);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    private void addItem(String item) {
        if (item == null || item.trim().isEmpty()) {
            return;
        }
        Label label = new Label(item, skin);
        tasklistTable.add(label).expandX().left().pad(5);
        tasklistTable.row();
    }
}
