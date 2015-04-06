var World = {
loaded: false,

init: function initFn() {
this.createOverlays();
},

createOverlays: function createOverlaysFn() {
AR.logger.debug("Entering createOverlays");

/*
* Initialize the first tracker. This tracker tracks the book covers contained in the given
* wtc file.
*/
AR.logger.debug("Initializing tracker");
var coverTracker = new AR.Tracker("images/BookCovers.wtc", {
onLoaded:this.initUI
});

/*
* Initialize a Trackable2DObject with a wild card so it will recognize any item in the loaded
* wtc file (who ever came up with this api is brilliant). When an item is recognized, it calls
* onEnterFieldOfVision.
*/
AR.logger.debug("Initializing trackable object");
var pageOne = new AR.Trackable2DObject(coverTracker, "*", {
onEnterFieldOfVision:
function openNewWTC(targetName)
{
bookName = String(targetName);
//We got a hit!
AR.logger.debug("Opening WTC file for: " + bookName);




//Try to load the wtc file matching the book name
var path = "images/";
path = path.concat(bookName, ".wtc");
AR.logger.debug(path);
// var lol = String(path);

this.bookTracker = new AR.Tracker(String(path),
{
onLoaded: function bookWTCLoaded()
{
AR.logger.debug("WTC file for specific book loaded!");
//change div text field to show book currenlty being tracked
document.getElementById('loadingMessage').innerHTML = "<div>" + bookName.replace("_"," ") +"</div>";
}
});
document.location = "architectsdk://" + bookName + "," + "0" ;

//Set up a new wildcard trackable object for the pages of the book currently being tracked
var trackableBook = new AR.Trackable2DObject(this.bookTracker, "*",
{
onEnterFieldOfVision: function pageRecognized(targetName)
{
pageNum = targetName;
//We got a hit for a book page
AR.logger.debug("Recognized book page " + pageNum + "!");
document.location = "architectsdk://" + bookName + "," + pageNum ;

//alert(bookName + ", " + pageNum );
//TODO: Call back to android to get the corresponding youtube link from the json file
// open("android-app://com.google.youtube/http/www.youtube.com/watch?v=Iu0VTI-ZSHQ");
}
});
}


});

},

initUI: function initUI()
{
AR.logger.debug ("Entering worldLoaded");
var cssDivLeft = " style='display: table-cell;vertical-align: middle; text-align: right; width: 50%; padding-right: 15px;'";
document.getElementById('loadingMessage').innerHTML = "<div >Please Scan a Book Cover</div>";

var e = document.getElementById('loadingMessage');


AR.logger.debug ("Leaving worldLoaded");
}
};

World.init();

function changeToCover()
{
World.init();
//document.location = "";
// document.getElementById('loadingMessage').innerHTML = "<div>CASH MONEY</div>";
}