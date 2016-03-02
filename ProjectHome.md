# GXT Multi Upload #
GXT Multi Upload is a GXT ([Ext GWT](http://www.sencha.com/products/extgwt/)) widget for multiple files uploading.
Main features are following:
  * select multiple files into file list, which can be upload by clicking on a button
  * file list is represents as a grid; grid can contains additional columns, such as file description and so on
  * no underlying flash object, pure GWT/GXT code
  * the server side code can be implemented in any language
  * internationalization (i18n) support
  * source code follows MVP pattern
  * future improvements such as HTML5 support, canceling the upload and other features
Try the [demo](http://gxt-multiupload-demo.appspot.com/) of widget!
## How does it work ##
Multi upload widget is a GXT's Window class, so it appears as modal dialog.
Widget creates two hidden panels:
  1. AbsolutePanel, a div element, which contains a input file elements. Also called a file stack.
  1. FormPanel, a form element, which is used to submit particular file from file stack.
Button for adding file into file list is a styled input file element (GXT FileUploadField widget). After clicking on this widget and choosing a file thru standard browser's File Choose dialog, input element is detached from this widget and appended to hidden AbsolutePanel. Button for adding file is then replaced by new one. A new item is added into grid.

Files can be removed from file list; particular input file element is then removed from AbsolutePanel and removed from grid.

Clicking on Upload button detach first input file element from AbsolutePanel and appends it into FormPanel, which is then submitted to server. After server response (represented as JSON), this input  is removed from FormPanel and grid model is updated about file upload status. The whole algorithm is repeating for next files in file stack. Upload is done, when file stack contains no file.

![https://lh5.googleusercontent.com/_W8q2Ok90LTM/TbqiJihwxdI/AAAAAAAAE-0/vW9xAQMAs3o/gxt-multiupload-dialog.png](https://lh5.googleusercontent.com/_W8q2Ok90LTM/TbqiJihwxdI/AAAAAAAAE-0/vW9xAQMAs3o/gxt-multiupload-dialog.png)

_Widget in action._

Grid in example contains additional column (Description), which value is then sent as HTTP parameter together with form submission. This behaviour was achieved by extending MultiUploadPresenter class. Custom renderer of Status column can renders particular status e.g. by different colour or by using images.