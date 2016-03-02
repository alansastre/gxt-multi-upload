# FAQ #

## How to include custom HTTP POST parameters ##
One of the solution is to add `FileBeforeSubmitListener` on `MultiUploadPresenter`. That listener is notified every time, before file is being submitted. In listener `onBeforeSubmit()` body method, you can add code for getting and setting the current URL action. So, the new URL action could contains origin value together with your own parameters.
```
FileBeforeSubmitListener listener = new FileBeforeSubmitListener() {
	@Override
	public void onBeforeSubmit(String filename) {
		String url = display.getFormPanel().getAction(); 
		// modify the url..
		display.getFormPanel().setAction(url);
	}
};
presenter.addFileBeforeSubmitListener(listener);
```

## How to change the button labels ##
Changing all button labels can be done due to GWT
internationalization. Both GXT and GXT Multi Upload library
messages are stored in static properties files. For more info, see GWT
site about static string internationalization.

For changing a Browse.. button text, create package
'com.extjs.gxt.ui.client.messages' in your GWT project.
Then create file XMessages_<your locale>.properties and add following line:
```
uploadField_browseText=<Label of browse button>
```_

XMessages.properties files (with various of languages) contains all
messages used in GXT and they are part of GXT jar file.

For changing other button text, create package 'gxt.multiupload' in
your GWT project.
Then create file UploadConstants