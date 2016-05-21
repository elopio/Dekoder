package view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;
import utils.MainDecoder;

import java.io.File;
import java.io.IOException;

/**
 * @author ice1000
 * Created by asus1 on 2016/5/21.
 */

public class MainActivity {
	@FXML
	private JFXListView<Object> propertiesList;
	@FXML
	private JFXButton fileButton;
	@FXML
	private JFXButton helpButton;
	@FXML
	private AnchorPane window;
	@FXML
	private JFXButton playButton;
	@FXML
	private Label nameLabel;
	private File file;
	private Dekoder dekoder;

	@FXML
	void playMusic(ActionEvent event) {
		if(dekoder != null) {
			dekoder.play();
		}
	}

	@FXML
	void openFile(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		file = chooser.showOpenDialog(window.getScene().getWindow());
		dekoder = new Dekoder(file.getPath());
		nameLabel.setText(file.getName());
		propertiesList.getItems().removeAll();
	}

	@FXML
	void openHelp(ActionEvent event) {
		String str=  "rundll32 url.dll,FileProtocolHandler https://github.com/ice1000/Dekoder" ;
		try {
			Runtime.getRuntime().exec(str);
		} catch (IOException ignored) {}
	}

	private class Dekoder extends MainDecoder {
		Dekoder(String file) {
			super(file);
		}

		@Override
		public void echo(@NotNull String msg) {
			propertiesList.getItems().add(msg);
		}
	}
}