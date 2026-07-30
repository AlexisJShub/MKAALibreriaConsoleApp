package org.andresaquino.system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author informatica
 */
public class Main extends Application{

    public static void main(String [] args) {
       launch(args);
    }
    @Override
    public void start(Stage escenarioPrincipal) throws Exception{
        Pane raiz = new Pane();
        
        Scene escena = new Scene(raiz, 450, 600);
        escenarioPrincipal.setScene(escena);
                escenarioPrincipal.show();
    }
}
