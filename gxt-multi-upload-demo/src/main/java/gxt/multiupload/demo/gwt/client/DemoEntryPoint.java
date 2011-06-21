package gxt.multiupload.demo.gwt.client;

import gxt.multiupload.MultiUploadPresenter;
import gxt.multiupload.MultiUploadView;
import gxt.multiupload.icons.UploadIcons;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.InfoConfig;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class DemoEntryPoint implements EntryPoint {

	private static final String UPLOAD_URL = "upload";
	private static final int TOOLBAR_HEIGTH = 28;

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(createViewport());
		showInfoPanel();
	}

	private Viewport createViewport() {
		Viewport viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, TOOLBAR_HEIGTH);
		data.setMargins(new Margins());
		viewport.add(createToolbar(), data);
		return viewport;
	}

	private Widget createToolbar() {
		ToolBar toolBar = new ToolBar();
		toolBar.setBorders(true);
		toolBar.add(createAddButton());
		return toolBar;
	}

	private Component createAddButton() {
		Button fileUpload = new Button("Upload", new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				MultiUploadView view = new MultiUploadView(new FileUploadGrid());
				view.getFormPanel().setAction(UPLOAD_URL);
				MultiUploadPresenter presenter = new MultiUploadPresenter(view);
				presenter.go();
			}
		});
		fileUpload.setIcon(UploadIcons.INSTANCE.upload());
		return fileUpload;
	}
	
	private void showInfoPanel() {
		Info info = new Info();
		InfoConfig infoConfig = new InfoConfig("Info", "Welcome to GXT MultiUpload demo v0.1. File size is limited to 1 kB, due to data transfer. Uploaded files are not stored. Enjoy!");
		infoConfig.display = 10000;
		infoConfig.height = 100;
		info.show(infoConfig);
	}

}
