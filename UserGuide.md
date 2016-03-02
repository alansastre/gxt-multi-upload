# Including and using widget in your project #

Including and using GXT Multi Upload widget in your project is easy. The widget requires GWT and GXT libraries and corresponding project structure. I am using GWT version 2.1.0 and GXT with version 2.2.1.
You may also want to see source code of demo project, which is located in project's SVN.

So, follow these steps:
  1. Download (or build from SVN) the latest version of widget.
  1. Include jar file into classpath.
  1. Include widget module into your GWT module.
  1. Instantiate and call MVP classes of widget.
  1. Create server side code for handling uploads.

## Download or build project from SVN ##
You can download the latest jar module of widget from Downloads section of this project web.

For checkout the project from SVN, see Source section. After project checkout, it is ready to build using Maven project tool. After build, you can install jar file it into your Maven repository.

## Include jar file into classpath ##
This depends on your favorite IDE or build tool. When you are using maven, and want to include widget into your project, you have to install jar into your Maven repository. Then add the following dependency fragment into your project:
```
<dependency>
  <groupId>com.gxt-multi-upload</groupId> 
  <artifactId>gxt-multi-upload</artifactId> 
  <version>0.1</version> 
</dependency>
```
The version number depends on downloaded/builded version.

## Include widget module into your GWT module ##
It's easy. Just add following fragment into your GWT module:
```
<inherits name='gxt.multiupload.MultiUpload'/>
```
MultiUpload is the name of project's GWT module.

## Instantiate and call MVP classes of widget ##
The source code for instantiate Presenter and View of widget should be following:
```
ColumnModel columnModel = ...
Grid<Model> grid = new Grid<Model>(new ListStore<Model>(), columnModel);

MultiUploadView view = new MultiUploadView(grid);
view.getFormPanel().setAction("multiupload");
MultiUploadPresenter presenter = new MultiUploadPresenter(view);
presenter.go();
```
First of all, you have to create a GXT's Grid instance, or you can use your custom one (extended from GXT's Grid class or its ancestors). ColumnModel can contains e.g. filename, but this is up to you. Grid has to be a Model type, which is GXT MultiUpload interface, which defines access methods for filename, status and status message. For detail info, see source code. The default implementation of this interface is FileUploadModel class. If you want use your own model, it  has to implements a Model interface.

In next lines, the view class is instantiated and the action (URL) of service for upload handling is defined. After instantiating presenter, the method go() is called, which binds Presenter code together with View and shows the dialog.

## Create server side code for handling uploads ##
Server side code can be written in any language, which supports HTTP  form-type request processing. The response has to be a HTML mime type in JSON format. The JSON response example is following:
```
{ name:'filename.jpg', status:'OK', message:'Ok.' }
```
It consist of three attributes:
  1. name - the name of uploaded file.
  1. status - string representation of status; for more info, see UploadStatus enumeration in the source code.
  1. message - human friendly message for the user. It could contains for example status message, size of uploaded file or just error messsage from the server.
This format is not strict, you can change existing key-pair attributes, or add another attributes to suit your needs.

The server side code should looks like following Java Servlet skelet:
```
public class GwtMultiUploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) {
		response.setContentType("text/html");
		FileUploadItem model = new FileUploadItem();
		try {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					// Handle form fields (if there are any)
				} else {
					// TODO handle file writing here

					String fileName = StringUtils.getFilename(StringUtils.cleanPath(item.getName()));
					model.setName(fileName);
					model.setState(UploadState.OK.name());
				}
			}
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getOutputStream(), model);	
		} catch (Exception ex) {
			// TODO Don't forget to handle error responding
		}		
	}
}
```
In code above is used [Jackson](http://jackson.codehaus.org/) library for serializing the model into JSON string, Spring Utils for cleaning up file path and finally [commons-upload](http://commons.apache.org/fileupload/) for file upload handling.